package com.georgeinfo.designpattern.producerconsumer.blockingqueue;

import com.georgeinfo.designpattern.producerconsumer.ProductData;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 *
 * @author George (GeorgeWorld@qq.com)
 */
public class Producer implements Runnable {
    /**
     * 退出线程的标志，使用关键字volatile来修饰，保证这个变量的值在线程内存区和全局内存区内的数据一致
     */
    private volatile boolean runningFlag = true;
    /**
     * 数据缓冲容器（队列），从外部调用方传递过来，保证整个生产-消费过程中，只有一个数据缓冲队列
     */
    private BlockingQueue<ProductData<String>> dataBufferQueue;
    /**
     * 产品编号记录器，其实是用来生成不同产品的一个素材因子而已，
     * 因为测试产品，生产出来的需要每个都不同，所以需要一个属于类的全局递增变量来作为产品差异因子。
     * 同时，这个变量也作为记录生产的产品总数记录器。
     */
    private static AtomicInteger productIndex = new AtomicInteger();
    /**
     * 线程休眠市场（微秒）
     */
    private static final int SLEEPTIME = 1000;


    public Producer(BlockingQueue<ProductData<String>> dataBufferQueue) {
        this.dataBufferQueue = dataBufferQueue;
    }

    @Override
    public void run() {
        ProductData<String> product = null;
        Random r = new Random();
        System.out.println("### 生产线【" + Thread.currentThread().getId() + "】开始生产产品 ###");
        try {
            while (runningFlag == true) {//一直生产，除非收到停止生产的信号
                //为了避免过快地打印，所以人为地休眠一段时间（0-1秒之内的随机时间）
                Thread.sleep(r.nextInt(SLEEPTIME));

                //生产实际的产品
                product = new ProductData<String>("产品：" + productIndex.incrementAndGet());

                //尝试将生产出的产品加入缓冲区队列，如果2秒内仍未加入队列，则视为加入队列失败。
                //这个是关键，当队列满的时候，选择等待一段时间，而不是直接取消插入队列的行为。
                if (!dataBufferQueue.offer(product, 2, TimeUnit.SECONDS)) {
                    System.err.println(product + " 加入队列失败");
                } else {
                    System.out.println(product + " 加入缓冲区队列");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

            //如果遇到异常，则中断整个线程
            Thread.currentThread().interrupt();
        }

    }

    public void stop() {
        runningFlag = false;
    }
}