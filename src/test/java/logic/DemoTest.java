package logic;

import com.alibaba.chainlist.DoublyLinkedList;
import com.georgeinfo.logic.Demo;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    // 数组倒置
    @Test
    public void printArray() {
        int[] nums = new int[]{3, 2, 5, 1, 8};
        System.out.println("数组倒置前：" + Arrays.toString(nums));
        int[] newNums = Demo.arrayReverse(nums);
        System.out.println("数组倒置后：" + Arrays.toString(nums));
    }

    // 在循环中动态删除List中的元素
    @Test
    public void removeListElement() {
        List<AtomicInteger> list = new ArrayList<AtomicInteger>();
        list.add(new AtomicInteger(1));
        AtomicInteger ai = new AtomicInteger(2);
        list.add(ai);
        list.add(ai);
        list.add(new AtomicInteger(3));

        System.out.println("删除前的列表："+list);

        List<AtomicInteger> newList = Demo.removeListOnRunning(list,ai);

        System.out.println("删除后的列表："+newList);

    }

    // 在循环中动态删除List中的元素（iterator迭代器方式）
    @Test
    public void removeListElement2() {
        List<AtomicInteger> list = new ArrayList<AtomicInteger>();
        list.add(new AtomicInteger(1));
        AtomicInteger ai = new AtomicInteger(2);
        list.add(ai);
        list.add(ai);
        list.add(new AtomicInteger(3));

        System.out.println("删除前的列表："+list);

        List<AtomicInteger> newList = Demo.removeListOnRunningByIterator(list,ai);

        System.out.println("删除后的列表："+newList);

    }
}
