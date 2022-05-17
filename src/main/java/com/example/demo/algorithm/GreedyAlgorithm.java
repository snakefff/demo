package com.example.demo.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 贪心算法
 * 实现问题：用最少广播站覆盖所有地区问题
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm();
        Map<String, Set<String>> broadcasts = new HashMap<>();
        Set<String> k1 = new HashSet<>();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");
        broadcasts.put("k1", k1);
        Set<String> k2 = new HashSet<>();
        k2.add("广州");
        k2.add("北京");
        k2.add("深圳");
        broadcasts.put("k2", k2);
        Set<String> k3 = new HashSet<>();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");
        broadcasts.put("k3", k3);
        Set<String> k4 = new HashSet<>();
        k4.add("上海");
        k4.add("天津");
        broadcasts.put("k4", k4);
        Set<String> k5 = new HashSet<>();
        k5.add("杭州");
        k5.add("大连");
        k5.add("茂名");
        k5.add("武汉");
        k5.add("成都");
        broadcasts.put("k5", k5);
        Set<String> allArea = greedyAlgorithm.getGreedyGroup(broadcasts);
        System.out.println("最优组合为：" + allArea);

    }

    /**
     * 通过贪心算法获得最优的广播组合，使得广播覆盖所有地区
     * @param broadcasts 所有的广播站
     * @return 广播站组合key
     */
    public Set<String> getGreedyGroup(Map<String, Set<String>> broadcasts) {
        Set<String> allArea = getAllArea(broadcasts); //保存所有扔未被覆盖的地区
        Set<String> keys = new HashSet<>(); //保存最优广播key
        while (allArea.size() != 0) {
            String maxKey = null;
            Set<String> commonArea = new HashSet<>(); //临时的公共集合，保存当前广播站与allArea的交集
            for (String key : broadcasts.keySet()) { //获得maxKey
                commonArea.clear();
                commonArea.addAll(broadcasts.get(key));
                commonArea.retainAll(allArea);//交集
                if (commonArea.size() != 0) { //当前广播站覆盖的地区与未覆盖地区有交集
                    if (maxKey == null) {
                        maxKey = key;
                    } else {
                        Set<String> maxValue = broadcasts.get(maxKey);
                        maxValue.retainAll(allArea);
                        if (commonArea.size() > maxValue.size()) {
                            maxKey = key;
                        }
                    }
                }
            }
            if (maxKey != null) {
                keys.add(maxKey);
                allArea.removeAll(broadcasts.get(maxKey));
            } else { //所有广播站都和公共区域没有了交集。这种情况一般不会出现，用于预防特殊情况可能出现的死循环
                break;
            }
        }
        return keys;
    }


    /**
     * 获取所有的地区名称
     *
     * @param broadcasts 传入所有广播站
     * @return 所有地区的Set集合
     */
    public Set<String> getAllArea(Map<String, Set<String>> broadcasts) {
        Set<String> allArea = new HashSet<>();
        for (Map.Entry<String, Set<String>> broadcast : broadcasts.entrySet()) {
            Set<String> area = broadcast.getValue();
            allArea.addAll(area);
        }
        return allArea;
    }


}
