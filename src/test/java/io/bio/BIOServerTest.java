package io.bio;

import com.georgeinfo.io.bio.SocketClient;
import com.georgeinfo.io.bio.SocketServer;
import com.georgeinfo.logic.Demo;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞式IO Socket服务端测试
 *
 * @author George <hi@georgeinfo.com>
 */
public class BIOServerTest {

    public BIOServerTest() {
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


    // 启动服务端
    @Test
    public void startupServer() throws Exception {
        //启动服务端
        SocketServer server = new SocketServer();
        try {
            server.startup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
