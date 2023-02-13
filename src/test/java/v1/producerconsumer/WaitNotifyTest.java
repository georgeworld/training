package v1.producerconsumer;

import com.georgeinfo.designpattern.producerconsumer.ProductData;
import com.georgeinfo.designpattern.producerconsumer.waitnotify.Consumer;
import com.georgeinfo.designpattern.producerconsumer.waitnotify.Producer;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Object累的wait 和 notifyAll方法，来使用普通List代替阻塞队列，实现生产者-消费者模式
 *
 * @author George <hi@georgeinfo.com>
 */
public class WaitNotifyTest {

    public WaitNotifyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }


    @Before
    public void setUp() {
        System.out.println("-- 开始了 -------------------------------");
    }

    @After
    public void tearDown() {
        System.out.println("-- 结束了 -------------------------------");
    }


    // 测试生产者-消费者模式
    @Test
    public void testProducerConsumer() {
        List<ProductData<String>> queue = new ArrayList<ProductData<String>>();
        int length = 10;
        Producer p1 = new Producer(queue, length);
        Producer p2 = new Producer(queue, length);
        Producer p3 = new Producer(queue, length);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
    }

}
