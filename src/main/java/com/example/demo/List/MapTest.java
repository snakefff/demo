package com.example.demo.List;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapTest {

    public void test(){
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("name1","jeb1");
        map1.put("name2","jeb2");
        map1.put("name3","jeb3");
        map1.put("name4","jeb4");
        map1.put("name5","jeb5");

        //iterate by entry
        System.out.println("map-entry");
        for (Map.Entry<String, Object> stringObjectEntry : map1.entrySet()) {

            System.out.println(stringObjectEntry);
        }
        //iterate by key
        System.out.println();
        System.out.println("map-key");
        Iterator<String> keyIterator = map1.keySet().iterator();
        while (keyIterator.hasNext()){
            String key = keyIterator.next();
            System.out.println("key:" +
                    ""+key+" value:"+map1.get(key));
        }

        //iterate by foreach
        System.out.println("iterate by foreach");
        map1.forEach((key,value)->{
            System.out.println("key:"+key + "value:"+ value);
        });
    }

    public static void main(String[] args) {
        MapTest mapTest = new MapTest();
        mapTest.test();
    }
}
