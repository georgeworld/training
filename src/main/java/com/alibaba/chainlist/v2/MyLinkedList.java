package com.alibaba.chainlist.v2;

import java.util.concurrent.atomic.AtomicInteger;

public class MyLinkedList {
    /** 第一个节点 */
    private MyNode<String> firstNode;
    /** 最后一个节点 */
    private MyNode<String> tailNode;
    /** 链表节点数量 */
    private AtomicInteger nodeNum = new AtomicInteger(0);

    /** 向链表添加一个节点 */
    public void addNode(String value){
        if(firstNode == null){
            //构建第一个节点
            this.firstNode = new MyNode<>(value);
            this.tailNode = this.firstNode;
        }else{
            //向链表尾部添加一个节点
            MyNode<String> node = new MyNode<String>(value);
            this.tailNode.setNextNode(node);
            node.setPreNode(this.tailNode);
            this.tailNode = node;
        }
        this.nodeNum.incrementAndGet();
    }

    /**
     * 构建一个链表
     */
    public void buildList(String... args) {
        for (int i = 0; i < args.length; i++) {
            this.addNode(args[i]);
        }
    }

    /**
     * 构建一个链表
     */
    public void buildList2(String... args) {
        this.firstNode = new MyNode<>(args[0]);
        this.nodeNum.incrementAndGet();

        MyNode<String> currentNode = null;
        for (int i = 1; i < args.length; i++) {
            MyNode<String> newNode = new MyNode<String>(args[i]);
            if(i == 1){
                newNode.setPreNode(this.firstNode);
                this.firstNode.setNextNode(newNode);
                currentNode = newNode;
            }else{
                newNode.setPreNode(currentNode);
                currentNode.setNextNode(newNode);
                currentNode = newNode;
            }
            this.nodeNum.incrementAndGet();
        }
    }

    /**
     * 递归遍历列表
     */
    public void traversalList(MyNode<String> node) {
        if (node != null) {
            System.out.println(node.getValue());
            if (node.getNextNode() != null) {
                traversalList(node.getNextNode());
            }
        }
    }

    /**
     * 循环遍历列表
     */
    public void traversalList2() {
        MyNode<String> tempNode = this.firstNode;
        while (tempNode != null){
            System.out.println(tempNode.getValue());
            tempNode = tempNode.getNextNode();
        }
    }

    public MyNode<String> getFirstNode() {
        return firstNode;
    }

    public MyNode<String> getTailNode() {
        return tailNode;
    }

    public int getNodeNum() {
        return nodeNum.intValue();
    }
}
