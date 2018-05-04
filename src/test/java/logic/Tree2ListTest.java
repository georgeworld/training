package logic;

import com.georgeinfo.logic.BST2DoublyLinkedList;
import com.georgeinfo.logic.Demo;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 二叉树转双向链表的测试
 *
 * @author George <hi@georgeinfo.com>
 */
public class Tree2ListTest {

    public Tree2ListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        System.out.println("---------------------------------");
    }

    // 二叉树转双向链表
    @Test
    public void doConvert() {
        BST2DoublyLinkedList
                .begin()
                .buildTree(new int[]{4, 1, 7, 0, 2, 5, 8, 3, 6, 9})
                .printTree()
                .convertTree2List()
                .printList();
    }
}
