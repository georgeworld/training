package com.georgeinfo.proxy;

/**
 * @author George (GeorgeWorld@qq.com)
 */
public class DynamicProxyTestImpl implements DynamicProxyTest {
    public void doTest() {
        System.out.println("我是doTest()方法");
    }

    public String toString() {
        return "我是toString()方法";
    }

}
