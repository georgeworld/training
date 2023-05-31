package com.georgeinfo.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class QueueManager {
    private static final Logger LOG = LoggerFactory.getLogger(QueueManager.class);
    private ObjectMapper om = new ObjectMapper();
    private PriorityBlockingQueue<Person> queue = new PriorityBlockingQueue<>(10);

    public void producesData() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Person p = new Person(10 + i, "此人" + (10 + i) + "岁");
                    queue.offer(p, 1000, TimeUnit.MILLISECONDS);
                    LOG.info("添加了一个：" + p + "，停留半秒再添加下一个");
                    Thread.sleep(500);
                } catch (Exception ex) {
                    LOG.error("添加队列时异常", ex);
                }
            }
        }).start();
        Person p81 = new Person(81, "老李81岁了");
        queue.offer(p81);
        LOG.info("添加了一个：" + p81);
        Person p82 = new Person(82, "老王82岁了");
        queue.offer(p82);
        LOG.info("添加了一个：" + p82);
        Person p80 = new Person(80, "老赵80岁了");
        queue.offer(p80);
        LOG.info("添加了一个：" + p80);
    }

    public void consumeData() {
        new Thread(() -> {
            LOG.info("进入了消费者环节");
            for (int i = 0; i < 13; i++) {
                try {
                    Person p = queue.take();
                    String json = om.writeValueAsString(p);
                    LOG.info(json);
                } catch (Exception ex) {
                    LOG.error("发生异常", ex);
                }
            }
        }).start();
    }
}
