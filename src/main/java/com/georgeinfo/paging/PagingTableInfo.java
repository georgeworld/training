package com.georgeinfo.paging;

public class PagingTableInfo {
    private Long recordCount;
    private DataStageEnum dataStage;
    private Long pageRangeBegin;
    private Long pageRangeEnd;
    private Long lastPageSize;
    private Long completedPageSize;
    private String comment;
    private Long beforeCompletedPageSize;
    private Long firstPageSize;

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public DataStageEnum getDataStage() {
        return dataStage;
    }

    public void setDataStage(DataStageEnum dataStage) {
        this.dataStage = dataStage;
    }

    public Long getPageRangeBegin() {
        return pageRangeBegin;
    }

    public void setPageRangeBegin(Long pageRangeBegin) {
        this.pageRangeBegin = pageRangeBegin;
    }

    public Long getPageRangeEnd() {
        return pageRangeEnd;
    }

    public void setPageRangeEnd(Long pageRangeEnd) {
        this.pageRangeEnd = pageRangeEnd;
    }

    public Long getLastPageSize() {
        return lastPageSize;
    }

    public void setLastPageSize(Long lastPageSize) {
        this.lastPageSize = lastPageSize;
    }

    public Long getCompletedPageSize() {
        return completedPageSize;
    }

    public void setCompletedPageSize(Long completedPageSize) {
        this.completedPageSize = completedPageSize;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getBeforeCompletedPageSize() {
        return beforeCompletedPageSize;
    }

    public void setBeforeCompletedPageSize(Long beforeCompletedPageSize) {
        this.beforeCompletedPageSize = beforeCompletedPageSize;
    }

    public Long getFirstPageSize() {
        return firstPageSize;
    }

    public void setFirstPageSize(Long firstPageSize) {
        this.firstPageSize = firstPageSize;
    }
}
