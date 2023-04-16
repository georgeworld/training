package com.georgeinfo.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalConverter implements Converter<BigDecimal> {
    @Override
    public Class supportJavaTypeKey() {
        return BigDecimal.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public BigDecimal convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String str = cellData.getStringValue();
        return new BigDecimal(str).setScale(2, BigDecimal.ROUND_DOWN);
    }


    @Override
    public WriteCellData<?> convertToExcelData(BigDecimal value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        BigDecimal cur = value.setScale(2, RoundingMode.DOWN);
        return new WriteCellData<>(cur.toString());
    }

}