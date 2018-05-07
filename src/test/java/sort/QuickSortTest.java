package sort;

import com.georgeinfo.algorithm.sort.BubbleSort;
import com.georgeinfo.algorithm.sort.QuickSort;
import org.junit.*;

import java.util.Arrays;

/**
 * 快速排序算法测试
 *
 * @author George <hi@georgeinfo.com>
 */
public class QuickSortTest {
    private int[] arrayData;

    public QuickSortTest() {
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
        arrayData = new int[]{6, 5, 8, 3, 1, 2, 9, 7, 4};
        System.out.println("### 排序前：" + Arrays.toString(arrayData));
    }

    @After
    public void tearDown() {
        System.out.println("---------------------------------");
    }


    // 快速排序
    @Test
    public void bubbleSort() {
        //快速排序
        QuickSort.quickSort(arrayData);

        System.out.println("### 排序后：" + Arrays.toString(arrayData));
    }
}
