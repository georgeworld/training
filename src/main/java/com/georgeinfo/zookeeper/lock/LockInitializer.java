package com.georgeinfo.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;

import java.io.IOException;

/**
 * 分布式锁初始化器，最主要的作用，就是检查基础根目录是否已经创建了，如果没创建，就创建之
 *
 * @author George (GeorgeWorld@qq.com)
 */
public class LockInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(LockInitializer.class);
    /**
     * zookeeper连接字符串
     */
    public static final String connectString = "go.georgeinfo.com:16009";
    /**
     * 连接超时时间
     */
    public static final int sessionTimeout = 30000;
    private ZooKeeper zk = null;
    /**
     * 分布式锁根节点
     */
    public static String ROOT_LOCK = "/locks-root";

    public void begin() throws LockException {
        try {
            // 连接zookeeper
            zk = new ZooKeeper(connectString, sessionTimeout, new InitializerWatcher());
        } catch (IOException e) {
            LOG.error("### Exception when connecting zookeeper server...", e);
            throw new LockException("Exception when checking zookeeper env...", e);
        }

    }

    public boolean check() throws LockException {
        if (zk == null) {
            throw new LockException("### Zookeeper connection is null.");
        }

        try {
            //检查根节点是否存在
            Stat stat = zk.exists(ROOT_LOCK, false);
            if (stat == null) {
                //如果根节点尚未创建，则创建之
                zk.create(ROOT_LOCK, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } else {
                LOG.debug("### The root node has already existed......");
            }

            return true;
        } catch (InterruptedException | KeeperException e) {
            LOG.error("### Exception when checking zookeeper env...", e);
            throw new LockException("Exception when checking zookeeper env...", e);
        }
    }


    public void close() {
        if (zk != null) {
            try {
                LOG.debug("### Close zookeeper connection...");
                zk.close();
            } catch (InterruptedException e) {
                LOG.error("### InterruptedException when close zookeeper connection.", e);
            }
        }
    }
}
