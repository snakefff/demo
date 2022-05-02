package com.example.demo.tree.Huffman;

import com.example.demo.sort.QuickSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 * 原理：所有节点的权值和路径长度的乘积最小的数叫最优二叉树，也叫赫夫曼树
 * 该类主要将一个数组的数组成一个赫夫曼树
 */
public class HuffmanTree {

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        int[] arr = {4,1,5,9,2};
        Node root = huffmanTree.createHuffmanTree(arr);  //生成赫夫曼树，返回树的根节点
        huffmanTree.preOrder(root); //前序遍历赫夫曼树

    }


    public Node createHuffmanTree(int[] arrs){
        List<Node> list = new ArrayList<>();
        for (int num:arrs) {
            list.add(new Node(num,1));
        }
        list.sort(Node::compareTo);
        System.out.println("排序后："+ list);

        while (list.size()>1){
            Node min1=list.get(0);
            Node min2=list.get(1);
            Node node = new Node((min1.getValue()+min2.getValue()),0);
            node.setLeft(min1);
            node.setRight(min2);
            list.remove(min1);
            list.remove(min2);
            list.add(node);
            Collections.sort(list);
        }
        return list.get(0);  //返回最终生成的赫夫曼树的根节点
    }
    //前序遍历
    public void preOrder(Node node){
        System.out.println("value：" + node.getValue() + " type:" + node.getType());

        if (node.getLeft() != null) {
            preOrder(node.getLeft());
        }
        if (node.getRight() != null) {
            preOrder(node.getRight());
        }
    }
}



//赫夫曼树的节点
class Node implements Comparable<Node>{
    private int value;//权值
    private Node left; //左节点
    private Node right; //右节点
    private int type; //判断是否是根节点，1是0否

    public Node(int value, int type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", type=" + type +
                '}';
    }
}
