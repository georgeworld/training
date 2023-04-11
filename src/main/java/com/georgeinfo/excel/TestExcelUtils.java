package com.georgeinfo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.FileOutputStream;
import java.util.List;

public class TestExcelUtils<T> {

    public static <T> void writeKolWithSheet(String fileName, String sheet, List<T> data, Class<T> obj, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);

            //设置响应头
            ExcelWriter excelWriter = EasyExcel.write(fos).build();
            WriteSheet writeSheet = writeKolSelectedSheet(obj, 0, sheet);
            excelWriter.write(data, writeSheet);
            excelWriter.finish();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建即将导出的sheet页（注册自定义的处理器）
     *
     * @param head      导出的表头信息和配置
     * @param sheetNo   sheet索引
     * @param sheetName sheet名称
     * @param <T>       泛型
     * @return sheet页
     */
    public static <T> WriteSheet writeKolSelectedSheet(Class<T> head, Integer sheetNo, String sheetName) {
        return EasyExcel.writerSheet(sheetNo, sheetName)
                .head(head)
                .registerWriteHandler(new SelectedCellWriteHandler())
                .build();
    }
}