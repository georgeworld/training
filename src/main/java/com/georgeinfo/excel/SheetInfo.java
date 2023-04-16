package com.georgeinfo.excel;

import com.alibaba.excel.write.handler.WriteHandler;

import java.util.List;

/**
 * Excel Sheet信息
 */
public class SheetInfo {
    /**
     * sheet名字
     */
    private String sheetName;
    /**
     * 数据模板类
     */
    private Class<?> templateClass;
    /**
     * handler单元格处理器列表，应该是一个有序列表
     */
    private List<WriteHandler> handlerList;
    /**
     * 数据内容列表
     */
    private List data;

    public SheetInfo() {
    }

    public SheetInfo(String sheetName, Class<?> templateClass, List<WriteHandler> handlerList, List data) {
        this.sheetName = sheetName;
        this.templateClass = templateClass;
        this.handlerList = handlerList;
        this.data = data;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Class<?> getTemplateClass() {
        return templateClass;
    }

    public void setTemplateClass(Class<?> templateClass) {
        this.templateClass = templateClass;
    }

    public List<WriteHandler> getHandlerList() {
        return handlerList;
    }

    public void setHandlerList(List<WriteHandler> handlerList) {
        this.handlerList = handlerList;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
