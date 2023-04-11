package v1.excel;

import com.georgeinfo.excel.TestService;
import org.junit.Test;

public class EasyExcelTest {
    @Test
    public void testExcel() {
        TestService testService = new TestService();
        testService.downloadKolList();
    }

}
