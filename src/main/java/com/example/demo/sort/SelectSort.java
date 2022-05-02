package com.example.demo.sort;

import com.example.demo.io.WriteDataToFile;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
/***
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) throws IOException {

//        int[] sortNums = {4,1,5,2,5,1,6,2,1,9,3};
//        System.out.println("排序前："+ Arrays.toString(sortNums));
//        int[] sorted = sort(sortNums);
//        System.out.println("排序后："+Arrays.toString(sorted));
        int[] sortNums = new int[80000];
        Random random = new Random();
        for(int i = 0;i<80000;i++){
            sortNums[i] = random.nextInt(80000);
        }
//        WriteDataToFile.writeData("E:\\\\linux\\\\TestData\\\\selectSortData1",Arrays.toString(sortNums));
        Date beforeDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        int[] sortedNums = sort2(sortNums);
        Date afterDate = new Date();
        MergeSort.elapsedTime(beforeDate,afterDate);

    }

    /**
     * 选择排序1
     * 说明：该种选择排序会经常性地改变nums[j]的值，从而导致前面查询到的小的数据会被扔到很后面，增加了查询次数。在后期甚至演变成了类似冒泡排序
     * 所以sort2方法要优秀得多；
     * 8万数据循环次数：1176142094 用时9s(9289ms)
     * @param nums
     * @return
     * @throws IOException
     */
    public static int[] sort1(int[] nums) throws IOException {

        int count = 0; //计算循环次数
        int temp = 0;
        for (int i = 0 ;i<nums.length;i++){
            for (int j = i+1;j < nums.length;j++){
                if (nums[i]>nums[j]){
                    count++; //结果为：1176142094
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
//                    WriteDataToFile.writeData("E:\\\\linux\\\\TestData\\\\selectSortData1","nums["+i+"]="+nums[i]+"\n");
                }
            }
//            System.out.println("第"+i+"次排序的结果为："+ Arrays.toString(nums));
        }
        System.out.println("sort1循环次数："+count);
        return nums;
    }

    /**
     * 选择排序2
     * 不会把小的数据往后面扔，可以减少查询次数，更优
     * 8万数据循环次数：766252 用时约2秒（1680ms）
     * @param nums
     * @return
     * @throws IOException
     */
    public static int[] sort2(int[] nums) throws IOException {
        int min;  //临时变量，用来保存当前最小值
        int minIndex = 0;
        int count = 0;
        int forICount = 0; //计算进入第一层循环的次数
        long forJcount =0; //计算进入第二层循环的次数
        for (int i = 0 ;i<nums.length;i++){
            forICount++;
            min = nums[i];
            minIndex = i;
            //循环比较第i位后面的数，获取到最小值
            for (int j = i+1;j < nums.length;j++){
                forJcount++;
                if (min>nums[j]){
                    count++; //结果为：766252
                    min = nums[j];
                    minIndex = j;
                }
            }
            //将第i位的值 和 原本存放最小值的位置的值交换
            nums[minIndex] = nums[i]; //原存放最小值的位置存i
            nums[i] = min; //i位存最小值
//            System.out.println("第"+i+"次排序的结果为："+ Arrays.toString(nums));
        }
        System.out.println("sort2进入第一层for次数："+forICount);
        System.out.println("sort2进入第二层for次数："+forJcount);
        System.out.println("sort2修改min次数："+count);
        return nums;
    }


}
