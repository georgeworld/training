package com.georgeinfo.logic;

import com.georgeinfo.bstree.BSTree;
import com.georgeinfo.bstree.BSTreeNode;
import com.georgeinfo.bstree.NodeCallback;
import com.util.CommonException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树转双向链表
 *
 * @author George<GeorgeWorld               @               qq.com>
 */
public class BST2DoublyLinkedList {
    private BSTree tree;
    private BSTreeNode doublyList;

    private BST2DoublyLinkedList() {
    }

    public static BST2DoublyLinkedList begin() {
        return new BST2DoublyLinkedList();
    }

    /**
     * 构建二叉树
     */
    public BST2DoublyLinkedList buildTree(int[] initData) {
        if (initData == null || initData.length == 0) {
            throw new CommonException("## initData can't be null or empty.");
        }

        tree = new BSTree(initData[0]);
        for (int i = 1; i < initData.length; i++) {
            tree.addNode(initData[i]);
        }
        return this;
    }

    /**
     * 打印二叉树
     */
    public BST2DoublyLinkedList printTree() {
        tree.printTree(tree.getRootNode());
        return this;
    }


    /**
     * 二叉树转换为双向链表
     */
    public BST2DoublyLinkedList convertTree2List() {
        BSTreeNode rootNode = tree.getRootNode();
        List<BSTreeNode> tempList = new ArrayList<BSTreeNode>();

        //第一步：遍历二叉树，把所有节点放入一个列表中
        tree.traverseAndCallback(rootNode, new NodeCallback() {
            @Override
            public void processNode(BSTreeNode node) {
                tempList.add(node);
            }
        });

        //第二步：对列表进行冒泡排序，得到一个有序的列表
        for (int i = 0; i < tempList.size() - 1; i++) {
            for (int j = i + 1; j < tempList.size(); j++) {
                if (tempList.get(i).getValue() > tempList.get(j).getValue()) {
                    BSTreeNode tempNode = tempList.get(i);
                    tempList.set(i, tempList.get(j));
                    tempList.set(j, tempNode);
                }
            }
        }

        //第三步：遍历得到的有序列表，组装链表结构
        BSTreeNode preNode = null;
        for (int i = 0; i < tempList.size() - 1; i++) {
            tempList.get(i).setRightNode(tempList.get(i + 1));
            if (i > 0) {
                preNode = tempList.get(i - 1);
                tempList.get(i).setLeftNode(preNode);
            }
        }

        //得到链表的第一个节点对象
        doublyList = tempList.get(0);

        return this;
    }

    /**
     * 打印链表
     */
    public void printList() {
        BSTreeNode currentNode = doublyList;
        while (currentNode.getRightNode() != null) {

            System.out.print(currentNode.getValue() + "         ");
            currentNode = currentNode.getRightNode();
        }
    }
}
