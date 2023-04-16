package test.v1.tree.btree2;

import com.georgeinfo.fbtree.FbTree;
import com.georgeinfo.fbtree.FbTreeNode;
import org.junit.Test;

public class BTreeTest2 {
    @Test
    public void testTree() {
        FbTree tree = new FbTree();
        //指定高度构建树
        FbTreeNode<Integer> root = tree.generateTree(3);
        //打印遍历树
        tree.printTree(root);
    }
}
