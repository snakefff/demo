package com.example.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * 归并算法
 * 基本原理：
 *  归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，
 *  该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，
 *  而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
 *  （治）的时间复杂度：arr.length()-1;即如果有1000数组，合并的次数就是1000-1
 *
 *  * 数据：80000 用时：28ms
 *  * 数据：800000 用时：109ms
 *  * 数据：8000000 用时：1069ms
 *  * 数据：80000000 用时：12055ms（约12s）  第一次执行堆内存溢出java.lang.OutOfMemoryError: Java heap space
 *  * 解决方法：修改堆默认大小：run-->edit configurations-->add VM options – 填入修改内存的大小-Xmx1000m 才能成功（-Xmx500m仍失败）
 *  跟快速排序比较：
 *      1 快速排序（quickSort）相对而言快10%~20%
 *      2 快速排序（quickSort）占用更少的堆内存空间
 *          论证--8千万个数排序时，快速排序把堆默认内存改为-Xmx500m就可以解决溢出，而归并排序不行
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = new int[8];
//        Random random = new Random();
//        for(int i = 0;i<8;i++){
//            arr[i] = random.nextInt(80);
//        }
        int[] arr = {2,1,4,2,1,5,1};
        int[] temp = new int[arr.length];
        System.out.println("排序前的数据："+ Arrays.toString(arr));
        Date beforeDate = new Date();
        int[] sortedNums = mergeSort(arr,0, arr.length-1,temp);
        Date afterDate = new Date();
        System.out.println("排序后的数据："+ Arrays.toString(sortedNums));
        elapsedTime(beforeDate,afterDate);
    }

    static void elapsedTime(Date beforeDate, Date afterDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("排序前时间："+simpleDateFormat.format(beforeDate));
        System.out.println("排序后时间"+simpleDateFormat.format(afterDate));
        System.out.println("用时："+(afterDate.getTime()-beforeDate.getTime()));
    }

    public static int[] mergeSort(int[] arr, int left, int right ,int[] temp){
        int mid = (left + right)/2;
        if (left<right){
            //分
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    public static void merge(int[] arr, int left,int mid, int right, int[] temp){
        int l = left;  //左边数组的起始索引
        int r = mid+1; //右边数组的起始索引
        int t = 0; //temp的索引
        //(1)依次比较左右两边的数
        //只要两边的数都没查完，就继续比较，小的一个数进入temp数组中
        while(l <= mid && r <= right){
            if(arr[l] <= arr[r]){
                temp[t] = arr[l];
                l++;
            } else {
                temp[t] = arr[r];
                r++;
            }
            t++;
        }
        //(2) 有任何一边比较完成，把另一边的剩余数据复制到temp后方
        //查完后,左边数组还有数据,把左边剩余的数据复制到temp后方
        while(l <= mid){
            temp[t] = arr[l];
            l++;
            t++;
        }
        //查完后，右边还有数据
        while(r <= right){
            temp[t] =arr[r];
            r++;
            t++;
        }
        //(2)把temp中的数据复制到arr中
        if (t >= 0) System.arraycopy(temp, 0, arr, left, t);
//      代码替代 for(int i = 0; i < t; i ++){
//            arr[left+i] = temp[i];
//        }

    }
}
