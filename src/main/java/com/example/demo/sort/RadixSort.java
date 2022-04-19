package com.example.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * 基数排序（桶排序的一种）
 * 原理：分为10个桶，代表0 1 2 3 4 5 6 7 8 9
 *      第一次按个位数入桶，如个位数是1的数入第0个桶，个位数5则入第4个桶，全部入桶后按桶号依次取出数据到arr(完成后个位数就排序好了)
 *      第二次按百位数入桶
 *      依次类推，直到最后一位数
 * 特点：
 *  1 基数排序是对传统桶排序的扩展，速度很快
 *  2 基数排序是经典的空间换时间的方式，占用内存很大
 *  3 基数排序时稳定的
 *
 *
 *  *  * 数据：80000 用时：62ms
 *  *  * 数据：800000 用时：124ms
 *  *  * 数据：8000000 用时：748ms  执行堆内存溢出java.lang.OutOfMemoryError: Java heap space 修改为-Xmx500m才可成功
 *  80万数据时 相对归并排序的1069ms来说要更快，但是占用的内存更多，导致了堆内存溢出
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        Random random = new Random();
        for(int i = 0;i<8000000;i++){
            arr[i] = random.nextInt(80000000);
        }
//        System.out.println("排序前的数据："+ Arrays.toString(arr));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        int[] sortedNums = radixSort(arr);
        Date date1 = new Date();
//        System.out.println("排序后的数据："+ Arrays.toString(sortedNums));
        System.out.println("排序前时间："+simpleDateFormat.format(date));
        System.out.println("排序后时间"+simpleDateFormat.format(date1));
        System.out.println("用时："+(date1.getTime()-date.getTime()));
    }


    public static int[] radixSort(int[] arr){
        //创建10个桶来临时保存数据
        //temp[i][j]:i表示第几个桶  j指向具体的数据
        int[][] temp = new int[10][arr.length];
        //指定每个桶中一共有多少个数据，如bucketSize[2]=5;表示第三个桶中共有5个数据
        int[] bucketSize = new int[10];

        String max = ""+ Arrays.stream(arr).max().getAsInt();
        //另一种简洁写法for(int k = 0, o =1 ; k <max.length(); k++,o *= 10){
        int o = 1;
        for(int k = 0; k <max.length(); k++){
            if (k > 0){
                o *= 10;
            }
            //遍历arr，将数放到桶temp中
            for (int i = 0; i < arr.length; i++) {
                int num = arr[i]/o%10; //取出个位数
                temp[num][bucketSize[num]] = arr[i];
                bucketSize[num] += 1;
            }
            //将桶中的数据取出，放回到arr中
            int index = 0; //arr的下标（index）
            for (int i = 0; i < 10; i++) { //十个桶
                for (int j = 0; j < bucketSize[i]; j++){ //遍历每个桶
                    arr[index] = temp[i][j];
                    index ++;
                }
                bucketSize[i] = 0;  //清空桶
            }
        }
        return arr;
    }
}
