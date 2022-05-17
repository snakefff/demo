package com.example.demo.algorithm;

public class PrimAlogorithm {
    private static final int N = Integer.MAX_VALUE; //常量，代表边的最大值，即这条边不存在
    
    public static void main(String[] args) {
        MyGraph myGraph = new MyGraph();
        char[] vertexs = new char[]{'A','B','C','D','E','F','G'}; //所有顶点
        int[][] map_matrix = new int[][]{
                {N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N},
        };
        myGraph.createGraph(vertexs,map_matrix);
        myGraph.prim(1);
    }
}


class MyGraph{
    char[] vertexs; //顶点
    int vertNum;
    int[][] weight; //边的权值集合
    private static final int N = Integer.MAX_VALUE; //常量，代表边的最大值，即这条边不存在
    public MyGraph(){
    }
    public MyGraph(int vertNum){
        this.vertNum = vertNum;
        this.vertexs = new char[vertNum];
        this.weight = new int[vertNum][vertNum];
    }
    /**
     * 以v为顶点开始显示图
     */
    public void createGraph(char[] vertexs,int[][] weight){
        this.vertNum = vertexs.length;
        this.vertexs=vertexs;
        this.weight = weight;
    }

    /**
     * prim算法求出最小路径
     */

    public void prim(int begin){
        int[] visited = new int[vertNum]; //判断顶点是否被访问过
        //初始化visited,0为未访问，1为已访问
        visited[begin] = 1; //标记开始顶点为已访问
        int h1 = -1; //临时保存开始顶点
        int h2 = -1; //临时保存结束顶点
        int minweight = N; //当前最小权值，初始化为最大值N

        for (int i = 1; i < vertNum; i++) { //需要生成vertNum -1 条边，所以开始索引i定为1
            //获得一条权值最小的边
            for (int j = 0; j < vertNum; j++) {  //已访问的顶点下标
                for (int k = 0; k < vertNum; k++) {  //未访问的顶点下标
                    if (visited[j] == 1 && visited[k] == 0 && weight[j][k] < minweight) { //当前值小于之前的最小值
                        minweight = weight[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            visited[h2] = 1;
            System.out.println("添加边：<" +vertexs[h1]+","+vertexs[h2]+">="+minweight );
            h1 = -1;
            h2 = -1;
            minweight = N;
        }
    }
}