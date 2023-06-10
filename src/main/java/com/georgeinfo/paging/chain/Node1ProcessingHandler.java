package com.georgeinfo.paging.chain;

import com.georgeinfo.paging.PageCountAndLastPage;
import com.georgeinfo.paging.PagingHelper;
import com.georgeinfo.paging.PagingTableInfo;
import com.georgeinfo.response.Result;
import com.georgeinfo.utils.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.georgeinfo.paging.DataStageEnum;

import javax.annotation.Resource;

public class Node1ProcessingHandler implements PagingTableNode {
    private static final Logger logger = LoggerFactory.getLogger(Node1ProcessingHandler.class);
    @Resource
    private PagingHelper pagingHelper;

    @Override
    public Result doProcess(PagingTableContext context) {
        logger.info("第一阶段处理中的数据处理：开始计算页码区间，context={}", JSON.toJSONString(context));
        PagingTableInfo pti = context.getPtiMap().get(DataStageEnum.PROCESSING.getCode());
        if (pti.getRecordCount() <= 0) {
            pti.setPageRangeBegin(0L);
            pti.setPageRangeEnd(0L);
            pti.setLastPageSize(0L);
            pti.setFirstPageSize(0L);
            pti.setCompletedPageSize(0L);
            pti.setComment("第一个节点就没有数据");
        } else {
            PageCountAndLastPage pclp = pagingHelper.getPageCount(pti.getRecordCount(), context.getPageSize());
            long myPageCount = pclp.getPageCount();
            pti.setPageRangeBegin(1L);
            pti.setPageRangeEnd(myPageCount);
            pti.setLastPageSize(pclp.getLastPageSize());
            pti.setCompletedPageSize(pti.getLastPageSize());
            if (1 == myPageCount) {
                pti.setFirstPageSize(pclp.getLastPageSize());
            } else {
                pti.setFirstPageSize(context.getPageSize().longValue());
            }
            pti.setComment("第一个节点又数据");
        }
        return Result.createSuccess();
    }
}
