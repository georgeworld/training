package com.georgeinfo.zookeeper.lock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 并发锁初始化器监视器
 *
 * @author George (GeorgeWorld@qq.com)
 */
public class InitializerWatcher implements Watcher {
    private static final Logger LOG = LoggerFactory.getLogger(InitializerWatcher.class);

    /**
     * 当收到节点监控事件后的处理
     */
    @Override
    public void process(WatchedEvent event) {
        //啥都不干，只是打印一下是哪个节点触发了哪个事件
        LOG.debug("event:" + event);
    }
}
