package com.alibaba.chainlist.v2;

public class MyNode<T> {
    /**
     * 上个节点
     */
    private MyNode<T> preNode;
    /**
     * 下个节点
     */
    private MyNode<T> nextNode;
    /**
     * 节点附带的值
     */
    private T value;

    public MyNode() {
    }

    public MyNode(T value) {
        this.value = value;
    }

    public MyNode getPreNode() {
        return preNode;
    }

    public void setPreNode(MyNode preNode) {
        this.preNode = preNode;
    }

    public MyNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(MyNode nextNode) {
        this.nextNode = nextNode;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
