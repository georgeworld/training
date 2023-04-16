package test.v1.alibaba;

import com.alibaba.chainlist.DoublyLinkedList;
import org.junit.*;

/**
 * 常规双向链表反转测试
 *
 * @author George <hi@georgeinfo.com>
 */
public class DLLTest {
    private DoublyLinkedList<Integer> dllist;

    public DLLTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private void printBeforeReversing() {
        System.out.println("### 链表节点数量{" + dllist.getNodeSize() + "}，反转之前打印结果如下：");
        System.out.println("--- 从前端向后端打印：");
        dllist.printHeader2Tail();
        System.out.println("--- 从后端向前端打印：");
        dllist.printTail2Header();
    }

    @Before
    public void setUp() {
        //为链表插入初始化数据
        dllist = new DoublyLinkedList<Integer>();
        for (int i = 1; i <= 6; i++) {
            dllist.addAfter(i);
        }

        //链表反转前，打印正常顺序的数据
        printBeforeReversing();
        System.out.println("*********************************");
    }

    @After
    public void tearDown() {
        System.out.println("---------------------------------");
    }


    // 链表反转
    @Test
    public void reverseChainList() {
        System.out.println("### 链表反转之后，打印的结果如下：");
        dllist.reverseList();
        System.out.println("--- 从前端向后端打印：");
        dllist.printHeader2Tail();
        System.out.println("--- 从后端向前端打印：");
        dllist.printTail2Header();

    }
}
