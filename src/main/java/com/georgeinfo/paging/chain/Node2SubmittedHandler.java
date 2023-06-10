package com.georgeinfo.paging.chain;

import com.georgeinfo.paging.PagingHelper;
import com.georgeinfo.paging.PagingTableInfo;
import com.georgeinfo.response.Result;
import com.georgeinfo.utils.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.georgeinfo.paging.DataStageEnum;
import javax.annotation.Resource;

public class Node2SubmittedHandler implements PagingTableNode {
    private static final Logger logger = LoggerFactory.getLogger(Node2SubmittedHandler.class);
    @Resource
    private PagingHelper pagingHelper;

    @Override
    public Result doProcess(PagingTableContext context) {
        logger.info("第二阶段已提交的数据处理：开始计算页码区间，context={}", JSON.toJSONString(context));
        PagingTableInfo pti = context.getPtiMap().get(DataStageEnum.SUBMITTED_WAITING_AUDITED.getCode());
        //寻找上一个数据不为空的数据阶段
        Result<PagingTableInfo> prePtiResult = pagingHelper.getPrePti(context.getPtiList(), pti.getDataStage().getSortNum());
        PagingTableInfo prePti = prePtiResult.getData();

        //获取上一个节点中的分页数据
        logger.info("已提交阶段的分页计算，prePti={},pageSize={}", JSON.toJSONString(prePti), context.getPageSize());
        pagingHelper.fillPageTableInfo(pti, prePti, context.getPageSize());
        return Result.createSuccess();
    }
}
