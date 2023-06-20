package com.georgeinfo.excel.handler;

import com.alibaba.excel.constant.BuiltinFormats;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.DataFormatData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.util.NumberDataFormatterUtils;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.property.ExcelWriteHeadProperty;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

public class ContentCellHandler extends AbstractCellStyleStrategy {
    private static Logger logger = LoggerFactory.getLogger(ContentCellHandler.class);
    /**
     * 本sheet页填充的数据行数：不算表头行
     */
    private Integer totalRows;
    private WriteCellStyle headWriteCellStyle;
    private List<WriteCellStyle> contentWriteCellStyleList;

    public ContentCellHandler(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public ContentCellHandler(WriteCellStyle headWriteCellStyle, List<WriteCellStyle> contentWriteCellStyleList) {
        this.headWriteCellStyle = headWriteCellStyle;
        this.contentWriteCellStyleList = contentWriteCellStyleList;
    }

    public ContentCellHandler(WriteCellStyle headWriteCellStyle, WriteCellStyle contentWriteCellStyle) {
        this.headWriteCellStyle = headWriteCellStyle;
        if (contentWriteCellStyle != null) {
            this.contentWriteCellStyleList = ListUtils.newArrayList(new WriteCellStyle[]{contentWriteCellStyle});
        }

    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        if (head != null) {
            //重新设置表头信息
//            List<String> headNameList = head.getHeadNameList();
//            if (CollectionUtils.isNotEmpty(headNameList)) {
//                for (String headName: headNameList) {
//                    if(headName.equals("工资")){
//                        head.setColumnIndex(0);
//                    }
//                    if(headName.equals("姓")){
//                        head.setColumnIndex(2);
//                    }
//                }
//            }
        }
    }


    protected void setHeadCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        logger.info("触发自定义表头样式设置方法");
    }

    protected void setContentCellStyle(CellWriteHandlerContext context) {
        logger.info("进入了自定义内容单元格样式的方法");
//        if (!this.stopProcessing(context) && !CollectionUtils.isEmpty(this.contentWriteCellStyleList)) {
//            WriteCellData<?> cellData = context.getFirstCellData();
//            if (context.getRelativeRowIndex() != null && context.getRelativeRowIndex() > 0) {
//                WriteCellStyle.merge((WriteCellStyle) this.contentWriteCellStyleList.get(context.getRelativeRowIndex() % this.contentWriteCellStyleList.size()), cellData.getOrCreateStyle());
//            } else {
//                WriteCellStyle.merge((WriteCellStyle) this.contentWriteCellStyleList.get(0), cellData.getOrCreateStyle());
//            }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (context.getColumnIndex() == 2) {
//            double valueNumber = context.getCell().getNumericCellValue();
//            logger.info("数字列单元格：{}-{}的原始值：{}", context.getRowIndex(), context.getColumnIndex(), valueNumber);
//            String value = String.valueOf(valueNumber);
//            if (StringUtils.isNotBlank(value) && value.contains(".")) {
//                char[] chars = value.toCharArray();
//                StringBuilder sb = new StringBuilder();
//                int i = -1;
//                boolean beginCount = false;
//                for (char c : chars) {
//                    if (i == 2) {
//                        break;
//                    }
//                    sb.append(c);
//                    if (c == '.') {
//                        beginCount = true;
//                    }
//                    if (beginCount) {
//                        i++;
//                    }
//                }
//                String valueString = sb.toString();
//                logger.info("单元格:{}-{}处理后小数点后的值：{}", context.getRowIndex(), context.getColumnIndex(), valueString);
//                context.getCell().setCellValue(Double.valueOf(valueString));
//            }
        }
//        }

