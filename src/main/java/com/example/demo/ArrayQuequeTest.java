package com.example.demo;

import java.util.Scanner;

public class ArrayQuequeTest {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("欢迎测试数组队列！");
            ArrayQueque arrayQueque = new ArrayQueque();
            boolean flag = true;
            while (flag) {
                System.out.println("请选择功能");
                System.out.println("a(add)添加队列元素");
                System.out.println("g(get)取出队列元素");
                System.out.println("s(show)显示所有元素");
                System.out.println("h(head)显示队列头元素");
                System.out.println("e(end)退出");
                System.out.println("z显示数组");
                char input = scanner.next().charAt(0); //只读取输入值得头一位
                switch (input) {
                    case ('a'):
                        System.out.println("请输入要添加的值：");
                        int num = scanner.nextInt();
                        arrayQueque.add(num);
                        break;
                    case ('g'):
                        arrayQueque.get();
                        break;
                    case ('s'):
                        arrayQueque.showAll();
                        break;
                    case ('h'):
                        arrayQueque.showHead();
                        break;
                    case ('e'):
                        flag = false;
                        System.out.println("系统退出");
                        break;
                    case ('z'):
                        arrayQueque.showArray();
                        break;
                    case 0:
                        System.out.println("请输入正确的指令");
                }
            }
        } catch (Exception e) {
            System.out.println("系统异常");
        }
    }
}

class ArrayQueque{
    int[] array ; //模拟队列的数组
    int front;  //指向队列头，初始值为0，指向当前要取出来的值
    int rear;   //指向队列尾，初始值也为0，指向队列最后一个数据的下一个位置
    int quequeSize;
    int arraySize;

    public ArrayQueque(int quequeSize){ //指定容量
        this.array= new int[quequeSize+1]; //因为是循环队列，保留一位空格 区分队列的头尾
        this.front = 0;
        this.rear = 0;
        this.quequeSize =quequeSize;
        this.arraySize = this.array.length;
    }

    public ArrayQueque(){
        this.array= new int[4]; //如果没有指定队列容量，默认为3（保留一位空格,所以数组要写为4）
        this.front = 0;
        this.rear = 0;
        this.quequeSize =3;
        this.arraySize = this.array.length;
    }

    //判断队列是否已满
    //条件：((rear + quequeSize +1) - front）%（quequeSize+1） == quequeSize
    //也就是(rear + arraySize-front)%arraySize == quequeSize
    public boolean isFull(){
        return (rear + arraySize-front)%arraySize == quequeSize;
    }
    //判断是否为空
    public boolean isEmpty(){
        return front == rear;
    }
    //添加队列元素
    public void add(int n){
        if(isFull()){
            System.out.println("队列已满，不能添加");
            return;
        }
        array[rear] = n;
        rear = (rear + 1 +arraySize)%arraySize;
        System.out.println("添加成功：" + n);
    }
    //取出队列元素
    public void get() {
        if (isEmpty()) {
            System.out.println("队列为空，无法取出数据");
            return;
        }
        System.out.println("取出的数据为：" + array[front]);
        array[front]=0; //将队列元素置空
        front = (front + 1 +arraySize)%arraySize; //指针后移一位

    }
    //显示队列头元素
    public void showHead() {
        if (isEmpty()) {
            System.out.println("队列为空，无法显示");
            return;
        }
        System.out.println("队列头为：" + array[front]);
    }

    //显示队列中的所有元素
    public void showAll(){
        if (isEmpty()){
            System.out.println("队列为空，无法显示");
            return;
        }
        System.out.print("队列为:{");
        for (int i = 0; i < (rear - front + arraySize)%arraySize; i++){
            int index = (front+i)%arraySize;
           System.out.print(array[index]+" ");
        }
        System.out.println("}");
    }

    public void showArray(){
        System.out.print("数组：" +"[");
        for (int i = 0 ;i<arraySize;i++){
            System.out.print(array[i] + ",");
        }
        System.out.println("]");
    }
}
