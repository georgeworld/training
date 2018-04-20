package logic;

import com.alibaba.chainlist.DoublyLinkedList;
import com.georgeinfo.logic.Demo;
import org.junit.*;

/**
 * 常用代码技巧的测试
 *
 * @author George <hi@georgeinfo.com>
 */
public class DemoTest {

    public DemoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        System.out.println("---------------------------------");
    }


    // 打印有规律的数字
    @Test
    public void printNumber() {
        Demo.printStar(5);
    }
}