        if (context.getColumnIndex() == 2) {
            logger.info("尝试自定义第三列的数据格式");
            // 第一个单元格
            // 只要不是头 一定会有数据 当然fill的情况 可能要context.getCellDataList() ,这个需要看模板，因为一个单元格会有多个 WriteCellData
            WriteCellData<?> cellData = context.getFirstCellData();
            // 这里需要去cellData 获取样式
            // 很重要的一个原因是 WriteCellStyle 和 dataFormatData绑定的 简单的说 比如你加了 DateTimeFormat
            // ，已经将writeCellStyle里面的dataFormatData 改了 如果你自己new了一个WriteCellStyle，可能注解的样式就失效了
            // 然后 getOrCreateStyle 用于返回一个样式，如果为空，则创建一个后返回
            WriteCellStyle writeCellStyle = cellData.getOrCreateStyle();
            writeCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND
            writeCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
//            DataFormatData formatData = new DataFormatData();
//            formatData.setFormat("#.##");
//            writeCellStyle.setDataFormatData(formatData);

            // 这样样式就设置好了 后面有个FillStyleCellWriteHandler 默认会将 WriteCellStyle 设置到 cell里面去 所以可以不用管了

//            DataFormat dataFormat = context.getWriteSheetHolder().getSheet().getWorkbook().createDataFormat();
//            short format = dataFormat.getFormat("#.##");
////            Cell cell = context.getCell();
////            CellStyle cs = cell.getCellStyle();
////            cs.setDataFormat(format);
////            cell.setCellStyle(cs);
            //$$$ 强制设置单元格保留一位小数
            DataFormatData formatData = new DataFormatData();
            formatData.setFormat("0.0");
            writeCellStyle.setDataFormatData(formatData);

        }
    }

    protected boolean stopProcessing(CellWriteHandlerContext context) {
        return context.getFirstCellData() == null;
    }


    /**
     * 字体样式
     *
     * @param size   字体大小
     * @param isBold 是否加粗
     * @return
     */
    private Font getFont(Workbook workbook, short size, boolean isBold) {
        Font font = workbook.createFont();
        font.setFontName("宋体"); // 字体样式
        font.setBold(isBold);    // 是否加粗
        font.setColor(IndexedColors.RED.getIndex());
        font.setFontHeightInPoints(size);   // 字体大小
        return font;
    }

    /**
     * 设置单元格为文本格式
     */
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        // 3.0 设置单元格为文本
        Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
        System.out.println("列：" + cell.getColumnIndex() + "的值是：" + cell.getStringCellValue());
        if (cell.getColumnIndex() == 4 && cell.getRowIndex() > 1) {
            CellStyle css = workbook.createCellStyle();
            //设置单元格为文本格式
            DataFormat format = workbook.createDataFormat();
            css.setDataFormat(format.getFormat("yyyy-mm-dd"));
            //设置字体大小和颜色
            Font font = getFont(workbook, (short) 16, true);
            css.setFont(font);
            Sheet sheet = writeSheetHolder.getSheet();
            sheet.setDefaultColumnStyle(4, css);

            //增加单元格校验器
            DataValidationHelper helper = sheet.getDataValidationHelper();
            // 创建数值约束
            DataValidationConstraint integerConstraint = helper.createDateConstraint(
                    DataValidationConstraint.OperatorType.BETWEEN, "Date(1990,1,1)", "Date(2999,12,31)", "yyyy-mm-dd");
            // 创建验证
            //设置下拉框得起始行，结束行，起始列，结束列
            int firstRow = 2;
            int lastRow = 0x10000;
            int firstCol = 4;
            int lastCol = 4;
            CellRangeAddressList list = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
            DataValidation validation = helper.createValidation(integerConstraint, list);
            // 阻止输入非下拉选项的值
            validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
            validation.setShowErrorBox(true);
            validation.setSuppressDropDownArrow(true);
            validation.createErrorBox("提示", "日期格式不正确");
            sheet.addValidationData(validation);
        }
    }

    //设置单元格为文本格式
//    @Override
//    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
//        if (cell.getColumnIndex() == 4) {
//            //设置单元格格式为文本
//            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
//            CellStyle cellStyle = workbook.createCellStyle();
//            DataFormat dataFormat = workbook.createDataFormat();
//            cellStyle.setDataFormat(dataFormat.getFormat("@"));
//            cell.setCellStyle(cellStyle);
//        }
//    }

    public void afterCellDispose(CellWriteHandlerContext context) {
        if (context.getHead() != null) {
            if (context.getHead()) {
                this.setHeadCellStyle(context);
            } else {
                this.setContentCellStyle(context);

                List<WriteCellData<?>> cellDataList = context.getCellDataList();

                //设置单元格默认格式为本文格式
                Workbook workbook = context.getWriteWorkbookHolder().getWorkbook();
                if (context.getColumnIndex() == 0 ||
                        context.getColumnIndex() == 1) {
                    DataFormat dataFormat = workbook.createDataFormat();
                    for (WriteCellData<?> writeCellData : cellDataList) {
                        WriteCellStyle writeCellStyle = writeCellData.getOrCreateStyle();
                        DataFormatData dataFormatData = new DataFormatData();
                        dataFormatData.setIndex(dataFormat.getFormat("@"));
                        writeCellStyle.setDataFormatData(dataFormatData);
                    }
                }

                //设置日期单元格格式
                if (context.getColumnIndex() == 4 && context.getRowIndex() > 1) {
                    DataFormat dataFormat = workbook.createDataFormat();
                    for (WriteCellData<?> writeCellData : cellDataList) {
                        WriteCellStyle writeCellStyle = writeCellData.getOrCreateStyle();
                        DataFormatData dataFormatData = new DataFormatData();
                        dataFormatData.setIndex(dataFormat.getFormat("yyyy-mm-dd"));
                        writeCellStyle.setDataFormatData(dataFormatData);
                    }
                }

                //为单元格增加公式
                try {
                    if (context.getColumnIndex() == 2) {
                        //$$$ 设置单元格的公式
                        Cell cell = context.getCell();
                        String v = cell.getStringCellValue();
                        Double dv = Double.parseDouble(v.trim());
                        //向下取整
//                    cell.setCellFormula("ROUNDDOWN(" + dv + ",1)");
                        //四舍五入
                        cell.setCellFormula("ROUND(" + dv + ",1)");
                    }
                }catch (Exception ex){
                    logger.error("设置单元格公式时出现异常",ex);
                }
                context.getWriteSheetHolder().getSheet().setForceFormulaRecalculation(true);
            }
        }
    }

}
