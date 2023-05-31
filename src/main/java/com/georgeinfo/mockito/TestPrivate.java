package com.georgeinfo.mockito;

import javax.annotation.Resource;

public class TestPrivate {
    private String text;
    @Resource
    private TestSpringBean testSpringBean;

    private String hello(String name, int age) {
        this.text = "姓名：" + name + ",年龄:" + age;
//        System.out.println(text);
        return text;
    }

    public String test() {
        System.out.println(text);
        return "ok";
    }
}
