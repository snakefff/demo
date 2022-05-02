package com.example.demo.search;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 直接查找
 */
public class DerectSearch {

    public static void main(String[] args) {
        int[] arr = {1,5,2,8,45,48,5,7,1};
        int num = 5;
        System.out.println(num + "在"+search(arr,num)+"位第一次出现");

        System.out.println(num + "在数组中的一下位置：" + searchAll(arr, num));

    }

    //查询一个数是否在数组中，在就返回第一个下标，不存在则返回-1
    public static int search(int[] arr ,int num){
        int res = -1;
        for (int i = 0; i < arr.length; i++) {
            if (num==arr[i]){
                return i;
            }
        }
        return res;
    }

    public static ArrayList<Integer> searchAll(int[] arr, int num){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (num==arr[i]){
                arrayList.add(i);
            }
        }
        if (arrayList.isEmpty()){
            return null;
        }
        return arrayList;
    }
}
