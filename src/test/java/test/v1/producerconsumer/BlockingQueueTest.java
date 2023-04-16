package test.v1.producerconsumer;

import com.georgeinfo.designpattern.producerconsumer.ProductData;
import com.georgeinfo.designpattern.producerconsumer.blockingqueue.Consumer;
import com.georgeinfo.designpattern.producerconsumer.blockingqueue.Producer;
import org.junit.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 使用阻塞队列的方式，来实现生产者-消费者模式
 *
 * @author George <hi@georgeinfo.com>
 */
public class BlockingQueueTest {

    public BlockingQueueTest() {
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
    public void testProducerConsumer() throws InterruptedException {
        /** 全局数据缓存队列： 默認隊列容量，設置為10個，隊列滿的時候，再向裡面插入數據會拋出異常，
         * 如果使用boolean offer(E e, long timeout, TimeUnit unit)方法插入時，
         * 會按照offer方法傳入的等待時間來等待隊列，如果等待期間隊列有空間了，
         * 就成功插入，如果到了超時時間還沒有空間，就報錯。
         **/
        BlockingQueue<ProductData<String>> globalDataBufferQueue = new LinkedBlockingDeque<>(10);
        Producer p1 = new Producer(globalDataBufferQueue);
        Producer p2 = new Producer(globalDataBufferQueue);
        Producer p3 = new Producer(globalDataBufferQueue);
        Consumer c1 = new Consumer(globalDataBufferQueue);
        Consumer c2 = new Consumer(globalDataBufferQueue);
        Consumer c3 = new Consumer(globalDataBufferQueue);
        ExecutorService service = Executors.newCachedThreadPool();
        //将生产者线程加入线程池，并执行
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);

        //将消费者线程加入线程池，并执行
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
        Thread.sleep(10 * 1000);

        //停止生产
        p1.stop();
        p2.stop();
        p3.stop();
        Thread.sleep(3000);

        //关闭线程池，停止消费
        service.shutdown();
    }

}
