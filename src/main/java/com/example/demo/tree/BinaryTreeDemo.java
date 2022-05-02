package com.example.demo.tree;

/**
 * 对二叉树进行前序 中序 后序遍历
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        //手动创建树
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "卢俊义");
        HeroNode heroNode4 = new HeroNode(4, "关胜");
        HeroNode heroNode5 = new HeroNode(5, "林冲");
        HeroTree heroTree = new HeroTree(root);
        root.left = heroNode2;
        root.right = heroNode3;
        heroNode3.left = heroNode4;
        heroNode3.right = heroNode5;
        System.out.println("前序遍历");
        heroTree.preOrder();
        System.out.println("中序遍历");
        heroTree.infixOrder();
        System.out.println("后序遍历");
        heroTree.postOrder();

    }


}

class HeroTree {
    HeroNode root;

    public HeroTree(HeroNode root) {
        this.root = root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (root == null) {
            System.out.println("二叉树为空，不可进行前序遍历");
        } else {
            root.preOrder(root);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空，不可进行前序遍历");
        } else {
            root.infixOrder(root);
        }
    }

    public void postOrder() {
        if (root == null) {
            System.out.println("二叉树为空，不可进行前序遍历");
        } else {
            root.postOrder(root);
        }
    }
}

class HeroNode {
    int id;
    String name;
    HeroNode left; //左节点
    HeroNode right; //右节点

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //前序遍历
    public void preOrder(HeroNode heroNode) {

        System.out.println("id：" + heroNode.id + " name:" + heroNode.name);

        if (heroNode.left != null) {
            preOrder(heroNode.left);
        }
        if (heroNode.right != null) {
            preOrder(heroNode.right);
        }
    }

    //中序遍历
    public void infixOrder(HeroNode heroNode) {
        if (heroNode.left != null) {
            infixOrder(heroNode.left);
        }

        System.out.println("id：" + heroNode.id + " name:" + heroNode.name);

        if (heroNode.right != null) {
            infixOrder(heroNode.right);
        }
    }

    //后序遍历
    public void postOrder(HeroNode heroNode) {
        if (heroNode.left != null) {
            postOrder(heroNode.left);
        }
        if (heroNode.right != null) {
            postOrder(heroNode.right);
        }

        System.out.println("id：" + heroNode.id + " name:" + heroNode.name);

    }
}
