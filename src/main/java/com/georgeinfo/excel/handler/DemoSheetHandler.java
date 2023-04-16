package com.georgeinfo.excel.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

public class DemoSheetHandler implements SheetWriteHandler {
    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
//        Workbook workbook = writeWorkbookHolder.getWorkbook();
//        Sheet sheet = workbook.getSheetAt(0);
//        //设置标题,设置首行表头样式
//        Row row1 = sheet.createRow(0);
//        // 设置行高
//        row1.setHeight((short) 900);
//        Cell cell1 = row1.createCell(0);
//        cell1.setCellValue("审定名单");
//        // 设置水平、垂直居中
//        CellStyle cellStyle = workbook.createCellStyle();
//        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        cellStyle.setAlignment(HorizontalAlignment.CENTER);
//        // 设置字体样式，bold加粗、fontHeight设置字体大小
//        Font font = workbook.createFont();
//        font.setBold(true);
//        font.setFontHeight((short) 500);
//        cellStyle.setFont(font);
//        cell1.setCellStyle(cellStyle);
//        // CellRangeAddress(1, 1, 0, 17) 用于合并表格，其中四个参数含义起始行号、终止行号、起始列号、终止列号
//        sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, 10));
//        //设置填表日期
//        Row row2 = sheet.createRow(1);
//        row2.setHeight((short) 500);
//        // 在当前行的第10+1列处展示（时间：2021-05-31）
//        row2.createCell(10).setCellValue("时间：" + DateUtil.format(new Date(), DateUtil.DATE_PATTERN_DEFAULT));

    }
}