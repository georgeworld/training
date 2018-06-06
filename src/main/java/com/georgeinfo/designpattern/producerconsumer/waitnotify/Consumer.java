package com.georgeinfo.designpattern.producerconsumer.waitnotify;

import com.georgeinfo.designpattern.producerconsumer.ProductData;

import java.util.List;

/**
 * 消费者
 *
 * @author George (GeorgeWorld@qq.com)
 */
public class Consumer implements Runnable {
    private List<ProductData<String>> dataBufferQueue;

    public Consumer(List<ProductData<String>> dataBufferQueue) {
        this.dataBufferQueue = dataBufferQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                ProductData<String> data = null;
                synchronized (dataBufferQueue) {
                    if (dataBufferQueue.size() == 0) {
                        //当 队列 为空的时候，使当前消费者线程阻塞（处于等待中）
                        dataBufferQueue.wait();

                        //示意其他等待队列锁的线程（生产者线程），激活并抓紧生产数据
                        dataBufferQueue.notifyAll();
                    } else {

                        //获得头部一个元素，并移除，如果没有数据，则抛出异常
                        data = dataBufferQueue.remove(0);
                    }
                }

                System.out.println("线程【" + Thread.currentThread().getId() + "】 消费了产品:" + data.getProduct());
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}