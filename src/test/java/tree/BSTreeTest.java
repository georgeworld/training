package tree;

import com.georgeinfo.bstree.BSTree;
import org.junit.*;

/**
 * 二叉树测试类
 *
 * @author George <hi@georgeinfo.com>
 */
public class BSTreeTest {
    private BSTree tree;

    public BSTreeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }


    @Before
    public void setUp() {
        //组装树
        tree = new BSTree(4);
        tree.addNode(1).addNode(7).addNode(0).addNode(2).addNode(5).addNode(8).addNode(3).addNode(6).addNode(9);

    }

    @After
    public void tearDown() {
        System.out.println("---------------------------------");
    }


    // 先序递归打印二叉树
    @Test
    public void printTree() {
        tree.printTree(tree.getRootNode());
    }

    // 先序循环打印二叉树
    @Test
    public void printTreeByLoop() {
        tree.traverseByLoop(tree.getRootNode());
    }

}
