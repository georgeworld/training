package com.georgeinfo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程池配置工具
 */
public class ThreadUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadUtils.class);
    /**
     * 核心线程数
     */
    private static final int CORE_THREAD_NUM = Runtime.getRuntime().availableProcessors() + 1;
    /**
     * 最大线程数
     */
    private static final int MAX_THREAD_NUM = CORE_THREAD_NUM * 2;
    /**
     * 线程等待队列大小
     */
    private static final int QUEUE_LENGTH = MAX_THREAD_NUM * 10;

    public static ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(
                CORE_THREAD_NUM,
                MAX_THREAD_NUM,
                50,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_LENGTH),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        while (true) {
                            try {
                                if (!executor.getQueue().offer(r, 5, TimeUnit.SECONDS)) break;
                            } catch (InterruptedException e) {
                                LOG.error("向线程等待队列里添加任务时，出现异常", e);
                            }
                            break;
                        }
                    }
                }
        );
    }

    public static ExecutorService getExecutor() {
        return new ThreadPoolExecutor(CORE_THREAD_NUM,
                MAX_THREAD_NUM,
                50,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_LENGTH),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        while (true) {
                            try {
                                if (!executor.getQueue().offer(r, 5, TimeUnit.SECONDS)) break;
                            } catch (InterruptedException e) {
                                LOG.error("向线程等待队列里添加任务时，出现异常", e);
                            }
                            break;
                        }
                    }
                });
    }
}
