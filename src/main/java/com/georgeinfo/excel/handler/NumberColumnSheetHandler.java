package com.georgeinfo.excel.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

public class NumberColumnSheetHandler implements SheetWriteHandler {
    /** 本sheet页填充的数据行数：不算表头行 */
    private Integer totalRows;

    public NumberColumnSheetHandler(Integer totalRows) {
        this.totalRows = totalRows;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        CellStyle cs = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        cs.setDataFormat(format.getFormat("0.0"));

        int numberColumn = 2;
        sheet.setDefaultColumnStyle(numberColumn, cs);

        DataValidationHelper helper = sheet.getDataValidationHelper();
        //$$$ 创建数值约束
        DataValidationConstraint integerConstraint = helper.createNumericConstraint(DataValidationConstraint.ValidationType.DECIMAL,
                DataValidationConstraint.OperatorType.BETWEEN, "1.1", "3222900.1");
        // 创建验证
        //设置下拉框得起始行，结束行，起始列，结束列，只校验新单元格，对于生成excel时已经填充了数据的单元格，不校验数值
        int firstRow = totalRows + 2;
        int lastRow = 0x10000;
        int firstCol = numberColumn;
        int lastCol = numberColumn;
        CellRangeAddressList list = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        DataValidation validation = helper.createValidation(integerConstraint, list);
        // 阻止输入非下拉选项的值
        validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        validation.setShowErrorBox(true);
        validation.setSuppressDropDownArrow(true);
        validation.createErrorBox("提示", "必须输入数字,且上下限为1.1~3222900.1");
        sheet.addValidationData(validation);
    }
}