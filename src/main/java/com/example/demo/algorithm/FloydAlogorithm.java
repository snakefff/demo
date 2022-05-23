package com.example.demo.algorithm;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 *  弗洛伊德算法(Floyd)计算图中各个顶点之间的最短路径
 *  优点：简单
 *  缺点：用了三个循环，时间复杂度为n^3,效率较低
 */
public class FloydAlogorithm {
    public static final int N = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
                {0,5,7,N,N,N,2},
                {5,0,N,9,N,N,3},
                {7,N,0,N,8,N,N},
                {N,9,N,0,N,4,N},
                {N,N,8,N,0,5,4},
                {N,N,N,4,5,0,6},
                {2,3,N,N,4,6,0}};
        Graph1 graph1 = new Graph1(vertex,matrix);
        graph1.show();
        graph1.floyd();
        System.out.println("after floyd");
        graph1.show();
    }
}


class Graph1 {
    char[] vertex;
    int[][] distance;
    int[][] preVertex;
    int len;
    public static final int N = Integer.MAX_VALUE;
    public Graph1(char[] vertex, int[][] matrix){
        this.vertex = vertex;
        this.distance = matrix;
        this.len = vertex.length;
        this.preVertex = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(preVertex[i],i);
        }
    }
    public void show(){
        for (int i = 0; i < len; i++) {
            System.out.println(Arrays.toString(distance[i]));
            System.out.println(Arrays.toString(preVertex[i]));
        }

    }

    public void floyd(){
        for (int i = 0; i < len; i++) {  //中间顶点
            for (int j = 0; j < len; j++) { //开始顶点
                for (int k = 0; k < len; k++) { //结束顶点
                    if (distance[j][i]!= N && distance[i][k] !=N &&  distance[j][i] + distance[i][k] < distance[j][k]){
                        distance[j][k] = distance[j][i] + distance[i][k];
                        distance[k][j] = distance[j][i] + distance[i][k];
                        preVertex[j][k] = i; //结束顶点的上一个顶点设置为中间顶点
                        preVertex[k][j] = i; //结束顶点的上一个顶点设置为中间顶点
                     }
                }
            }
        }
    }

}
