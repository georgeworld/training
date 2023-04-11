package com.georgeinfo.excel;

import java.util.ArrayList;
import java.util.List;

public class TestService {
    /**
     * @return
     */
    public String downloadKolList() {
        List<TestExport> data = buildExportKolList();
        String fileName = "数据清单";
        String sheet = "数据清单";
        TestExcelUtils.writeKolWithSheet(fileName, sheet, data, TestExport.class, "/Users/george/tempfile/test.xlsx");
        return "success";
    }

    private List<TestExport> buildExportKolList() {
        List<TestExport> list = new ArrayList<>();
        TestExport export = new TestExport();
        export.setKey("A");
        export.setValue("1");


        TestExport export1 = new TestExport();
        export1.setKey("B");
        export1.setValue("2");

        list.add(export);
        list.add(export1);
        return list;
    }

}