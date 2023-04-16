package com.georgeinfo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MultiSheetExcelBuilder implements SheetWriteHandler {
    private static final Logger logger = LoggerFactory.getLogger(MultiSheetExcelBuilder.class);

    private String filePath;
    private List<SheetInfo> sheetList;

    public String getFilePath() {
        return filePath;
    }

    public static MultiSheetExcelBuilder setFilePath(String filePath) {
        MultiSheetExcelBuilder builder = new MultiSheetExcelBuilder();
        builder.filePath = filePath;
        return builder;
    }

    public List<SheetInfo> getSheetList() {
        return sheetList;
    }

    public MultiSheetExcelBuilder setSheetList(List<SheetInfo> sheetList) {
        this.sheetList = sheetList;
        return this;
    }

    public boolean build() {
        try {
//            String filePath = System.getProperty("user.dir")+File.separator+"excel-template.xlsx";

            // 方法1 使用已有的策略 推荐
            // HorizontalCellStyleStrategy 每一行的样式都一样 或者隔行一样
            // AbstractVerticalCellStyleStrategy 每一列的样式都一样 需要自己回调每一页
            // 头的策略
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            // 背景设置为红色
            headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontHeightInPoints((short) 20);
            headWriteCellStyle.setWriteFont(headWriteFont);

            // 内容的策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
            contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
            // 背景绿色
            contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            WriteFont contentWriteFont = new WriteFont();
            // 字体大小
            contentWriteFont.setFontHeightInPoints((short) 20);
            contentWriteCellStyle.setWriteFont(contentWriteFont);
            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);


            ExcelWriter writer = EasyExcel.write(filePath).build();

            if (sheetList == null || sheetList.isEmpty()) {
                logger.error("生成excel失败，没有配置sheet数据");
                return false;
            } else {
                int i = 0;
                for (SheetInfo sheet : sheetList) {
                    ExcelWriterSheetBuilder sheetBuilder = EasyExcel.writerSheet(i, sheet.getSheetName());
                    if (sheet.getHandlerList() != null && !sheet.getHandlerList().isEmpty()) {
                        for (WriteHandler handler : sheet.getHandlerList()) {
                            sheetBuilder
                                    .registerWriteHandler(handler);
//                                    .registerConverter(new BigDecimalConverter());
                        }
                    }
                    WriteSheet ws = sheetBuilder.head(sheet.getTemplateClass()).build();
                    writer.write(sheet.getData(), ws);
                    i++;
                }
            }
            //千万别忘记finish 会帮忙关闭流
            writer.finish();

        } catch (Exception ex) {
            logger.error("生成excel时出现异常", ex);
        }
        return true;
    }
}

