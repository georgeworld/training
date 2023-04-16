package test.v1.common;

import com.georgeinfo.proxy.DynamicProxyTest;
import com.georgeinfo.proxy.DynamicProxyTestImpl;
import org.junit.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通用临时测试类
 *
 * @author George <hi@georgeinfo.com>
 */
public class CommonTest {

    public CommonTest() {
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


    // 测试
    @Test
    public void doUnitTest() {

        DynamicProxyTestImpl realObject = new DynamicProxyTestImpl();
        Class[] interfaces = DynamicProxyTestImpl.class.getInterfaces();
        DynamicProxyTest proxyObject = (DynamicProxyTest)Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(),
                interfaces,
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("##进入代理方法了……");
                Object o = method.invoke(realObject, args);
                System.out.println("##执行完被代理的方法了！！！！！！");
                return o;
            }
        });

        proxyObject.doTest();
    }
}
