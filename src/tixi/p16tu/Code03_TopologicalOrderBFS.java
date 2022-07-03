package tixi.p16tu;

import java.util.*;

/**
 * @author: jzh
 * @date: created in 2022/2/8
 * @description:
 * @version: 1.0
 */
//：https://www.lintcode.com/problem/topological-sorting
//    给定一个有向图，图节点的拓扑排序定义如下:
//
//对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
//拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
//针对给定的有向图找到任意一种拓扑排序的顺序.
public class Code03_TopologicalOrderBFS {

    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 提交下面的
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> indegreeMap = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            indegreeMap.put(cur, 0);
        }
        for (DirectedGraphNode cur : graph) {
            for (DirectedGraphNode next : cur.neighbors) {
                indegreeMap.put(next, indegreeMap.get(next) + 1);
            }
        }
        Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
        for (DirectedGraphNode cur : indegreeMap.keySet()) {
            if (indegreeMap.get(cur) == 0) {
                zeroQueue.add(cur);
            }
        }
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            DirectedGraphNode cur = zeroQueue.poll();
            ans.add(cur);
            for (DirectedGraphNode next : cur.neighbors) {
                indegreeMap.put(next, indegreeMap.get(next) - 1);
                if (indegreeMap.get(next) == 0) {
                    zeroQueue.offer(next);
                }
            }
        }
        return ans;
    }
}
