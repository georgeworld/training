package com.georgeinfo.paging;

public class PageCountAndLastPage {
    private Long pageCount;
    private Long lastPageSize;

    public PageCountAndLastPage() {
    }

    public PageCountAndLastPage(Long pageCount, Long lastPageSize) {
        this.pageCount = pageCount;
        this.lastPageSize = lastPageSize;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getLastPageSize() {
        return lastPageSize;
    }

    public void setLastPageSize(Long lastPageSize) {
        this.lastPageSize = lastPageSize;
    }
}
