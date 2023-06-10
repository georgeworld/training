package com.georgeinfo.paging;

import java.util.Map;

public class CountPagingInfo {
    private Map<Integer, PagingTableInfo> ptiMap;

    public Map<Integer, PagingTableInfo> getPtiMap() {
        return ptiMap;
    }

    public void setPtiMap(Map<Integer, PagingTableInfo> ptiMap) {
        this.ptiMap = ptiMap;
    }
}
