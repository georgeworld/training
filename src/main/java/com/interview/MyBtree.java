package com.interview;

import java.util.LinkedList;
import java.util.Queue;

public class MyBtree {
    private Queue<GNode<Integer>> list = new LinkedList<>();

    public GNode<Integer> generate(int height) {
        int totalNodes = (int) Math.pow(2, height) - 1;
        System.out.println("节点总数=" + totalNodes);
        for (int i = 1; i <= totalNodes; i++) {
            list.add(new GNode<Integer>(i));
        }

        GNode<Integer> root = list.peek();
        addNode();
        return root;
    }

    private void addNode() {
        int len = list.size();
        for (int i = 0; i < len; i += 2) {
            GNode<Integer> currentNode = list.poll();
            GNode<Integer> leftNode = list.poll();
            GNode<Integer> rightNode = list.poll();

            leftNode.setParentNode(currentNode);
            rightNode.setParentNode(currentNode);
            currentNode.setLeftNode(leftNode);
            currentNode.setRightNode(rightNode);
        }

    }

    public void printTree(GNode<Integer> node) {
        if (node == null) {
            return;
        } else {
            System.out.println(node.getValue());
        }
        printTree(node.getLeftNode());
        printTree(node.getRightNode());
    }


}
