package zookeeper.lock;

import com.georgeinfo.zookeeper.lock.ConcurrentLock;
import com.georgeinfo.zookeeper.lock.LockException;
import com.georgeinfo.zookeeper.lock.LockInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author George (GeorgeWorld@qq.com)
 */
public class ZKLockTestMain {
    private static final Logger LOG = LoggerFactory.getLogger(ZKLockTestMain.class);
    private static volatile int n = 500;

    public static synchronized void doBusiness() {
        System.out.println(--n);
    }

    public static void main(String[] args) {
        //第一步：执行ZK初始化器，创建并发锁根节点
        LockInitializer li = new LockInitializer();
        try{
            li.begin();
            li.check();
        }catch (LockException ex){
            LOG.error("### Exception when initialize zookeeper env.",ex);
        }finally {
            li.close();
        }

        //第二步：测试并发锁
        Runnable runnable = new Runnable() {
            public void run() {
                ConcurrentLock lock = null;
                try {
                    //连接zk服务器，并设置抢占锁的名称
                    lock = new ConcurrentLock("go.georgeinfo.com:16009", "test1");

                    //开始抢占锁
                    lock.lock();

                    //干实际的业务
                    doBusiness();
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}