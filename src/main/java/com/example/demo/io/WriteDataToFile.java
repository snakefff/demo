package com.example.demo.io;

import java.io.*;

public class WriteDataToFile {

    public static void writeData(String path,String str) throws IOException {
        BufferedWriter bufferWriter;
        boolean append = true; //是否追加
//        String path = "E:\\linux\\TestData\\selectSortData1";
        File file = new File(path);
        if (file.exists() == false){
            try {
                file.createNewFile();
                file = new File(path);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fileWriter = new FileWriter(file,append);
        bufferWriter = new BufferedWriter(fileWriter);
        bufferWriter.write(str);
        bufferWriter.flush();
        bufferWriter.close();
    }



}
