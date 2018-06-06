package com.georgeinfo.designpattern.producerconsumer.signal;

import com.georgeinfo.designpattern.producerconsumer.ProductData;

import java.util.List;
import java.util.Random;

/**
 * @author George (GeorgeWorld@qq.com)
 */
public class Producter implements Runnable {
    private List<ProductData<String>> queue;
    private int len;

    public Producter(List<ProductData<String>> queue, int len) {
        this.queue = queue;
        this.len = len;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                Random r = new Random();
                String proData = "产品：" + r.nextInt(500);
                ProductData<String> data = new ProductData<String>(proData);
                LockAndCondition.lock.lock();
                if (queue.size() >= len) {
                    LockAndCondition.empty.signalAll();
                    LockAndCondition.full.await();
                }
                Thread.sleep(1000);
                queue.add(data);
                LockAndCondition.lock.unlock();
                System.out.println("生产者ID:" + Thread.currentThread().getId() + " 生产了:" + data.getProduct());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}