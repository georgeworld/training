package test.v1.bio;

import com.georgeinfo.io.bio.SocketClient;
import org.junit.*;

/**
 * 阻塞式IO Socket客户端测试
 *
 * @author George <hi@georgeinfo.com>
 */
public class BIOClientTest {

    public BIOClientTest() {
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


    // 启动客户端
    @Test
    public void startupClient() throws Exception {
        System.out.println("## 现在开始启动客户端……");
        SocketClient client = new SocketClient();
        client.startup();
    }
}
