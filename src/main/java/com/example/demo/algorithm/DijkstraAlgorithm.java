package com.example.demo.algorithm;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 迪杰斯特拉算法（Dijkstra）
 * 典型最短路径算法，用于计算一个结点到其他结点的最短路径
 * 如：计算出G村庄到 其它各个村庄的最短距离?
 *
 */
public class DijkstraAlgorithm {
    DGraph graph;
    int[] isVisited;  //判断某个顶点是否被访问过
    int[] preVisitedNodes; //记录上一个顶点的下标
    int[] distances; //记录当前节点到开始节点的最短距离
    private static final int N=65535;

    public static void main(String[] args) {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        char[] vertex=new char[]{'A','B','C','D','E','F','G'}; //顶点数组
        int[][] matrix=new int[7][];     //邻接矩阵

        matrix[0]=new int[]{0,5,7,N,N,N,2};
        matrix[1]=new int[]{5,0,N,9,N,N,3};
        matrix[2]=new int[]{7,N,0,N,8,N,N};
        matrix[3]=new int[]{N,9,N,0,N,4,N};
        matrix[4]=new int[]{N,N,8,N,0,5,4};
        matrix[5]=new int[]{N,N,N,4,5,0,6};
        matrix[6]=new int[]{2,3,N,N,4,6,0};
        dijkstraAlgorithm.graph = new DGraph(vertex,matrix);
        dijkstraAlgorithm.dijkstra(6);
        System.out.println("是否访问："+ Arrays.toString(dijkstraAlgorithm.isVisited));
        System.out.println("上一个顶点：" + Arrays.toString(dijkstraAlgorithm.preVisitedNodes));
        System.out.println("目前最短距离：" + Arrays.toString(dijkstraAlgorithm.distances));


    }

    /**
     * 求出最短路径
     * @param begin  开始顶点beginIndex
     */
    public void dijkstra(int begin){
        //判断图是否已经创建完成
        if (this.graph == null){
            System.out.println("请先创建图");
            return;
        }
        //顶点个数
        int vertexNum = graph.getVertexNum();
        //初始化isVisited
        isVisited = new int[vertexNum];
        isVisited[begin] = 1;
        //初始化preVisitedNodes
        preVisitedNodes = new int[vertexNum];
        //初始化distances
        distances = new int[vertexNum];
        Arrays.fill(distances,N);
        //处理开始顶点--读取matrix,获得与开始顶点连通的顶点
        List<Integer> list = new LinkedList<>();  //list模拟队列
        int[][] matrix = graph.getEdge_matrix();
        for (int i = 0; i < vertexNum; i++) {
            if(matrix[begin][i] != N){
                preVisitedNodes[i] = begin;
                distances[i] = matrix[begin][i];
                list.add(i);
            }
        }
        //通过队列处理其他顶点
        System.out.println("队列：" + list);
        int temp; //临时节点，代表当前处理的顶点
        while(list.size()>0){
             temp = list.get(0);
            //该顶点还没被访问过,处理
             if (isVisited[temp] == 0 ){
                 for (int i = 0; i < vertexNum; i++) {
                     if (matrix[temp][i] != N){
                         if (distances[i] > distances[temp] + matrix[temp][i]){ //i当前最小距离目前较大
                             preVisitedNodes[i] = temp;
                             distances[i] = distances[temp]+matrix[temp][i];
                         }
                         list.add(i);
                     }
                 }
                 isVisited[temp] = 1;
             }
            list.remove(0);
        }

    }

    /**
     * 重载dijkstra
     * @param graph 图
     * @param index 开始顶点
     */
    public void dijkstra(DGraph graph ,int index){
        this.graph = graph;
        dijkstra(index);
    }


}
class DGraph{
    private char[] vertex;  //顶点
    private int[][] edge_matrix; //边的二维数组
    private int vertexNum;

    public DGraph(char[] vertex, int[][] edge_matrix) {
        this.vertex = vertex;
        this.edge_matrix = edge_matrix;
        this.vertexNum = vertex.length;
    }

    public char[] getVertex() {
        return vertex;
    }

    public void setVertex(char[] vertex) {
        this.vertex = vertex;
    }

    public int[][] getEdge_matrix() {
        return edge_matrix;
    }

    public void setEdge_matrix(int[][] edge_matrix) {
        this.edge_matrix = edge_matrix;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public void setVertexNum(int vertexNum) {
        this.vertexNum = vertexNum;
    }
}

