package test.v1.excel;

import com.alibaba.excel.EasyExcel;
import com.georgeinfo.excel.template.MainTemplate;
import com.georgeinfo.utils.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadExcelUT {
    private static final Logger log = LoggerFactory.getLogger(ReadExcelUT.class);
    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link MainTemplate}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void simpleRead() {
        String fileName = "/Users/george/GeorgeProjects/study/training/training/excel-template.xlsx";
        EasyExcel.read(fileName, MainTemplate.class, new DemoDataListener()).sheet().doRead();
    }
}
