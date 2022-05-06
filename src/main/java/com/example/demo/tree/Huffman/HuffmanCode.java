package com.example.demo.tree.Huffman;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
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
    //    Map<Byte, String> huffmanMap;
    Map<Byte, String> huffmanCodeMap = new HashMap<>();
    StringBuffer route = new StringBuffer();

    public static void main(String[] args) {
        HuffmanCode huffmanCode = new HuffmanCode();
        //压缩和解压一个字符串
        String word = "i like like like java do you like a java011";
        //1 将字符串转换为byte[]
        byte[] bytes = word.getBytes();
        System.out.println("初始bytes[]:" + Arrays.toString(bytes));
        byte[] zipBytes = huffmanCode.zip(bytes);
        System.out.println("压缩后：" + Arrays.toString(zipBytes));
        byte[] unzipBytes = huffmanCode.unzip(zipBytes, huffmanCode.huffmanCodeMap);
        System.out.println("解压后：" + Arrays.toString(unzipBytes));
        //压缩文件
        System.out.println("文件压缩开始");
        huffmanCode.zipFile("E:\\java\\zipTest\\review-20220501.md","E:\\java\\zipTest\\review-20220501.zip");
        //解压文件
        System.out.println("文件解压开始");
        huffmanCode.unzipFile("E:\\java\\zipTest\\sunset.zip","E:\\java\\zipTest\\sunset2.webp.jpg");
    }

    /**
     * 解压文件
     * @param src 要解压文件的路径
     * @param unzipFile 解压后的路径和文件名
     */
    public void unzipFile(String src,String unzipFile){
        FileInputStream  fi = null;
        ObjectInputStream ofi = null;
        FileOutputStream fo = null;

        try {
            fi = new FileInputStream(src);
            ofi = new ObjectInputStream(fi);
            byte[] bytes = (byte[]) ofi.readObject();
            System.out.println("文件解压前："+bytes.length);
            Map<Byte, String> huffmanMap = (Map<Byte, String>) ofi.readObject();
            byte[] unzipBytes = unzip(bytes, huffmanMap);
            System.out.println("文件解压后："+unzipBytes.length);
            fo=new FileOutputStream(unzipFile);
            fo.write(unzipBytes);
            System.out.println("解压完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                assert fo != null;
                fo.close();
                ofi.close();
                fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 压缩文件
     * @param src 压缩文件的路径
     * @param zipFile 压缩后的路径和文件名
     */
    public void zipFile(String src,String zipFile) {
        FileInputStream fi =null;
        FileOutputStream fo = null;
        ObjectOutputStream oos = null;
        byte[] zipBytes;
        try {
            fi = new FileInputStream(src);
            byte[] bytes = new byte[fi.available()];
            int length = fi.read(bytes);
            if (length != -1){ //读到的内容不为空，压缩
                System.out.println("文件压缩前："+ bytes.length);
                zipBytes = zip(bytes);
                System.out.println("文件压缩后："+ zipBytes.length);
                System.out.println("文件压缩后的赫夫曼编码表："+ huffmanCodeMap);
                fo = new FileOutputStream(zipFile);
                oos = new ObjectOutputStream(fo);
                oos.writeObject(zipBytes); //写入压缩后的bytes
                oos.writeObject(huffmanCodeMap); //写入赫夫曼编码表
                System.out.println("压缩完成~~");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                assert fo != null;
                fo.close();
                assert oos != null;
                oos.close();
                fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * @param bytes      压缩的字符串的bytes,注意点：倒数第一位不是具体数值，而是用来统计倒数的二位的长度的
     * @param huffmanMap 赫斯曼编码表
     * @return 解压后的bytes字符串
     */
    public byte[] unzip(byte[] bytes, Map<Byte, String> huffmanMap) {
        //1 将bytes[73, 9, 85, 84, 66...]转换为StringBuffer=010010010000100101010101010101000100001....
        String bString;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length - 1; i++) {
            int b = bytes[i];
            b |= 256;  //b=73=100 1001,256=1 0000 0000,两者按位与再取后八位可以将73延长为8位的二进制数：0100 1001
            bString = Integer.toBinaryString(b);  //1 0100 1001
            if (i != bytes.length - 2) { //i不是倒数第二位
                bString = bString.substring(bString.length() - 8); //取最后8位-->0100 1001
            } else { //i是倒数第二位，需要根据倒数第一位来取长
                int len = bytes[i + 1]; //i+1是倒数第一位，也就是最后一位，记录的是长度
                bString = bString.substring(bString.length() - len);//取最后len位
            }
            stringBuffer.append(bString);
        }
//        System.out.println("" + stringBuffer);
        //2 将赫夫曼编码表huffmanMap键值转换
        Map<String, Byte> revHuffmanMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanMap.entrySet()) {
            revHuffmanMap.put(entry.getValue(), entry.getKey());
        }
//        System.out.println("反转后的赫夫曼编码表：" + revHuffmanMap);
        //3 创建集合list保存byte
        List<Byte> list = new ArrayList<>();
        //4 遍历stringBuffer，根据revHuffmanMap取出对应的byte，存放到list中
        for (int i = 0; i < stringBuffer.length(); ) {
            int len = 0; //计算子字符串的长度
            Byte b = null; //保存从revHuffmanMap中取出来的值
            while ((i + len) < stringBuffer.length() && b == null) {
                len += 1;
                b = revHuffmanMap.get(stringBuffer.substring(i, i + len));
            }
            //while循环结束后，一般就已经取到值了
            if ( b != null){
                list.add(b);
            }
            i += len;
        }
//        System.out.println("解压后list:"+ list);
        //list转换为byte[]
        byte[] decodedBytes = new byte[list.size()];
        for (int i = 0;i<list.size();i++){
            decodedBytes[i] = list.get(i);
        }
//        System.out.println("解压后bytes:"+Arrays.toString(decodedBytes));
        return decodedBytes;
    }


    /**
     * 封装赫夫曼压缩
     *
     * @param bytes 字符串的byte[]形式
     * @return 压缩后的bytes
     */
    public byte[] zip(byte[] bytes) {
        //2 统计byte[]中每个字符的个数,将字符和个数作为参数保存为map
        Map<Byte, Integer> map = countByte(bytes);
        //3 把map转换成Node节点，通过Node节点创建赫夫曼树,并返回赫夫曼树的头节点
        HuffNode root = createHuffmanTree(map);
        //4 读取赫夫曼树，往左走为0，右走为1；形成赫夫曼编码，并以字符为key,路径组合为value保存为赫夫曼编码表
        //如{121=0101000, 100=01010001, 111=010100101, 101=0101010, 105=0100, 106=00010, 117=01010010, 118=000101, 107=01010101, 108=000, 97=01001, 32=001}
        huffmanCodeMap = createHuffmanMap(root,null, route);
        //5 根据赫夫曼编码
        return zip(bytes, huffmanCodeMap);
    }


    /**
     * 根据赫夫曼编码表将bytes转换为二进制形式，并且压缩
     *
     * @param bytes          原字符串的byte[]形式
     * @param huffmanCodeMap 赫夫曼编码表
     * @return 压缩后的bytes
     */
    public byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodeMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodeMap.get(b));
        }
//        System.out.println(stringBuilder);
        //压缩
        int length;  //记录数据所需要的长度
        if (stringBuilder.length() % 8 == 0) {
            length = stringBuilder.length() / 8;
        } else {
            length = stringBuilder.length() / 8 + 1;
        }
        byte[] newByte = new byte[length + 1];  //length+1,临时增加一位来记录最后一段字符串的长度，方能正确恢复
        for (int i = 0; i < length; i++) {
            if ((i * 8 + 8) < stringBuilder.length()) {
                //取出八位，并且把八位二进制转为十进制,再由int转换为byte
                newByte[i] = (byte) Integer.parseInt(stringBuilder.substring(i * 8, i * 8 + 8), 2);
            } else {//最后几位已经不足8位，进行额外处理
                String lastString = stringBuilder.substring(i * 8);
                newByte[i] = (byte) Integer.parseInt(lastString, 2);
                newByte[i + 1] = (byte) lastString.length();
            }
        }
        return newByte;

    }

    //统计byte[]中每个字符的个数,将字符和个数作为参数保存为map
    public Map<Byte, Integer> countByte(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            //map.merge(b, 1, Integer::sum);
            Integer count = map.get(b);
            if (count != null) {
                map.put(b, count + 1);
            } else {
                map.put(b, 1);
            }
        }
        return map;
    }

    public HuffNode createHuffmanTree(Map<Byte, Integer> map) {
        if (map.isEmpty()) {
            return null;
        }
        ArrayList<HuffNode> list = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
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


    //遍历赫夫曼树的所有子节点，生成赫夫曼树编码表
    public Map<Byte, String> createHuffmanMap(HuffNode huffNode,String next, StringBuffer route) {
        if (huffNode == null) {
            return null;
        }
        StringBuffer myRoute = new StringBuffer(route);
        if (next!= null){
            myRoute.append(next);
        }
        //根据key是否为空保存赫夫曼树编码表
        Byte key = huffNode.getKey();
        if (key != null) {
            huffmanCodeMap.put(key, myRoute.toString());
        }
        if (huffNode.getLeft() != null) {
            createHuffmanMap(huffNode.getLeft(),"0", myRoute);
        }
        if (huffNode.getRight() != null) {
            createHuffmanMap(huffNode.getRight(),"1", myRoute);
        }

        return huffmanCodeMap;
    }
}
