package com.example.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * 冒泡排序
 */
public class DubbleSort {

    public static void main(String[] args) {
        int[] sortNums = new int[80000];

//        System.out.println("排序前："+ Arrays.toString(sortNums));
//        int[] sorted = sort(sortNums);
//        System.out.println("排序后："+Arrays.toString(sorted));

        Random random = new Random();
        for(int i = 0;i<80000;i++){
            sortNums[i] = random.nextInt(80000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.out.println("排序前时间："+simpleDateFormat.format(date));
        int[] sortedNums = sort(sortNums);
        Date date1 = new Date();
        System.out.println("排序后时间"+simpleDateFormat.format(date1));
        System.out.println("用时："+(date1.getTime()-date.getTime())); //结果：8万数据排序用时9s(9389ms)
    }


    public static int[] sort(int[] nums){
        int temp;
        boolean ifchange = false;
        for (int i = 1; i < nums.length; i++){ //第几次排序
            for (int j = 0; j<nums.length-i ;j++){ //前后两个数进行比较
                if (nums[j]>nums[j+1]){
                    temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    ifchange = true;
                }
            }
//            System.out.println("第"+i+"次排序的结果为："+Arrays.toString(nums));
            if (!ifchange){//如果ifchange为false，则说明在上一次排序中没有对顺序进行改变，也就是说数组已经是有序的了
                System.out.println("第"+i+"次排序时数组已经有序的了");
                break;
            }
            ifchange = false; //将标记重置为false
        }
        return nums;
    }
}
