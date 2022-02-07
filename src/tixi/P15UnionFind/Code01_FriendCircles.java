package tixi.P15UnionFind;

import java.util.Map;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/27-15:03
 * @Version: 1.0.0
 */
public class Code01_FriendCircles {

    //Leetcode
    //
    // https://leetcode-cn.com/problems/friend-circles/
    // https://leetcode-cn.com/problems/number-of-provinces


    //岛问题
    //
    // 给定一个二维数组matrix，里面的值不是1就是0，
    // 上、下、左、右相邻的1认为是一片岛，
    // 返回matrix中岛的数量


    //有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
    //
    // 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
    //
    // 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
    //
    // 返回矩阵中 省份 的数量。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/number-of-provinces
    // 这种二维数组问题很有可能就是并查集结构

    // 1 0 0 0 1   只遍历上半区
    // 0 1 0 1 0
    // 1 0 1 0 0
    // 0 1 0 1 0
    // 1 0 0 0 1

    public int findCircleNum(int[][] isConnected) {
        UnionFind unionFind = new UnionFind(isConnected.length);
        for(int i=0;i<isConnected.length;i++){
            for(int j=i+1;j<isConnected.length;j++){
                if(isConnected[i][j]==1){//相连才合并
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.sets;
    }
    public  class UnionFind{
        public int[] parent;//parent[i]=k  i的父亲是k;
        public int[] size;//顶点的大小,用来小合大的
        public int[] help;//辅助结构
        public int sets;//一共有多少个集合

        public UnionFind(int N){
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;

            for(int i=0;i<N;i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int findFather(int i){
            int index = 0;
            while (i!=parent[i]){
                help[index++] = i;
                i = parent[i];
            }
            for(int j = index-1;j>=0;j--){
                parent[help[j]] = i;
            }
            return i;
        }
        public void union(int a, int b) {
            int fatherA = findFather(a);
            int fatherB = findFather(b);
            if(fatherA!=fatherB){
                int sizeA = size[fatherA];
                int sizeB = size[fatherB];
                int big = sizeA>=sizeB ? fatherA:fatherB;
                int small = sizeA>=sizeB ? fatherB:fatherA;
                parent[small] = big;
                size[big] = size[big]+size[small];
                size[small] = 0;
                sets--;
            }
        }
    }
}