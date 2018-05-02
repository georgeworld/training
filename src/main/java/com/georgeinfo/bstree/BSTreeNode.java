package com.georgeinfo.bstree;

/**
 * 二叉树节点定义
 *
 * @author George<GeorgeWorld @ qq.com>
 */
public class BSTreeNode {
    private BSTreeNode leftNode;
    private BSTreeNode rightNode;
    private int value;

    public BSTreeNode() {
    }

    public BSTreeNode(int value) {
        this.value = value;
    }

    public BSTreeNode(BSTreeNode leftNode, BSTreeNode rightNode, int value) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.value = value;
    }

    public BSTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BSTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public BSTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BSTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BSTreeNode{" +
                "leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                ", value=" + value +
                '}';
    }
}
