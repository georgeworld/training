package com.georgeinfo.designpattern.producerconsumer.signal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author George (GeorgeWorld@qq.com)
 */
public class LockAndCondition {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition empty = lock.newCondition();
    public static Condition full = lock.newCondition();
}
