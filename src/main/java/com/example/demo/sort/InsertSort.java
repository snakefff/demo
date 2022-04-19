package com.example.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/***
 * 插入排序-直接插入排序
 * 还有一种是插入排序是希尔排序
 */
public class InsertSort {

    public static void main(String[] args) {

//        int[] nums = {4,52,12,51,23,11};

//        int[] nums = new int[10];
//        Random random = new Random();
//        for (int i = 0;i<10;i++){
//            nums[i] = random.nextInt(80000);
//        }
        int[] nums = {4,1,5,2,5,1,6,2,1,9,3};
        System.out.println("排序前："+ Arrays.toString(nums));
        Date beforeDate = new Date();//排序前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        int[] sortedNums = sort(nums);
        Date afterDate = new Date();//排序后时间
        System.out.println("排序后："+ Arrays.toString(sortedNums));
        System.out.println("排序前时间：" + simpleDateFormat.format(beforeDate));
        System.out.println("排序前时间：" + simpleDateFormat.format(afterDate));
        System.out.println("用时：" + (afterDate.getTime()-beforeDate.getTime()));

    }

    /**
     * 插入排序算法，
     * 从1开始，每个数字都跟前面的数字比较，
     * 如果前一个数比插入数大则前一个数后移一位，插入位置前移一位；
     * 直到前一个数小于或者等于插入数，就是插入位置
     * 从第二个数nums[1]开始比较,直到最后一个数nums[nums.length-1]
     * 测试结果：8万数据用时1.2s(1233ms) 数组里的数后移次数（while）：1598949950；插入最大次数：79999（即nums.length-1）
     *
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums){
        int count = 0; //统计插入值前移次数
        for (int i = 1;i < nums.length;i++){
            int insertVal = nums[i];
            int insertIndex = i;
            //后移循环
            //如果insertIndex大于0，并且插入位置inserIndex的上一个值大于插入位置，插入位置就该前移，并且上一个值后移
            while (insertIndex > 0 && nums[insertIndex-1] >insertVal){
                nums[insertIndex] = nums[insertIndex -1];
                insertIndex--;
                count++;
            }
            //不再进入上面的while循环，说明当前位置insertIndex已经是合适的插入位置；则赋予插入值
            nums[insertIndex] = insertVal;
        }
        System.out.println("总循环次数："+count);
        return nums;
    }
}
