package com.example.demo.algorithm;

import java.util.Arrays;

public class KruskalAlgorithm {

//代码实现


    private int edgeNum;        // 边的数量
    private char[] vertexs;       // 顶点集合
    private int[][] matrix;    // 邻接矩阵
    private static final int INF = Integer.MAX_VALUE;   // 最大值
    /*
     * 创建图(用已提供的矩阵)
     *
     * 参数说明：
     *     vexs  -- 顶点数组
     *     matrix-- 矩阵(数据)
     */
    public KruskalAlgorithm(char[] vertexs, int[][] matrix) {


        // 初始化"顶点数"和"边数"
        int vlen = vertexs.length;

        // 初始化"顶点"
        this.vertexs = new char[vlen];
        System.arraycopy(vertexs, 0, this.vertexs, 0, vertexs.length);

        // 初始化"边"
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, vlen);

        // 统计"边"
        for (int i = 0; i < vlen; i++)
            for (int j = i+1; j < vlen; j++)
                if (this.matrix[i][j]!=INF)
                    edgeNum++;
    }

    /*
     * 返回ch位置, 返回是第几个顶点
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++)
            if (vertexs[i] == ch)
                return i;
        return -1;
    }
    /*
     * 打印矩阵队列图
     */
    public void print() {
        System.out.print("Martix Graph:\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++)
                System.out.printf("%20d", matrix[i][j]);
            System.out.println();
        }
    }

    /*
     * 克鲁斯卡尔（Kruskal)最小生成树
     */
    public void kruskal() {
        int index = 0;                      // rets数组的索引
        int[] ends = new int[edgeNum];     // 用于保存"已有最小生成树"中每个顶点在该最小树中的终点。
        EData[] rets = new EData[edgeNum];  // 结果数组，保存kruskal最小生成树的边

        // 获取"图中所有的边" 共12条
        EData[] edges = getEdges();
        System.out.println(Arrays.toString(edges) + edges.length);
        // 将边按照"权"的大小进行排序(从小到大)
        sortEdges(edges);

        for (int i=0; i<edgeNum; i++) {
            int p1 = getPosition(edges[i].start);      // 获取第i条边的"起点"的序号
            int p2 = getPosition(edges[i].end);        // 获取第i条边的"终点"的序号

            int m = getEnd(ends, p1);                 // 获取p1在"已有的最小生成树"中的终点
            int n = getEnd(ends, p2);                 // 获取p2在"已有的最小生成树"中的终点
            // 如果m!=n，意味着"边i"与"已经添加到最小生成树中的顶点"没有形成环路
            // 如果形成环路，就不能加入到 rets数组中
            if (m != n) {
                ends[m] = n;                       // 设置m在"已有的最小生成树"中的终点为n
                System.out.println(Arrays.toString(ends));
                rets[index++] = edges[i];           // 保存结果
            }
        }

        // 统计并打印"kruskal最小生成树"的信息
        int length = 0;
        for (int i = 0; i < index; i++)
            length += rets[i].weight;
        System.out.printf("Kruskal=%d: ", length);
        for (int i = 0; i < index; i++)
            System.out.printf("(%c,%c) ", rets[i].start, rets[i].end);
        System.out.print("\n");
    }

    /*
     * 获取图中的边,放到 EData[] 数组中, 后面我们要遍历该数组
     * 存放形式 [['A','B', 12],['B','F',7]]
     */
    private EData[] getEdges() {
        int index=0;
        EData[] edges = new EData[edgeNum];
        for (int i=0; i < vertexs.length; i++) {
            for (int j=i+1; j < vertexs.length; j++) {
                if (matrix[i][j]!=INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /*
     * 对边按照权值大小进行排序(由小到大)
     */
    private void sortEdges(EData[] edges) {

        for (int i=0; i<edges.length - 1; i++) {
            for (int j=0; j<edges.length - 1 - i; j++) {

                if (edges[j].weight > edges[j+1].weight) {
                    // 交换"边i"和"边j"
                    EData tmp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = tmp;
                }
            }
        }
    }

    /*
     * 获取i的终点, 这个vends 就是存放终点的数组，是在遍历边的过程中，逐步得到的
     * i: 表示 传入的顶点的序号(索引)
     * 返回i顶点对应的顶点索引值
     * 第一次 E,F
     * getEnd(.., 4) => 4
     * getEnd(.., 5) => 5
     * 将<E,F> 加入到 rets 后
     * ends= [0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0] 即 ends[4] = 5 序号为4的顶点E的顶点是序号为5的顶点F
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) //可能是传递的,一直找到ends[i] = 0，才传递.
            i = ends[i];
        return i;
    }


    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        //克鲁斯卡尔算法的邻接矩阵
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        //原理普利姆算法的邻接矩阵
//        int matrix[][] = {
//                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
//         /*A*/ {   10000,  5, 7, 10000, 10000,  10000,  2},
//         /*B*/ {  5,   10000,  10000, 9, 10000,   10000, 3},
//         /*C*/ { 7,  10000,   10000,   10000,   8,   10000, 10000},
//         /*D*/ { 10000, 9,   10000,   10000,   10000, 4, 10000},
//         /*E*/ { 10000, 10000,   8,   10000,   10000,   5,   4},
//         /*F*/ {  10000,   10000,   10000, 4,   5,   10000,   6},
//         /*G*/ {  2, 3, 10000, 10000,   4,   6,   10000}};



        // 自定义"图"(输入矩阵队列)
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vexs, matrix);

        kruskalAlgorithm.print();   // 打印图
        kruskalAlgorithm.kruskal();   // Kruskal算法生成最小生成树
    }
}

//EData 的一个对象，表示一条边, 形式 <E,F> 3
class EData {
    char start; // 边的起点
    char end;   // 边的终点
    int weight; // 边的权重

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData [start=" + start + ", end=" + end + ", weight=" + weight + "]";
    }


}

