package tixi.P15UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/27-16:20
 * @Version: 1.0.0
 */
public class Code03_NumberOfIslandsII {

    //给定一个二维数组matrix，里面的值不是1就是0，
    // 上、下、左、右相邻的1认为是一片岛，
    // 返回matrix中岛的数量
    //https://leetcode.com/problems/number-of-islands-ii/

    //二维数组初始全是0，每个positions都是新增一个1，每次都返回一次岛总数
    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        //用不了感染了，只能用并查集了
        UnionFind unionFind = new UnionFind(m,n);
        List<Integer> ans = new ArrayList<>();
        for(int[] position : positions){//[0,1],[0,2],[0,3],[0,4]
            ans.add(unionFind.connect(position[0],position[1]));
        }
        return ans;
    }



    public static class UnionFind{
        public int[] sizeMap;
        public int[] parent;
        public int[] help;
        public int col;
        public int row;
        public int len;
        public int sets;

        public UnionFind(int m,int n){
            col = m;
            row = n;
            len = m*n;
            sets = 0;
            parent = new int[len];
            sizeMap = new int[len];//靠 sizeMap[i] 是不是0 来代表有没有初始化
            help = new int[len];
            //这题不用初始化,
            // for(int i=0;i<m;i++){
            //     for(int j=0;j<n;j++){
            //         int index = index(i, j);
            //         parent[index] = index;
            //         sizeMap[index] = 0; 初始值就是0，不用初始化
            //     }
            // }
        }

        // row 行  col列
        public int index(int row, int col) {
            return row * col + col;
        }

        public int find(int index){
            int hi = 0;
            while (index != parent[index]) {
                help[hi++] = index;
                index = parent[index];
            }
            for (--hi; hi >= 0; hi--) {
                parent[help[hi]] = index;
            }
            return index;
        }

        public int connect(int i,int j){
            int index = index(i,j);
            if(sizeMap[index]==0){// ==0代表从来没出现过，初始化
                parent[index] = index;
                sizeMap[index] = 1;
                sets++;
                union(i,j,i-1,j);
                union(i,j,i+1,j);
                union(i,j,i,j-1);
                union(i,j,i,j+1);
            }
            return sets;
        }

        public void union(int col1,int row1,int col2,int row2){
            if (row1 < 0 || row1 == row || row2 < 0 || row2 == row || col1 < 0 || col1 == col || row1 < 0 || row1 == col) {
                return;
            }
            int i1 = index(row1,col1);
            int i2 = index(row2,col2);
            if (sizeMap[i1] == 0 || sizeMap[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (sizeMap[f1] >= sizeMap[f2]) {
                    sizeMap[f1] += sizeMap[f2];
                    parent[f2] = f1;
                } else {
                    sizeMap[f2] += sizeMap[f1];
                    parent[f1] = f2;
                }
            }
        }
    }

    // 如果 m n 特别大  但是k很小
    // 课上讲的如果m*n比较大，会经历很重的初始化，而k比较小，怎么优化的方法
    public static List<Integer> numIslands22(int m, int n, int[][] positions) {
        UnionFind2 uf = new UnionFind2();
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind2 {
        private HashMap<String, String> parent;
        private HashMap<String, Integer> size;
        private ArrayList<String> help;
        private int sets;

        public UnionFind2() {
            parent = new HashMap<>();
            size = new HashMap<>();
            help = new ArrayList<>();
            sets = 0;
        }

        private String find(String cur) {
            while (!cur.equals(parent.get(cur))) {
                help.add(cur);
                cur = parent.get(cur);
            }
            for (String str : help) {
                parent.put(str, cur);
            }
            help.clear();
            return cur;
        }

        private void union(String s1, String s2) {
            if (parent.containsKey(s1) && parent.containsKey(s2)) {
                String f1 = find(s1);
                String f2 = find(s2);
                if (!f1.equals(f2)) {
                    int size1 = size.get(f1);
                    int size2 = size.get(f2);
                    String big = size1 >= size2 ? f1 : f2;
                    String small = big == f1 ? f2 : f1;
                    parent.put(small, big);
                    size.put(big, size1 + size2);
                    sets--;
                }
            }
        }

        public int connect(int r, int c) {
            String key = String.valueOf(r) + "_" + String.valueOf(c);
            if (!parent.containsKey(key)) {
                parent.put(key, key);
                size.put(key, 1);
                sets++;
                String up = String.valueOf(r - 1) + "_" + String.valueOf(c);
                String down = String.valueOf(r + 1) + "_" + String.valueOf(c);
                String left = String.valueOf(r) + "_" + String.valueOf(c - 1);
                String right = String.valueOf(r) + "_" + String.valueOf(c + 1);
                union(up, key);
                union(down, key);
                union(left, key);
                union(right, key);
            }
            return sets;
        }

    }
}