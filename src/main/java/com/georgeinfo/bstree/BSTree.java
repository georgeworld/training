package com.georgeinfo.bstree;

import com.util.CommonException;

import java.util.Stack;

/**
 * 二叉树操作类
 *
 * @author George<GeorgeWorld                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               @                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               qq.com>
 */
public class BSTree {
    private BSTreeNode rootNode;

    public BSTree(int rootValue) {
        rootNode = new BSTreeNode(rootValue);
    }

    /**
     * 将给定的节点，插入到合适的地方<br>
     * <nobr>
     * ------------------4
     * ----------------/  \
     * --------------1      7
     * ------------/  \     / \
     * ----------0     2  5    8
     * ----------------\   \    \
     * -----------------3   6    9
     * </nobr>
     *
     * @param value 待插入的节点数据
     * @return 组装后的根节点对象
     **/
    public BSTree addNode(int value) {
        if (value == rootNode.getValue()) {
            throw new CommonException("### The value entered cannot be repeated with the existing node value.");
        }
        //首先，遍历二叉树，查找
        BSTreeNode currentNode = rootNode;
        while (true) {
            if (value < currentNode.getValue()) {//比当前父节点的值要小，下一层比较左节点
                if (currentNode.getLeftNode() == null) { //如果父节点还没有左子树，就插入
                    BSTreeNode newNode = new BSTreeNode(value);
                    currentNode.setLeftNode(newNode);
                    break;
                } else {//左子树不为空，与左子树进行比较
                    currentNode = currentNode.getLeftNode();
                    continue;
                }

            } else if (value > currentNode.getValue()) {//如果传入的值，比当前节点的值要大，则与右子树比较
                if (currentNode.getRightNode() == null) { //如果父节点还没有右子树，就插入
                    BSTreeNode newNode = new BSTreeNode(value);
                    currentNode.setRightNode(newNode);
                    break;
                } else {//右子树不为空，与右子树进行比较
                    currentNode = currentNode.getRightNode();
                    continue;
                }

            } else {//相等
                throw new CommonException("### The value entered cannot be repeated with the existing node value[1].");
            }
        }


        return this;
    }

    /**
     * 先序递归遍历二叉树
     * * <nobr>
     * * ------------------4
     * * ----------------/  \
     * * --------------1      7
     * * ------------/  \     / \
     * * ----------0     2  5    8
     * * ----------------\   \    \
     * * -----------------3   6    9
     * * </nobr>
     **/
    public void printTree(BSTreeNode node) {
        if (node == null) {
            System.out.println("### Null node.");
            return;
        }
        System.out.println(node.getValue());
        if (node.getLeftNode() != null) {
            printTree(node.getLeftNode());
        }

        if (node.getRightNode() != null) {
            printTree(node.getRightNode());
        }
    }

    /**
     * 先序循环遍历二叉树
     * * <nobr>
     * * ------------------4
     * * ----------------/  \
     * * --------------1      7
     * * ------------/  \     / \
     * * ----------0     2  5    8
     * * ----------------\   \    \
     * * -----------------3   6    9
     * * </nobr>
     **/
    public void traverseByLoop(BSTreeNode node) {
        if (node == null) {
            throw new CommonException("### Root node can't be null.");
        }

        Stack<BSTreeNode> stack = new Stack();
        stack.push(node);

        while (!stack.isEmpty()) {
            BSTreeNode currentNode = stack.pop();
            System.out.println(currentNode.getValue());

            BSTreeNode leftNode = currentNode.getLeftNode();
            BSTreeNode rightNode = currentNode.getRightNode();

            if (rightNode != null) {
                stack.push(rightNode);
            }

            if (leftNode != null) {
                stack.push(leftNode);
            }
        }
    }

    public BSTreeNode getRootNode() {
        return rootNode;
    }
}
