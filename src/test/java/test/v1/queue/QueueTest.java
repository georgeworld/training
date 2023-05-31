package test.v1.queue;

import com.georgeinfo.queue.QueueManager;
import org.junit.Test;

public class QueueTest {
    public static void main(String[] args){
        QueueManager qm = new QueueManager();
        qm.producesData();
        qm.consumeData();
    }
}
