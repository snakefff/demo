package com.example.demo.algorithm;

import java.util.Arrays;

/**
 * 通过kmp算法查找string字符串的匹配值
 */
public class KMPAlogorithm {

    public static void main(String[] args) {
        KMPAlogorithm kmpAlogorithm = new KMPAlogorithm();
        String mainString = "ABCABCABCABCCBC0ABCABCABABCABCCBC0ABCABCABCDCDEF";
        String subString = "ABCABCCBC0ABCABCABCD";
        int index = kmpAlogorithm.KMPSearch(mainString,subString);
        int index1 = KMPSearch1(mainString,subString);
        System.out.println("下标：" + index);
        System.out.println("教程下标：" + index1);

        int[] next = kmpAlogorithm.kmpNext(subString);
        int[] next1 = kmpNext1(subString);
        System.out.println("我的next[]:" + Arrays.toString(next));
        System.out.println("老师的next[]:" + Arrays.toString(next1));

    }

    /**
     * kmp查询子字符串
     * @param mianString 主字符串
     * @param subString 子字符串
     * @return 下标或-1
     */
    public int KMPSearch(String mianString, String subString) {
        int[] next = kmpNext(subString);
        for (int i = 0, j = 0; i < mianString.length(); i++) {
            //j大于0代表至少有一个值匹配上了，当前值没匹配上，j归0，i回退。
            // 不能用if,因为i跟当前j不匹配后，还需要判断是否跟上一层j匹配，直到j的值跟i匹配或者j==0;
            while (j>0 && mianString.charAt(i) != subString.charAt(j)) {
                j = next[j-1];  //j-1是当前比较值j的前一位
            }
            if (mianString.charAt(i) == subString.charAt(j)) {
                j++;
            }
            if (j == subString.length()) {  //匹配上了
                return i - j + 1;
            }
        }
        return -1;

    }
    //教程的版本
    //str文本串  dest 模式串
    public static int KMPSearch1(String str, String dest){
        int[] next = kmpNext1(dest);
        for(int i = 0, j = 0; i < str.length(); i++){
            while(j > 0 && str.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if(str.charAt(i) == dest.charAt(j)){
                j++;
            }
            if(j == dest.length()){
                return i-j+1;
            }
        }
        return -1;
    }


    /**
     * 获得子字符串的的部分匹配标
     *
     * @param subString 子字符串
     * @return 部分匹配表
     */
    public int[] kmpNext(String subString) {
        int len = subString.length();
        int[] next = new int[len];
        next[0] = 0; //第一个字符必然和自身相等，直接赋0
        int i = 0; //字符串的下标
        for (int j = 1; j < len; j++) { //从第二个数开始比较，j是跟第一数比较的下标
            while (i>0 && subString.charAt(i) != subString.charAt(j)) {//没有匹配成功
                i = next[i-1];
            }
            if (subString.charAt(i) == subString.charAt(j)) { //匹配成功，进入下一个字符的匹配
                i++;
                next[j] = i;
            }
        }
        return next;
    }

    /**
     * 教程里面的写法
     *
     * @param dest 子字符串
     * @return 部分匹配标
     *
     */
    public static int[] kmpNext1(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }


}
