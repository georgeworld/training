package com.georgeinfo.paging.chain;

import com.georgeinfo.paging.PagingTableInfo;
import com.georgeinfo.response.Result;
import com.georgeinfo.utils.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Node0PretreatmentHandler implements PagingTableNode {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Result doProcess(PagingTableContext context) {
        logger.info("第0阶段预处理，context={}", JSON.toJSONString(context));
        //将数据阶段倒序排序
        List<PagingTableInfo> ptiList = context.getPtiMap().values().stream().collect(Collectors.toList());
        List<PagingTableInfo> sortedPtiList = ptiList.stream().sorted((o2, o1) -> {
            int sortResult = ((o1.getDataStage().getSortNum() == o2.getDataStage().getSortNum()) ? 0 : 1);
            return (o1.getDataStage().getSortNum() < o2.getDataStage().getSortNum()) ? -1 : sortResult;
        }).collect(Collectors.toList());
        context.setPtiList(sortedPtiList);
        return Result.createSuccess();
    }
}
