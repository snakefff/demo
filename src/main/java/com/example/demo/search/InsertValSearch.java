package com.example.demo.search;

import java.util.ArrayList;

/**
 * 插值查找；
 * 原理：跟二分查找基本一样，区别是将中间索引mid由（first+last）/2,即first+1/2*(last - first)，
 *      改为了first+(（findVal-firstVal）/(lastVal-firstVal))*(last-first)
 */
public class InsertValSearch {

    public static void main(String[] args) {
        int[] arr = {1, 5, 8, 8, 8, 9, 11, 12, 12, 15, 15, 18};
        int findVal = 8;
        int index = search(arr, findVal, 0, arr.length - 1);
        if (index > -1) {
            System.out.println("索引为:" + index);
        } else {
            System.out.println(findVal + "不存在与数组中");
        }
        ArrayList<Integer> list = searchAll(arr, findVal, 0, arr.length - 1);
        if (list != null) {
            System.out.println("索引为:" + list);
        } else {
            System.out.println(findVal + "不存在于数组中");
        }
    }


    public static int search(int[] arr, int findVal, int first, int last) {
        //如果首下标大于尾下标，则说明查询已结束，返回-1
        if (first > last) {
            return -1;
        }
        int firstVal = arr[first];
        int lastVal = arr[last];
        int mid = first+((findVal-firstVal)/(lastVal-firstVal))*(last-first);
        //如果查找数大于中值，则往右查找
        if (findVal > arr[mid]) {
            return search(arr, findVal, mid + 1, last);
        } else if (findVal < arr[mid]) {
            return search(arr, findVal, first, mid - 1);
        } else {
            return mid;
        }
    }

    public static ArrayList<Integer> searchAll(int[] arr, int findVal, int first, int last) {
        //如果首下标大于尾下标，则说明查询已结束，返回-1
        if (first > last) {
            return null;
        }
        int firstVal = arr[first];
        int lastVal = arr[last];
        int mid = first+((findVal-firstVal)/(lastVal-firstVal))*(last-first);
        //如果查找数大于中值，则往右查找
        if (findVal > arr[mid]) {
            return searchAll(arr, findVal, mid + 1, last);
        } else if (findVal < arr[mid]) {
            return searchAll(arr, findVal, first, mid - 1);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid;
            while (temp >= 0 && arr[temp] == findVal) {
                list.add(temp);
                temp--;
            }
            temp = mid + 1;
            while (temp <= last && arr[temp] == findVal) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
