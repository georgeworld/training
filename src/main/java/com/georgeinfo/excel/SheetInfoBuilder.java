package com.georgeinfo.excel;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.WriteHandler;

import java.util.List;

/**
 * Excel Sheet信息
 */
public class SheetInfoBuilder {
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

    public SheetInfoBuilder() {
    }

    public SheetInfoBuilder(String sheetName, Class<?> templateClass, List<WriteHandler> handlerList, List data) {
        this.sheetName = sheetName;
        this.templateClass = templateClass;
        this.handlerList = handlerList;
        this.data = data;
    }

    public String getSheetName() {
        return sheetName;
    }

    public static SheetInfoBuilder setSheetName(String sheetName) {
        SheetInfoBuilder builder = new SheetInfoBuilder();
        builder.sheetName = sheetName;
        return builder;
    }

    public Class<?> getTemplateClass() {
        return templateClass;
    }

    public SheetInfoBuilder setTemplateClass(Class<?> templateClass) {
        this.templateClass = templateClass;
        return this;
    }

    public List<WriteHandler> getHandlerList() {
        return handlerList;
    }

    public SheetInfoBuilder setHandlerList(List<WriteHandler> handlerList) {
        this.handlerList = handlerList;
        return this;
    }

    public List getData() {
        return data;
    }

    public SheetInfoBuilder setData(List data) {
        this.data = data;
        return this;
    }

    public SheetInfo build() {
        return new SheetInfo(sheetName, templateClass, handlerList, data);
    }

}
