package com.georgeinfo.fbtree;

public class FbTreeNode<T> {
    /**
     * 节点值
     */
    private T value;
    /**
     * 上级节点
     */
    private FbTreeNode<T> parentNode;
    /**
     * 左节点
     */
    private FbTreeNode<T> leftNode;
    /**
     * 右节点
     */
    private FbTreeNode<T> rightNode;

    public FbTreeNode() {
    }

    public FbTreeNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public FbTreeNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(FbTreeNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public FbTreeNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(FbTreeNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    public FbTreeNode<T> getParentNode() {
        return parentNode;
    }

    public void setParentNode(FbTreeNode<T> parentNode) {
        this.parentNode = parentNode;
    }
}
