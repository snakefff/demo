package com.example.demo.algorithm;

import java.util.Arrays;

public class KMPAlgorithmTest {
    public static void main(String[] args) {
        KMPAlgorithmTest kmpAlgorithmTest = new KMPAlgorithmTest();
        String mainString = "abeicabc abcabccabcabc kdasdf";
        String subString = "abcabccabcabc";
        int index = kmpAlgorithmTest.kmpSearch(mainString, subString);
        if (index == -1) {
            System.out.println("没找到对应的字符串");
        } else {
            System.out.println("字符串的下标为：" + index);
        }
    }

    public int kmpSearch(String mainString, String subString) {
        int[] next = kmpNext(subString);
        System.out.println("next[]=" + Arrays.toString(next));
        for (int i = 0, j = 0; i < mainString.length(); i++) { //i是mainString的下标，j是subString的下标
            while (j > 0 && mainString.charAt(i) != subString.charAt(j)) {
                j = next[j - 1];
            }
            if (mainString.charAt(i) == subString.charAt(j)) {
                j++;
            }
            if (j == subString.length()) { //找到完全匹配的值，返回
                return i - j + 1;
            }
        }
        return -1;
    }

    public int[] kmpNext(String subString) {
        int len = subString.length();
        int[] next = new int[len];  //保存每个字段的跟前缀索引的相似值
        next[0] = 0;  //第一个不需要比较，直接赋0
        for (int i = 1, j = 0; i < len; i++) { //i从第2位开始，跟字符串的前缀索引比较，由j保存相似值
            while (j > 0 && subString.charAt(i) != subString.charAt(j)) { //当第一位相等时，从第二位开始不等的话，重新赋值j
                j = next[j - 1]; //通过next[]数组推测出新的前缀位置j
            }
            if (subString.charAt(i) == subString.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
