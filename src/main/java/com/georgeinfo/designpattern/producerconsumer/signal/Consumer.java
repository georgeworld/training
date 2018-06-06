package com.georgeinfo.designpattern.producerconsumer.signal;

import com.georgeinfo.designpattern.producerconsumer.ProductData;

import java.util.List;

/**
 * @author George (GeorgeWorld@qq.com)
 */
public class Consumer implements Runnable {
    private List<ProductData<String>> queue;

    public Consumer(List<ProductData<String>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                ProductData<String> data = null;
                LockAndCondition.lock.lock();
                if (queue.size() == 0) {
                    LockAndCondition.full.signalAll();
                    LockAndCondition.empty.await();
                }
                Thread.sleep(1000);
                data = queue.remove(0);
                LockAndCondition.lock.unlock();
                System.out.println("消费者ID:" + Thread.currentThread().getId() + " 消费了:" + data.getProduct());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}