package com.georgeinfo.paging.chain;

import com.georgeinfo.response.Result;

public interface PagingTableNode {
    Result doProcess(PagingTableContext context);
}
