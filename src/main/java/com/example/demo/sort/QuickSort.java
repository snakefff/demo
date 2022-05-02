package com.example.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * 快速排序
 * 数据：80000 用时：29ms
 * 数据：800000 用时：94ms
 * 数据：8000000 用时：874ms
 * 数据：80000000 用时：9617ms（约10s）  第一次执行堆内存溢出java.lang.OutOfMemoryError: Java heap space
 * 解决方法：修改堆默认大小：run-->edit configurations-->add VM options – 填入修改内存的大小-Xmx500m 才能成功
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] sortNums = new int[8000000];
        Random random = new Random();
        for(int i = 0;i<8000000;i++){
            sortNums[i] = random.nextInt(80000000);
        }
//        int[] sortNums = {4,1,5,2,5,1,6,2,1,9,3};
//        int[] sortNums = {5,3,5,5};
//        System.out.println("排序前的数据："+ Arrays.toString(sortNums));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.out.println("开始排序");

        int[] sortedNums = new QuickSort().sort(sortNums,0,sortNums.length-1);
        Date date1 = new Date();
//        System.out.println("排序后的数据："+ Arrays.toString(sortedNums));
        System.out.println("排序前时间："+simpleDateFormat.format(date));
        System.out.println("排序后时间"+simpleDateFormat.format(date1));
        System.out.println("用时："+(date1.getTime()-date.getTime()));
    }

    /**
     * 重载sort方法
     * @param arr 需要排序的数组
     * @return 排序后的数组
     */
    public int[] sort(int[] arr){
        return sort(arr,0,arr.length-1);
    }

    /**
     * 快速排序:跟冒泡排序一样属于交换排序
     * 核心：每次排序后，左边的数都小于中值，右边的数都大于中值
     * @param arr 保存数据的数组
     * @param first 第一个下标
     * @param last  最后一个下标
     * @return
     */
    public int[] sort(int[] arr,int first,int last){
        int midVal = arr[(first+last)/2]; //中值，
        int left =first;    //左指针
        int right = last;   //右指针
        int temp;
        while(left<right){
            while (arr[left] < midVal){ //如果左边的值小于等于中值，数值不用变，则左边指针右移
                left++;
            }
            while (arr[right] > midVal ){//如果右边的值大于等于中值，数值不用变，则右边指针左移
                right--;
            }
            if (left >= right){
                break;
            }
            //否则两个数就需要交换位置
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            //排序最终必然至少会有一个索引的位置的值是等于midVal的，因而一次递归结束后，中值的索引会被确定
            if (arr[left] == midVal){
                right--;
            }
            if (arr[right] ==midVal){
                left++;
            }
        }
        //上方一次比较完成，下方为下一次递归准备
        //这一个判断很关键，保证了 当left==right时，arr[left] ==midVal;
        // 也就是说midVal的索引已经确定是left，下一次递归的时候就不用再排这个索引的数
        //继续延伸，也就是说每次快速排序都能确定一个中值的索引位置。所以快速排序实际递归的次数最多为arr.length()-1
        if (left == right){
            left++;
            right--;
        }
        if (right>first){
            sort(arr,first,right);
        }
        if (left<last){
            sort(arr,left,last);
        }
        return arr;
    }

}
