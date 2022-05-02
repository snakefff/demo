package com.example.demo.search;

import java.util.Arrays;

/***
 * 斐波那契查找
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = {1,4,6,7,8,11,11,14,15,1111111};
        System.out.println("数组：" + Arrays.toString(arr));
        int findVal = 11;
        int index = fibSearch2(arr,0,arr.length,findVal);
        System.out.println(findVal+"的下标是："+index);
    }
    /**
     * 斐波那契查找
     * 循环方式实现
     * @param arr     数组
     * @param findVal 要查找的值
     * @return 找到值的下标，不存在返回-1
     */
    public static int fibSearch2(int[] arr, int left, int right, int findVal) {
        int k = getFibMaxIndex(arr); //获得斐波那契最大索引
        int[] fibArr = getFibArr(k+1);  //生成斐波那契数列
        System.out.println("斐波那契数列：" + Arrays.toString(fibArr));
        int newLenth = fibArr[k];
        int[] temp = getTempArr(arr,newLenth); //生成新的 经过斐波那契扩张后的数组
        System.out.println("临时数组：" + Arrays.toString(temp));
        while(left <= right){ //注意：left==right的时候，说明需查的数组只有一个数，也是有可能是我们要找的值
            int mid = left + fibArr[k - 1] - 1;
            if (temp[mid] > findVal) { //比较左边
                k = k - 1;
                right = mid - 1;
            } else if (temp[mid] < findVal) {//比较右边
                k = k - 2;
                left = mid + 1;
            } else {
                return Math.min(mid, arr.length-1);
            }
        }
        return -1;
    }

    /**
     * 获得斐波那契数列
     *
     * @param length 指定数列长度
     * @return 返回斐波那契数列
     */
    public static int[] getFibArr(int length) {
        int[] fibArr = new int[length];
        fibArr[0] = 1;
        fibArr[1] = 1;
        for (int i = 2; i < length; i++) {
            fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
        }
        return fibArr;
    }

    /**
     * 根据数组arr长度生成斐波那契数组的最大索引
     * @param arr  原数组
     * @return 最大索引
     */
    public static int getFibMaxIndex(int[] arr){
        int right = arr.length-1;
        int[] fibArr = getFibArr(30);  //临时生成长度为30的斐波那契数列
        int k = 0;  //斐波那契数列的下标
        int oldlength; //临时记录斐波那契数列的长度，用于扩张
        //数组arr的最大值如果大于fibArr的第k位，k+1，直到high小于或者等于fibArr[k]
        while (right > fibArr[k]-1) {
            k++;
            if(k > fibArr.length-1){ //扩张数组
                oldlength = fibArr.length;
                fibArr = getFibArr(oldlength+1);
            }
        }
        return k;
    }

    /**
     * 根据斐波那契数列，生成新大小的数组，多出的值由arr[high]填充
     * @param arr 原数组
     * @return  斐波那契扩张过的数组，用于进行斐波那契查找
     */
    public static int[] getTempArr(int[] arr,int newLength){
        int right = arr.length-1;
        //复制数组arr到temp，temp的长度为fibArr[k]，超过长度的值用arr的最大值arr[high]来填充
        int[] temp = new int[newLength];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        return temp;
    }
}
