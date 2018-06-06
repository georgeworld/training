package com.georgeinfo.designpattern.producerconsumer.waitnotify;

import com.georgeinfo.designpattern.producerconsumer.ProductData;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 *
 * @author George (GeorgeWorld@qq.com)
 */
public class Producer implements Runnable {
    /**
     * 全局数据缓冲区队列（使用列表代替）
     */
    private List<ProductData<String>> dataBufferQueue;
    /**
     * 队列容量长度
     */
    private int length;
    /**
     * 生产产品时用于区分不同产品的标志因子
     */
    private static final AtomicInteger productIndex = new AtomicInteger();

    public Producer(List<ProductData<String>> dataBufferQueue, int length) {
        this.dataBufferQueue = dataBufferQueue;
        this.length = length;
    }

    @Override
    public void run() {
        try {
            System.out.println("### 线程【" + Thread.currentThread().getId() + "】开始生产数据 ###");
            while (true) {
                //当线程中断时，退出循环
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }

                ProductData<String> product = new ProductData<String>("产品：" + productIndex.incrementAndGet());

                System.out.println("产品【" + product.toString() + "】被生产线【" + Thread.currentThread().getId() + "】生产出来了");
                synchronized (dataBufferQueue) {//获得缓存队列的锁
                    if (dataBufferQueue.size() >= length) {//生产时，如果缓存队列满了，则暂停生产，通知消费者赶快取数据
                        //释放当前线程对缓存队列的锁，让当前线程进入阻塞等待中
                        dataBufferQueue.wait();
                    } else {
                        dataBufferQueue.add(product);
                    }
                }
                Thread.sleep(1000);
            }

            //通知所有其他等待队列锁的线程（消费者线程），开始激活线程消费数据
            dataBufferQueue.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}