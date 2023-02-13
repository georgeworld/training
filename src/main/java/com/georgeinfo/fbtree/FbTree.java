package com.georgeinfo.fbtree;

public class FbTree {

    /**
     * 指定高度，构建满二叉树
     */
    public FbTreeNode<Integer> generateTree(int height) {
        //先创建一个根节点
        FbTreeNode<Integer> root = new FbTreeNode<>(50);
        if (height == 1) {
            return root;
        }
        for (int i = 1; i < height; i++) {
            addNode(root);
        }
        return root;
    }

    private void addNode(FbTreeNode<Integer> node) {
        if (node == null) {
            return;
        }
        FbTreeNode<Integer> left = node.getLeftNode();
        FbTreeNode<Integer> right = node.getRightNode();
        //如果当前节点左右孩子都为空，则为它添加左右孩子
        if (left == null && right == null) {
            FbTreeNode<Integer> leftChild = new FbTreeNode<>(node.getValue() - 1);
            FbTreeNode<Integer> rightChild = new FbTreeNode<>(node.getValue() - 1);
            node.setLeftNode(leftChild);
            node.setRightNode(rightChild);
        }
        //递归添加子节点
        addNode(left);
        addNode(right);
    }

    /**
     * 遍历打印树
     */
    public void printTree(FbTreeNode<Integer> node) {
        if (node != null) {
            System.out.println(node.getValue());
        }
        FbTreeNode<Integer> left = node.getLeftNode();
        FbTreeNode<Integer> right = node.getRightNode();
        if (left != null && right != null) {
            //递归遍历
            printTree(left);
            printTree(right);
        }
    }
}
