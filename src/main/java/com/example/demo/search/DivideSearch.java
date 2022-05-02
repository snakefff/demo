package com.example.demo.search;

import java.util.ArrayList;

/**
 * 二分查找
 * 前提：查找的数组必须是有序的
 */
public class DivideSearch {

    public static void main(String[] args) {
        int[] arr = {1, 5, 8, 8, 8, 9, 11, 12, 12, 15, 15, 18};
        int num = 8;
        int index = search(arr, num, 0, arr.length - 1);
        if (index > -1) {
            System.out.println("索引为:" + index);
        } else {
            System.out.println(num + "不存在与数组中");
        }
        ArrayList<Integer> list = searchAll(arr, num, 0, arr.length - 1);
        if (list != null) {
            System.out.println("索引为:" + list);
        } else {
            System.out.println(num + "不存在于数组中");
        }
    }

    /**
     * 查找到一个就返回下标
     * 前提，默认数组已经升序排序好
     *
     * @param arr   已升序排序的数组
     * @param num   查找的数
     * @param first 查找的首下标
     * @param last  查找的尾下标
     * @return 返回值，查到返回查到一个索引，没查到返回-1
     */
    public static int search(int[] arr, int num, int first, int last) {
        //如果首下标大于尾下标，则说明查询已结束，返回-1
        if (first > last) {
            return -1;
        }
        int mid = (first + last) / 2;
        //如果查找数大于中值，则往右查找
        if (num > arr[mid]) {
            return search(arr, num, mid + 1, last);
        } else if (num < arr[mid]) {
            return search(arr, num, first, mid - 1);
        } else {
            return mid;
        }
    }
    //递归查找值等于num的所有下标
    public static ArrayList<Integer> searchAll(int[] arr, int num, int first, int last) {
        //如果首下标大于尾下标，则说明查询已结束，返回-1
        if (first > last) {
            return null;
        }
        int mid = (first + last) / 2;
        //如果查找数大于中值，则往右查找
        if (num > arr[mid]) {
            return searchAll(arr, num, mid + 1, last);
        } else if (num < arr[mid]) {
            return searchAll(arr, num, first, mid - 1);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid;
            while (temp >= 0 && arr[temp] == num) {
                list.add(temp);
                temp--;
            }
            temp = mid + 1;
            while (temp <= last && arr[temp] == num) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
