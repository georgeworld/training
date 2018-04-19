package com.alibaba.chainlist;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双向链表定义类
 *
 * @author George <hi@georgeinfo.com>
 */
public class DoublyLinkedList<T> {
    /**
     * 链表的第一个节点
     */
    private DLNode<T> headerNode;

    /**
     * 链表的最后一个节点（尾部节点，整个链表，
     * 其实是以headerNode为起点的一个数据结构，tailNode只是记录一下最后一个节点对象是谁，
     * 这个类成员变量，其实不是链表结构的一部分，只需要headerNode节点及节点的后续链接节点，就构成了
     * 一个完整的链表结构）
     */
    private DLNode<T> tailNode;
    /**
     * 链表当前节点的数量
     */
    private AtomicInteger num = new AtomicInteger(0);

    /**
     * 添加节点(在链表后面追加节点）
     *
     * @param data 在尾部新追加的节点值
     */
    public void addAfter(T data) {
        if (headerNode == null) {//第一次初始化链表数据
            headerNode = new DLNode<T>(data);
            tailNode = headerNode;
        } else {//向链表后端追加节点
            DLNode<T> newNode = new DLNode<T>(data);
            tailNode.setNextNode(newNode);
            newNode.setPreviousNode(tailNode);
            tailNode = newNode;
        }

        //节点数加1
        num.incrementAndGet();
    }

    public int getNodeSize() {
        return num.intValue();
    }

    /**
     * 从前端向尾部打印
     */
    public void printHeader2Tail() {
        DLNode<T> firstNode = this.headerNode;
        if (firstNode == null) {
            throw new CommonException("## 链表还没有插入任何数据，请先插入数据(1)。");
        }

        StringBuffer strb = new StringBuffer();
        DLNode<T> tempNode = firstNode;
        while (tempNode != null) {
            strb.append(tempNode.getValue()).append(",");
            tempNode = tempNode.getNextNode();
        }
        System.out.println(strb.substring(0, strb.length() - 1));
    }


    /**
     * 从尾部向前端打印
     */
    public void printTail2Header() {
        DLNode<T> lastNode = this.tailNode;
        if (lastNode == null) {
            throw new CommonException("## 链表还没有插入任何数据，请先插入数据(2)。");
        }

        StringBuffer strb = new StringBuffer();
        DLNode<T> tempNode = lastNode;
        while (tempNode != null) {
            strb.append(tempNode.getValue()).append(",");
            tempNode = tempNode.getPreviousNode();
        }
        System.out.println(strb.substring(0, strb.length() - 1));
    }


    /**
     * 链表节点反转
     */
    public void reverseList() {
        if (getNodeSize() < 2) {//没有节点，或者只有一个节点，链表反转没有意义
            throw new CommonException("## 链表中最少有2个节点，反转才有意义，请添加节点。");
        }
        //反转后新的头节点，就是尾节点，尾节点就成了头结点，所以，先用两个变量，把将来
        //反转后的新头尾节点记录下来。
        DLNode<T> newHeader = tailNode;
        DLNode<T> newTail = headerNode;

        //从当前链表的尾节点开始遍历
        DLNode<T> currentNode = tailNode;
        while (currentNode != null) {
            DLNode<T> oldNextNode = currentNode.getNextNode();
            DLNode<T> oldPreviousNode = currentNode.getPreviousNode();

            currentNode.setPreviousNode(oldNextNode);
            currentNode.setNextNode(oldPreviousNode);

            currentNode = oldPreviousNode;
        }

        //反转完成之后，设置链表的新的入口头结点
        this.headerNode = newHeader;
        this.tailNode = newTail;
    }
}
