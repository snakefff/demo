package com.example.demo.tree;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 堆排序（堆其实指的的一个二叉树）
 * 结合二叉树和插入排序的一种排序方式
 * 时间复杂度为nlog(n)
 * 如果有几个数相等，无法保证数的顺序仍然是排序前的顺序，所以具有不稳定性
 * 8百万1695ms(1.7s)
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] sortNums = new int[8000000];
        Random random = new Random();
        for(int i = 0;i<8000000;i++){
            sortNums[i] = random.nextInt(8000000);
        }
//        int[] sortNums = {4,1,5,2,5,1,6,2,1,9,3};
//        int[] sortNums = {5,3,5,5};
//        System.out.println("排序前的数据："+ Arrays.toString(sortNums));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("开始排序");
        int[] sortedNums = new HeapSort().heapSort(sortNums);
        Date date1 = new Date();
//        System.out.println("排序后的数据："+ Arrays.toString(sortedNums));
        System.out.println("排序前时间："+simpleDateFormat.format(date));
        System.out.println("排序后时间"+simpleDateFormat.format(date1));
        System.out.println("用时："+(date1.getTime()-date.getTime()));
    }

    /**
     * 堆排序
     * @param arr 数组
     * @return 排序后的数组
     */
    public int[] heapSort(int[] arr) {
        //生成大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        //将大顶堆的顶位和尾位交换位置，然后除去尾位再生成大顶堆，如此循环
        for (int maxIndex = arr.length-1; maxIndex >0 ; maxIndex--) {
            //顶尾交换位置
            int temp = arr[0];
            arr[0]= arr[maxIndex];
            arr[maxIndex] = temp;
            //整理堆，使得新的堆重新变成大顶堆
            adjustHeap(arr,0,maxIndex);
        }
        return arr;
    }

    /**
     * 调整堆（数组）中的值，使得堆不断接近大顶堆，最终会成为大顶堆
     *
     * @param arr    排序的数组
     * @param i      该次执行比较的值的索引
     * @param length 比较的数组长度，会越来越小
     */
    public void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//临时值，用于比较
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) { //由上往下比较temp值，大于temp值的数上移
            //比较左右节点的大小
            if ((k+1)<length && arr[k] < arr[k + 1]) {//右节点大于左节点
                k = k + 1;
            }
            //临时值和左右节点中大的值比较
            if (temp >= arr[k]) { //临时值大于左右节点，直接退出；原因：外层会使i从大到小地调用该方法，因此下面的堆永远是大顶堆，没必要继续往下比较
                break;
            } else {  //arr[k]>temp时，arr[k]上移到i，临时值的位置i下移到k;
                arr[i] = arr[k];
                i = k;
            }
        }
        //循环结束后，将临时值temp放回临时位置i
        arr[i] = temp;
    }
}
