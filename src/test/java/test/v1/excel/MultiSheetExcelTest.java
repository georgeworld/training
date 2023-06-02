package test.v1.excel;

import com.georgeinfo.excel.MultiSheetExcelBuilder;
import com.georgeinfo.excel.SheetInfo;
import com.georgeinfo.excel.SheetInfoBuilder;
import com.georgeinfo.excel.handler.ContentCellHandler;
import com.georgeinfo.excel.handler.NumberColumnSheetHandler;
import com.georgeinfo.excel.handler.DropDownMenuHandler;
import com.georgeinfo.excel.handler.HeadStyleHandler;
import com.georgeinfo.excel.template.DemoTemplate;
import com.georgeinfo.excel.template.MainTemplate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class MultiSheetExcelTest {
    private static final Logger logger = LoggerFactory.getLogger(MultiSheetExcelTest.class);

    @Test
    public void testEasyExcel() {
        String filePath = System.getProperty("user.dir") + File.separator + "excel-template.xlsx";

        List<SheetInfo> sheetList = new ArrayList();
        sheetList.add(SheetInfoBuilder.setSheetName("第一个sheet")
                .setTemplateClass(MainTemplate.class)
                .setHandlerList(Arrays.asList(new HeadStyleHandler(),
                        new DropDownMenuHandler(getDropDownData()),
                        new ContentCellHandler()))
                .setData(getFirstSheetData())
                .build());
        sheetList.add(SheetInfoBuilder.setSheetName("第二个sheet")
                .setTemplateClass(DemoTemplate.class)
                .setHandlerList(Arrays.asList(new NumberColumnSheetHandler()))
                .setData(getSencondSheetData())
                .build());

        boolean result = MultiSheetExcelBuilder
                .setFilePath(filePath)
                .setSheetList(sheetList).build();
        logger.error("\n生成excel的结果：{}，生成的文件存放在：{}", result, filePath);
    }

    private LinkedHashMap<Integer, List<String>> getDropDownData() {
        LinkedHashMap<Integer, List<String>> map = new LinkedHashMap<>();
        map.put(3, Arrays.asList("上海", "北京", "广州"));
        return map;
    }


    private List getFirstSheetData() {
        List list = new ArrayList();
        list.add(new MainTemplate("刘", "德华", new BigDecimal("2099899.89"), "香港","2023-06-02 23:48:01"));
        list.add(new MainTemplate("张", "学友", new BigDecimal("998.1"), "香港","2023-06-02 23:50:02"));
        list.add(new MainTemplate("黎", "明", new BigDecimal("19886.9"), "香港","2023-06-02 23:50:03"));
        list.add(new MainTemplate("郭", "富城", new BigDecimal("3222900"), "香港","2023-06-02 23:50:04"));
        return list;
    }

    private List getSencondSheetData() {
        List list = new ArrayList();
        list.add(new DemoTemplate(1, "歌星"));
        list.add(new DemoTemplate(2, "影星"));
        list.add(new DemoTemplate(3, "综艺明星"));
        return list;
    }

}

