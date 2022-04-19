package com.example.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class ShellSort {
    public static void main(String[] args) {
//        int[] sortNums = new int[80000];
//        Random random = new Random();
//        for(int i = 0;i<80000;i++){
//            sortNums[i] = random.nextInt(80000);
//        }
        int[] sortNums = {4,1,5,2,5,1,6,2,1,9,3};
        Date beforeDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        int[] sortedNums = sort(sortNums);
        Date afterDate = new Date();
        MergeSort.elapsedTime(beforeDate,afterDate);
    }

    /**
     * 希尔排序-移动法（效率高得多）
     * 希尔排序有交换法和移动法
     * 交换法：比较大小后将两数互相交换
     * 移动法：将要比较的右边的数保存到临时变量，一旦符合交换条件，将左边的值直接移动到右边即可
     * @param arr
     * @return
     */
    public static int[] sort(int[] arr){
        //gap 比较的数据的步数差
        for (int gap = arr.length/2; gap>0 ;gap /=2){
            for(int i = gap;i<arr.length;i++){
                int j = i;
                int insertVal = arr[j];
                while (j - gap >= 0 && arr[j-gap]>insertVal){//左边的数大于右边，则左边的值移动到右边
                    arr[j] = arr[j-gap];
                    j -= gap;
                }
                arr[j] = insertVal;
            }
        }
        return arr;
    }
}
