package com.georgeinfo.designpattern.producerconsumer.blockingqueue;

import com.georgeinfo.designpattern.producerconsumer.ProductData;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 *
 * @author George (GeorgeWorld@qq.com)
 */
public class Consumer implements Runnable {
    /**
     * 数据缓冲容器（队列），从外部调用方传递过来，保证整个生产-消费过程中，只有一个数据缓冲队列
     */
    private BlockingQueue<ProductData<String>> dataBufferQueue;
    /**
     * 线程休眠市场（微秒）
     */
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<ProductData<String>> dataBufferQueue) {
        this.dataBufferQueue = dataBufferQueue;
    }

    @Override
    public void run() {
        System.out.println("### 消费者线程【" + Thread.currentThread().getId() + "】开始消费数据");
        Random r = new Random();
        try {
            while (true) {
                //从全局缓冲容器队列中，获取一个数据，
                //使用take()方法是关键，表示当队列为空，里面没有数据的时候，take()取值会一直等待，知道队列中有数据为止。
                ProductData<String> product = dataBufferQueue.take();
                if (product != null) {
                    String data = product.getProduct();
                    System.out.println("消费了产品：" + data);

                    //为了避免过快地打印，所以人为地休眠一段时间（0-1秒之内的随机时间）
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}