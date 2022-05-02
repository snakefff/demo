package com.example.demo.tree.Huffman;

/**
 *
 */
public class HuffNode implements Comparable<HuffNode>{
    private String key; //保存的值
    private int value; //权值
    private HuffNode left; //左节点
    private HuffNode right; // 右节点

    public HuffNode(int value) {
        this.value = value;
    }

    public HuffNode(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HuffNode getLeft() {
        return left;
    }

    public void setLeft(HuffNode left) {
        this.left = left;
    }

    public HuffNode getRight() {
        return right;
    }

    public void setRight(HuffNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(HuffNode o) {
        return this.getValue()-o.getValue();
    }

    @Override
    public String toString() {
        return "HuffNode{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
