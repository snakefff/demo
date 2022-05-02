package com.example.demo.tree.Huffman;

import java.util.*;

/**
 * 赫夫曼编码
 * 将一个字符串"i like like like java do you like a java"压缩，利于保存和传递
 * 1 将字符串转换为byte[]
 * 2 统计byte[]中每个字符的个数,将字符和个数作为参数保存为map
 * 3 把map转换成Node节点，通过Node节点创建赫夫曼树
 * 4 读取赫夫曼树，往左走为0，右走为1；形成赫夫曼编码，并以字符为key,路径组合为value保存赫夫曼编码表
 * 5 将byte[]根据赫夫曼编码表转换成10010101...的形式
 * 6 将编码每八位转换为一个十进制数保存到一个byte[]中
 */
public class HuffmanCode {

    public static void main(String[] args) {
        HuffmanCode huffmanCode = new HuffmanCode();
        String word = "i like like like java do you like a java";
        //1 将字符串转换为byte[]
        byte[] bytes = word.getBytes();
        //2 统计byte[]中每个字符的个数,将字符和个数作为参数保存为map
        Map<String, Integer> map = huffmanCode.countByte(bytes);
        System.out.println(map.toString());
        //3 把map转换成Node节点，通过Node节点创建赫夫曼树,并返回赫夫曼树的头节点
        HuffNode root = huffmanCode.createHuffmanTree(map);
        //4 读取赫夫曼树，往左走为0，右走为1；形成赫夫曼编码，并以字符为key,路径组合为value保存赫夫曼编码表
        Map<String, StringBuffer> huffmanMap = huffmanCode.createHuffmanMap(root, huffmanCode.route);
        System.out.println(huffmanMap.toString());

    }

    //统计byte[]中每个字符的个数,将字符和个数作为参数保存为map
    public Map<String, Integer> countByte(byte[] bytes) {
        Map<String, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            //map.merge(b, 1, Integer::sum);
            Integer count = map.get(String.valueOf(b));
            if (count != null) {
                map.put(String.valueOf(b), count + 1);
            } else {
                map.put(String.valueOf(b), 1);
            }
        }
        return map;
    }

    public HuffNode createHuffmanTree(Map<String, Integer> map) {
        if (map.isEmpty()) {
            return null;
        }
        ArrayList<HuffNode> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(new HuffNode(entry.getKey(), entry.getValue()));
        }
        Collections.sort(list);

        while (list.size() > 1) {
            HuffNode minNode1 = list.get(0);
            HuffNode minNode2 = list.get(1);
            HuffNode node = new HuffNode(minNode1.getValue() + minNode2.getValue());
            node.setLeft(minNode1);
            node.setRight(minNode2);
            list.remove(minNode1);
            list.remove(minNode2);
            list.add(node);
            Collections.sort(list);
        }

        return list.get(0);
    }



    Map<String, StringBuffer> huffmanCodeMap = new HashMap<>();
    StringBuffer route = new StringBuffer();

    //遍历赫夫曼树的所有子节点，生成赫夫曼树编码表
    public Map<String,StringBuffer> createHuffmanMap(HuffNode huffNode,StringBuffer route) {
        if (huffNode == null) {
            return null;
        }
        StringBuffer myRoute = new StringBuffer(route);
        //根据key是否为空保存赫夫曼树编码表
        String key = huffNode.getKey();
        if (key != null) {
            huffmanCodeMap.put(key,myRoute);
        }
        if (huffNode.getLeft() != null) {
            myRoute.append(0);
            createHuffmanMap(huffNode.getLeft(),myRoute);
        }
        if (huffNode.getRight() != null) {
            myRoute.append(1);
            createHuffmanMap(huffNode.getRight(),myRoute);
        }

        return huffmanCodeMap;
    }
}
