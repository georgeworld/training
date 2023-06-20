package com.georgeinfo.excel;


import java.util.List;

public class HeadVO implements Comparable<HeadVO> {
    /**
     * 列头名
     */
    private List<String> headTitle;
    /**
     * 字段名
     */
    private String key;
    /**
     * 主排序
     */
    private int index;
    /**
     * 次排序
     */
    private int order;

    public HeadVO() {
    }

    public HeadVO(List<String> headTitle, String key, int index, int order) {
        this.headTitle = headTitle;
        this.key = key;
        this.index = index;
        this.order = order;
    }

    public List<String> getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(List<String> headTitle) {
        this.headTitle = headTitle;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(HeadVO o) {
        if (this.index == o.getIndex()) {
            return this.order - o.getOrder();
        }
        return this.index - o.getIndex();
    }
}
