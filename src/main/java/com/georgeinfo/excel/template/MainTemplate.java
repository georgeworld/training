package com.georgeinfo.excel.template;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.georgeinfo.excel.BigDecimalConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainTemplate {
    @ColumnWidth(50)
    @ExcelProperty(value = {"firstName", "姓"}, index = 0)
    private String firstName;
    @ColumnWidth(50)
    @ExcelProperty(value = {"lastName", "名"}, index = 1)
    private String lastName;
    @ColumnWidth(50)
    //#.##和0.00的区别就是，比如你导入的数值是132.70，#.##会忽略掉后面的0变成132.7，而0.00则不会忽略，保持原来的132.70
    //使用String 接收该单元格的值时，会触发该注解的解析，舍弃小数点函数：TRUNC
    @NumberFormat(value = "#.#",roundingMode=RoundingMode.DOWN)//浮点数，保留1位小数
    //dataFormat的值是com.alibaba.excel.constant.BuiltinFormats中_formats数组的下标地址
    @ContentStyle(dataFormat = 4)
    @ExcelProperty(value = {"money", "工资"}, index = 2)
    private String money;
    @ColumnWidth(50)
    @ExcelProperty(value = {"area", "活跃地区"}, index = 3)
    private String area;
    @ColumnWidth(50)
    @ContentStyle(dataFormat = 49)
    @ExcelProperty(value = {"date", "日期"}, index = 4,converter = CustomStringStringConverter.class)
//    @DateTimeFormat("yyyy-MM-dd")
    private String date;

    public MainTemplate() {
    }

    public MainTemplate(String firstName, String lastName, String money, String area,String date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.money = money;
        this.area = area;
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
