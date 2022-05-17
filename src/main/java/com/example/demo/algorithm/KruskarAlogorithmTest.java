package com.example.demo.algorithm;

import java.util.*;

public class KruskarAlogorithmTest {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Graph graph = new Graph();
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        //克鲁斯卡尔算法的邻接矩阵
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        graph.createGraph(vexs, matrix);
        Edge[] minTree = graph.createMinTree();
        System.out.println("最小生成树：" + Arrays.toString(minTree));
    }


}

class Graph {
    private static final int INF = Integer.MAX_VALUE;
    char[] vertexs; //顶点
    Edge[] edges;  //边
    int edgeNum;

    public void createGraph(char[] vertexs, int[][] weight_matrix) {
        int len = vertexs.length;
        int edgeIndex = 0;
        //创建顶点
        this.vertexs = vertexs;
        //创建边
        edgeNum = getEdgeNum(weight_matrix);
        edges = new Edge[edgeNum];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (weight_matrix[i][j] != INF) {
                    edges[edgeIndex++] = new Edge(vertexs[i], vertexs[j], weight_matrix[i][j]);
                }
            }
        }
    }

    /**
     * 根据Kruskar算法 求出最小生成树
     *
     * @return 最小生成树的边的集合
     */
    public Edge[] createMinTree() {
        Edge[] minTree = new Edge[vertexs.length - 1]; //最小生成树
        int index = 0;                       //最小生成树的下标
        int[] ends = new int[edgeNum];      //保存当前最小生成树的每个顶点的下一个顶点
        sortEdges();//边排序
        for (Edge edge : edges) {
            //获取顶点的数字下标
            int start = 0;
            int end = 0;
            for (int v = 0; v < vertexs.length; v++) {
                if (vertexs[v] == edge.start) {
                    start = v;
                }
                if (vertexs[v] == edge.end) {
                    end = v;
                }
            }
            //判断两个顶点的最终顶点是否相同，如果相同，不作处理，如果不同，加入到最小生成树中
            int e1 = getEnd(ends, start);
            int e2 = getEnd(ends, end);
            if (e1 != e2) {                 //说明没有形成回路
                ends[e1] = end;
                minTree[index] = edge;
                index++;
            }
        }
        return minTree;
    }

    /**
     * @param ends 保存当前最小生成树的每个顶点的最终顶点
     * @param i    字符编号
     */
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }


    /**
     * 对边根据weight大小进行排序
     */
    public void sortEdges() {
        System.out.println("排序前：" + Arrays.toString(edges));
        Comparator<Edge> comparator = Comparator.comparingInt(o -> o.weight);
        Arrays.sort(edges, comparator);
        System.out.println("排序后：" + Arrays.toString(edges));
    }

    public void showEdges() {
        System.out.println("边：" + Arrays.toString(edges));
    }

    public int getEdgeNum(int[][] weight_matrix) {
        edgeNum = 0;
        int len = weight_matrix.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (weight_matrix[i][j] != INF)
                    edgeNum++;
            }
        }
        return edgeNum;
    }
}

/**
 * 边
 */
class Edge implements Comparator<Edge> {
    char start;
    char end;
    int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "<" + start +
                "," + end +
                ">=" + weight +
                '}';
    }

    @Override
    public int compare(Edge o1, Edge o2) {
        return o1.weight - o2.weight;
    }

}
