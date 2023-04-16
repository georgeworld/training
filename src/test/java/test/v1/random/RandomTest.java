package test.v1.random;

import com.georgeinfo.random.RandomAlgorithm;
import org.junit.*;

import java.util.Arrays;

/**
 * 随机数测试类
 *
 * @author George <hi@georgeinfo.com>
 */
public class RandomTest {

    public RandomTest() {
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


    // 数组倒置
    @Test
    public void printArray() {
        int[] nums = new int[]{3, 2, 5, 1};
        double[] weights = new double[]{0.25, 0.25, 0.25, 0.25};

        System.out.println("候选数字：" + Arrays.toString(nums));
        System.out.println("概率值：" + Arrays.toString(weights));
        int result = RandomAlgorithm.getRandomByWeight(nums, weights);
        System.out.println("取到的随机数字是：" + result);
    }

}
