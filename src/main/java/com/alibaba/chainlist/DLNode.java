package com.alibaba.chainlist;

/**
 * 双向链表节点类
 *
 * @author George <hi@georgeinfo.com>
 */
public class DLNode<T> {

    /**
     * 前节点
     */
    private DLNode<T> previousNode = null;

    /**
     * 后节点
     */
    private DLNode<T> nextNode = null;

    /**
     * 节点绑定的值
     */
    private T value;

    public DLNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DLNode<T> getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(DLNode<T> previousNode) {
        this.previousNode = previousNode;
    }

    public DLNode<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(DLNode<T> nextNode) {
        this.nextNode = nextNode;
    }
}
