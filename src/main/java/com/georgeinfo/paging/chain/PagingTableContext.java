package com.georgeinfo.paging.chain;

import com.georgeinfo.paging.PagingTableInfo;

import java.util.List;
import java.util.Map;

public class PagingTableContext {
    private Integer pageSize;
    private Map<Integer, PagingTableInfo> ptiMap;
    /**
     * 按照数据阶段倒序排的分页信息列表
     */
    private List<PagingTableInfo> ptiList;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map<Integer, PagingTableInfo> getPtiMap() {
        return ptiMap;
    }

    public void setPtiMap(Map<Integer, PagingTableInfo> ptiMap) {
        this.ptiMap = ptiMap;
    }

    public List<PagingTableInfo> getPtiList() {
        return ptiList;
    }

    public void setPtiList(List<PagingTableInfo> ptiList) {
        this.ptiList = ptiList;
    }
}
