package com.georgeinfo.excel.template;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

public class DemoTemplate {
    @ColumnWidth(50)
    @ExcelProperty({"ID", "枚举ID"})
    private Integer id;
    @ColumnWidth(50)
    @ExcelProperty({"name", "枚举名称"})
    private String name;

    public DemoTemplate() {
    }

    public DemoTemplate(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
