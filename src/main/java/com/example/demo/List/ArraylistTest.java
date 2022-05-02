package com.example.demo.List;

import java.util.ArrayList;
import java.util.List;

public class ArraylistTest {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(){
        try {
            List list = new ArrayList<String>();
            list.add("张三");
            list.add("lisi");
            list.add("wangwu");
            System.out.println("users:"+ list);
            System.out.println("size:" + list.size());
            System.out.println("to array"+list.toArray());
            System.out.println("单独列出list中的字符串");
            for (Object o : list) {
                System.out.println((list.indexOf(o)+1)+":"+o.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new ArraylistTest().add();
    }
}
