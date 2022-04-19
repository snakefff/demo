package com.example.demo;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroSingleLinkedList list = new HeroSingleLinkedList();
        HeroNode heroNode2 = new HeroNode(2,"吴用","智多星");
        HeroNode heroNode1 = new HeroNode(1 ,"宋江","及时雨");
        HeroNode heroNode3 = new HeroNode(3,"晁盖","玉麒麟");
        HeroNode heroNode4 = new HeroNode(4,"林冲","豹子头");
        HeroNode heroNode5 = new HeroNode(5,"武松","打虎英雄");
        HeroNode heroNode10 = new HeroNode(10,"鲁智深","花和尚");
        list.add(heroNode2);
        list.add(heroNode1);
        list.add(heroNode3);
        list.add(heroNode4);
        list.add(heroNode5);
        list.showHeros();
        list.delete(2);
        list.showHeros();
        list.deleteByname("林冲");
        list.showHeros();
        list.add(heroNode4);
        list.add(heroNode10);
        list.add(heroNode1);
        list.showHeros();
    }
}

class HeroSingleLinkedList{
    HeroNode head; //链表头

    public HeroSingleLinkedList(){
        head = new HeroNode(0,"","");
    }
    //判断是否为空链表
    public boolean isEmpty(){
        return head.getNext() == null;
    }
    //添加英雄到链表
    public void add(HeroNode newHero){
//        //如果链表为空，直接添加
//        if (isEmpty()){
//            head.setNext(newHero);
//            return;
//        }
//        HeroNode temp = head.getNext();
//        //id已存在，报错退出
//        if (temp.getId() == newHero.getId()){
//            System.out.printf("添加失败，第%d名的英雄已存在",newHero.getId());
//            System.out.println();
//            return;
//        }
//        //如果第一个id英雄的排名就比新英雄大，直接插入到第一个位置
//        if (temp.getId()> newHero.getId()){
//            head.setNext(newHero);
//            newHero.setNext(temp);
//            return;
//        }

        HeroNode temp = head;
        while (true){
            //如果temp已经是最后一个英雄，则新英雄放到链表最后方
            if (temp.getNext()==null){ //最后一个英雄
                temp.setNext(newHero);
                System.out.println("添加新英雄成功："+newHero.getName());
                break;
            }
            //id已存在，报错退出
            if (temp.getNext().getId() == newHero.getId()){
                System.out.printf("添加失败，第%d名的英雄已存在",newHero.getId());
                System.out.println();
                return;
            }
            //如果新的英雄排名在temp前面，则插入
            if(temp.getNext().getId() > newHero.getId()){
                newHero.setNext(temp.getNext());
                temp.setNext(newHero);
                System.out.println("添加新英雄成功："+newHero.getName());
                break;
            }
            temp=temp.getNext();
        }
    }
    public void delete(int id){
        if (isEmpty()){
            System.out.println("链表为空，无需删除");
        }
        HeroNode temp = head.getNext(); //临时节点，初始值为第一个有效节点
        if (temp.getId() == id){//第一个就匹配了，删除(即)
            head.setNext(temp.getNext());
            temp.setNext(null);
            return;
        }
        while (true){
            //查询到最后一个，未找到,删除失败
            if (temp.getNext()==null){
                System.out.println("删除失败，该英雄不存在");
                break;
            }
            //临时节点的下一个节点的id匹配，删除
            if(temp.getNext().getId() == id){
                temp.setNext(temp.getNext().getNext());
                System.out.printf("删除排名%d的英雄完成：",id);
                System.out.println();
                break;
            }
            temp = temp.getNext();
        }


    }
    public void deleteByname(String name){
        if (isEmpty()){
            System.out.println("链表为空，删除");
        }
        HeroNode temp = head.getNext(); //临时节点，初始值为第一个有效节点
        if (temp.getName().equals(name)){//第一个就匹配了，删除(即)
            head.setNext(temp.getNext());
            temp.setNext(null);
            return;
        }
        while (true) {
            //临时节点的下一个节点为空,退出
            if (temp.getNext() == null) {
                System.out.println("删除失败，该英雄不存在");
                break;
            }
            //临时节点的下一个节点的id匹配，删除
            if (temp.getNext().getName().equals(name)) {
                temp.setNext(temp.getNext().getNext());
                System.out.printf("删除%s完成：",name);
                System.out.println();
                break;
            }
            temp = temp.getNext();
        }
    }
    public void showHeros(){
        if (isEmpty()){
            System.out.println("链表为空");
            return;
        }
        System.out.println("英雄排名：");
        HeroNode temp = head.getNext();
        while (true){
            if (temp != null){
                System.out.println(temp);
                temp = temp.getNext();
                continue;
            }
            break;
        }
    }
}

class HeroNode{
    private int id;  //排名
    private String name; //名字
    private String nickname; //外号
    private HeroNode next;

    public HeroNode(int id,String name , String nickname){
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        if (next==null){
            return "" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", next=null";
        }

        return "" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", next=" + next.getId() +
                ":"+next.getName();
    }
}
