package com.georgeinfo.paging;

import com.georgeinfo.paging.chain.PagingTableContext;
import com.georgeinfo.paging.chain.PagingTableNode;
import com.georgeinfo.response.Result;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.georgeinfo.utils.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PagingHelper {
    private static List<PagingTableNode> pagingHandlerList = new ArrayList<>();
    private static final Logger LOG = LoggerFactory.getLogger(PagingHelper.class);

    public PageCountAndLastPage getPageCount(long stageDataCount, int pageSize) {
        if (stageDataCount <= 0) {
            LOG.info("本阶段数据查出为0，不负责任何页码计算");
            return new PageCountAndLastPage(0L, 0L);
        }
        //分页计算
        long pageCount = stageDataCount / pageSize;
        long modPage = stageDataCount % pageSize;
        long lastPageSize = pageSize;
        if (modPage != 0) {
            pageCount++;
            lastPageSize = modPage;
        }
        return new PageCountAndLastPage(pageCount, lastPageSize);
    }

    public void fillPageTableInfo(PagingTableInfo pti, PagingTableInfo prePti, Integer pageSize) {
        LOG.info("填充分页信息时，pti={},prePti={},pageSize={}", JSON.toJSONString(pti), JSON.toJSONString(prePti), pageSize);
        if (pti.getRecordCount() <= 0) {
            pti.setPageRangeBegin(0L);
            pti.setPageRangeEnd(0L);
            pti.setLastPageSize(0L);
            pti.setFirstPageSize(0L);
            pti.setCompletedPageSize(0L);
            pti.setComment("无数据，跳过本数据节点");
            return;
        }
        //如果没有在数据
        if (prePti != null) {
            PageCountAndLastPage pclp = getPageCount(pti.getRecordCount(), pageSize);
            long myPageCount = pclp.getPageCount();
            pti.setPageRangeBegin(1L);
            pti.setPageRangeEnd(myPageCount);
            pti.setLastPageSize(pclp.getLastPageSize());
            pti.setCompletedPageSize(pti.getLastPageSize());
            pti.setBeforeCompletedPageSize(pti.getCompletedPageSize());
            if (1 == myPageCount) {
                pti.setFirstPageSize(pclp.getLastPageSize());
            } else {
                pti.setFirstPageSize(pageSize.longValue());
            }
            pti.setComment("上一个节点为空，本节点当做第一个节点来计算分页");
        } else {
            LOG.info("找到上一个数据节点：{}", JSON.toJSONString(prePti));
            if (prePti.getLastPageSize().longValue() == pageSize.intValue()) {
                //如果上一个节点红的最后一页数据时全页数据，则本节点正常分页即可
                fullPageSetting(pti, prePti, pageSize, "上一个节点(" + prePti.getDataStage().name() + "）最后一页已满页");
            } else {
                if (prePti.getCompletedPageSize().longValue() == pageSize.intValue()) {
                    //上一个数据阶段，最后一页经过补全后，已经满页了，则正常分页
                    fullPageSetting(pti, prePti, pageSize, "上一个节点（" + prePti.getDataStage().name() + "）最后一页，补全后满页");
                } else {
                    //为上一节点的最后一页补数据
                    pti.setPageRangeBegin(prePti.getPageRangeEnd());
                    //需要为上一个节点补的数据量
                    long complementNum = pageSize.intValue() - prePti.getCompletedPageSize().longValue();
                    //本节点总数据量减去上一节点补全的数据量，然后计算本节点所负责的页码区间
                    long myRemainingCount = pti.getRecordCount().longValue() - complementNum;
                    //给上个节点补全数据后，本节点就没有数据了
                    if (myRemainingCount <= 0) {
                        pti.setPageRangeEnd(pti.getPageRangeBegin());
                        pti.setFirstPageSize(pti.getRecordCount());
                        pti.setLastPageSize(pti.getRecordCount());

                        Long beforeCompleted = prePti.getCompletedPageSize();
                        prePti.setBeforeCompletedPageSize(beforeCompleted);
                        prePti.setCompletedPageSize(beforeCompleted.longValue() + pti.getFirstPageSize().longValue());
                        pti.setComment("为上一节点（" + prePti.getDataStage().name() + "）补充数据后，本节点没有数据了");
                        pti.setCompletedPageSize(prePti.getCompletedPageSize());
                        pti.setBeforeCompletedPageSize(pti.getCompletedPageSize());


                    } else {
                        //为上一个节点补充数据后，本节点还剩下数据
                        Long beforeCompleted = prePti.getCompletedPageSize();
                        prePti.setBeforeCompletedPageSize(beforeCompleted);
                        prePti.setCompletedPageSize(beforeCompleted.longValue() + complementNum);
                        pti.setComment("为上一节点（" + prePti.getDataStage().name() + "）补充数据后，本节点还有数据，上个节点补全");
                        PageCountAndLastPage pclp = getPageCount(myRemainingCount, pageSize.intValue());
                        pti.setPageRangeEnd(prePti.getPageRangeEnd().longValue() + pclp.getPageCount().longValue());
                        pti.setFirstPageSize(complementNum);
                        pti.setLastPageSize(pclp.getLastPageSize());
                        pti.setCompletedPageSize(pti.getLastPageSize());
                        pti.setBeforeCompletedPageSize(pti.getCompletedPageSize());
                    }
                }
            }
        }
    }

    private void fullPageSetting(PagingTableInfo pti, PagingTableInfo prePti, Integer pageSize, String s) {
    }


    public static Result<Map<Integer, PagingTableInfo>> fillPageRange(Integer pageSize,
                                                                      Map<Integer, PagingTableInfo> ptiMap) {
        Result<Map<Integer, PagingTableInfo>> result = Result.createSuccess();
        //执行责任链，每个数据表使用自己的节点计算自己的数据
        PagingTableContext context = new PagingTableContext();
        context.setPageSize(pageSize);
        context.setPtiMap(ptiMap);
        LOG.info("开始执行分页节点计算前，上下文：{}", JSON.toJSONString(context));
        for (PagingTableNode ptn : pagingHandlerList) {
            Result ptResult = ptn.doProcess(context);
            if (!ptResult.isSuccess()) {
                return result.failure().setMsg("执行" + ptn.getClass().getName() + "节点失败，" + ptResult.getMsg());
            }
        }
        LOG.info("执行分页节点计算后，上下文：{}", JSON.toJSONString(context));
        return result.setData(ptiMap).setDetail(context.getPtiList());
    }

    public Result<PagingTableInfo> getPrePti(List<PagingTableInfo> ptiList, int sortNum) {
        return null;
    }
}
