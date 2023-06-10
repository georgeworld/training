package com.georgeinfo.excel.template;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomStringStringConverter implements Converter<String> {
    /**
     * 对应的Java实体类字段的类型
     */
    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    /**
     * Excel中列单元格格式，如果是自定义格式，
     * 是CellDataTypeEnum.NUMBER枚举，如果是混合列（有文本，有自定义，有数字），按照字符串来处理
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 读取一列数据时，会调用该方法进行类型转换
     *
     * @param context
     * @return
     */
    @Override
    public String convertToJavaData(ReadConverterContext<?> context) {
        //该列的表头中的两行，读取到的是字符串类型
        if (context.getReadCellData().getType() == CellDataTypeEnum.STRING) {
            return "我是自定义列中的文本单元格：" + context.getReadCellData().getStringValue();
        } else if (context.getReadCellData().getType() == CellDataTypeEnum.NUMBER) {//表头之下，是自定义格式
            //将数字格式表示的日期，转换为文本格式的日期
            LocalDate localDate = LocalDate.of(1900, 1, 1);
            //补上easyexcel bug导致的日期数差2天
            localDate = localDate.plusDays(context.getReadCellData().getNumberValue().longValue() - 2);
            String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return date;
        } else {//默认按照字符串单元格来处理
            return context.getReadCellData().getStringValue();
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) {
        return new WriteCellData<>(context.getValue());
    }

}