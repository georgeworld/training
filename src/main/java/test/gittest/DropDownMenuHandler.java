package test.gittest;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;

public class DropDownMenuHandler implements SheetWriteHandler {
    private static final Logger logger = LoggerFactory.getLogger(DropDownMenuHandler.class);
    /**
     * 设置下拉框的起始行，默认为第二行，可通过后续传参改变
     */
    private final static int firstRow = 1;
    /**
     * 设置下拉框得结束行行，默认为最后一行
     */
    private final static int lastRow = 0x10000;

    /**
     * Integer 是需要设置下拉框得列
     * List<String> 是该列下拉框得值
     */
    private LinkedHashMap<Integer, List<String>> fieldValues;

    /**
     * 完成赋值，上面得起始列，结束列也可以如此实现
     */
    public DropDownMenuHandler(LinkedHashMap<Integer, List<String>> fieldValues) {
        this.fieldValues = fieldValues;
    }

    /**
     * 创建sheet 操作
     */
    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        //获取sheet页
        Sheet sheet = writeSheetHolder.getSheet();
        DataValidationHelper helper = sheet.getDataValidationHelper();
        fieldValues.forEach((k, v) -> {
            //设置下拉框得起始行，结束行，起始列，结束列
            CellRangeAddressList list = new CellRangeAddressList(firstRow, lastRow, k, k);
            //将数字转化为 A-Z 格式
            String excelLine = getExcelLinke(k);
            //重新定义一个隐藏得sheet名称为 xxx(自己爱取啥名就取啥名）+ k
            String sheetName = "DropDownMenu" + k;
            //创建sheet，突破下拉框255得限制
            Workbook workbook = writeWorkbookHolder.getWorkbook();
            Sheet workbookSheet = workbook.createSheet(sheetName);
            for (int i = 0; i < v.size(); i++) {
                //row 表示开始得行数，cell表示开始得列数，数据固定写到第一列
                workbookSheet.createRow(i).createCell(0).setCellValue(v.get(i));
            }
            Name name = workbook.createName();
            name.setNameName(sheetName);
            //下拉框设置
            String beginColumn = "A";
            String endColumn = "A";
            String refers = sheetName + "!$" + beginColumn + "$1:$" + endColumn + "$" + (v.size() + 1);
            name.setRefersToFormula(refers);
            //设置为隐藏
            int index = workbook.getSheetIndex(sheetName);
//            if (!workbook.isSheetHidden(index)) {
//                workbook.setSheetHidden(index, true);
//            }
            DataValidationConstraint constraint = helper.createFormulaListConstraint(refers);
            DataValidation dataValidation = helper.createValidation(constraint, list);
            //适配 office ，配置很多得话，office会把初始话表格
            if (dataValidation instanceof XSSFDataValidation) {
                dataValidation.setSuppressDropDownArrow(true);
                dataValidation.setShowErrorBox(true);
            } else {
                dataValidation.setSuppressDropDownArrow(false);
            }
            sheet.addValidationData(dataValidation);
        });
        logger.info("sheet写入完成！");
    }

    /**
     * 返回excel列标A-Z-AA-ZZ
     */
    private String getExcelLinke(int num) {
        String line = "";
        int first = num / 26;
        int second = num % 26;
        if (first > 0) {
            line = (char) ('A' + first - 1) + "";
        }
        line += (char) ('A' + second) + "";
        return line;
    }

}

