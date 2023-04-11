package com.interview;

public class GNode<T> {
    private T value;
    private GNode<T> leftNode;
    private GNode<T> rightNode;
    private GNode<T> parentNode;

    public GNode() {
    }

    public GNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public GNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(GNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public GNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(GNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    public GNode<T> getParentNode() {
        return parentNode;
    }

    public void setParentNode(GNode<T> parentNode) {
        this.parentNode = parentNode;
    }
}
