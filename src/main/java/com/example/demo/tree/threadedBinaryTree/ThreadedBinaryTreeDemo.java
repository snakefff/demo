package com.example.demo.tree.threadedBinaryTree;


public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        //手动创建树
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "卢俊义");
        HeroNode heroNode4 = new HeroNode(4, "关胜");
        HeroNode heroNode5 = new HeroNode(5, "林冲");
        ThreadedBinaryTree heroTree = new ThreadedBinaryTree(root);
        root.left = heroNode2;
        root.right = heroNode3;
        heroNode3.left = heroNode4;
        heroNode3.right = heroNode5;
        /* 前序遍历线索化 */
        heroTree.threadPreTree();
        System.out.printf("吴用的前驱是 %s ,后继是 %s：",heroNode2.left.name,heroNode2.right.name);
        System.out.println();
        System.out.printf("关胜的前驱是 %s ,后继是 %s：",heroNode4.left.name,heroNode4.right.name);
        System.out.println();
        System.out.printf("林冲的前驱是 %s：",heroNode5.left.name);
    }


}

class ThreadedBinaryTree{
    HeroNode root;

    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;

    }
    //将原本的二叉树 前序遍历线索化
    public void threadPreTree(){
        root.threadedPreOrder();
    }
}

class HeroNode {
    int id;
    String name;
    HeroNode left = null; //左节点
    HeroNode right = null; //右节点
    int leftType; //左指针是否被线索化 默认0为否，1为已经线索化过
    int rightThread; //右指针是否被线索化 默认0为否，1为已经线索化过

    HeroNode preNode; //前驱节点

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //前序遍历线索化
    public void threadedPreOrder() {
        //前一个节点的右指针为空，设置其后继节点为当前节点
        if (preNode != null && preNode.right == null) {
            preNode.right = this;
            preNode.rightThread = 1;
        }
        //左指针为空，设置前驱节点
        if (this.left == null){
            this.left = preNode;
            this.leftType = 1;
        } else{ //左指针不为空，左递归
            this.left.preNode=this;//前驱节点变成当前节点
            this.left.threadedPreOrder();
        }
        //右指针不为空，右递归
        if (this.right != null) {
            //重新赋值前驱节点
            if (this.left!=null){
                this.right.preNode=this.left;
            }else {
                this.right.preNode = this;
            }
            this.right.threadedPreOrder();
        }

    }
    //中序和后续遍历未完成
    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            infixOrder();
        }

        System.out.println("id：" + this.id + " name:" + this.name);

        if (this.right != null) {
            infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            postOrder();
        }
        if (this.right != null) {
            postOrder();
        }

        System.out.println("id：" + this.id + " name:" + this.name);

    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}