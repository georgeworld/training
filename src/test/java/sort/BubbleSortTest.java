package sort;

import com.georgeinfo.algorithm.sort.BubbleSort;
import com.georgeinfo.bstree.BSTree;
import org.junit.*;

import java.util.Arrays;

/**
 * 冒泡排序算法测试
 *
 * @author George <hi@georgeinfo.com>
 */
public class BubbleSortTest {
    private int[] arrayData;

    public BubbleSortTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }


    @Before
    public void setUp() {
        //组装待排序数组
        arrayData = new int[]{6, 5, 2, 3, 1, 2, 5, 6, 2};
        System.out.println("### 排序前：" + Arrays.toString(arrayData));
    }

    @After
    public void tearDown() {
        System.out.println("---------------------------------");
    }


    // 冒泡排序
    @Test
    public void bubbleSort() {
        BubbleSort bs = new BubbleSort(arrayData);
        //常规冒泡排序
        bs.sort();

        System.out.println("### 排序后：" + Arrays.toString(arrayData));
    }

}
