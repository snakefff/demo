package com.example.demo.tree;

/**
 * 数组二叉树，即把数组以二叉树的形式读取
 * 说明：二叉树的保存的是数组的下标，根据二叉树查找的下标来读取数组
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 7, 8, 11, 14};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        arrBinaryTree.preOrder();

        System.out.println("");
        System.out.println("中序遍历");
        arrBinaryTree.infixOrder();

        System.out.println("");
        System.out.println("后序遍历");
        arrBinaryTree.postOrder();
    }

}

class ArrBinaryTree {
    int index = 0;
    int[] arr;
    int length;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
        this.length = arr.length;
    }

    public void preOrder() {
        preOrder(0);
    }

    public void infixOrder() {
        infixOrder(0);
    }

    public void postOrder() {
        postOrder(0);
    }

    public void preOrder(int index) {
        if (index < length) {
            System.out.print(arr[index] + ",");
        }
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (left < length) {
            preOrder(left);
        }
        if (right < length) {
            preOrder(right);
        }
    }

    public void infixOrder(int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (left < length) {
            infixOrder(left);
        }
        System.out.print(arr[index] + ",");
        if (right < length) {
            infixOrder(right);
        }
    }

    public void postOrder(int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        if (left < length) {
            postOrder(left);
        }
        if (right < length) {
            postOrder(right);
        }
        System.out.print(arr[index] + ",");
    }

}

