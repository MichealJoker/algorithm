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
public class Code03_TopologicalOrderDFS1 {

    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    //题意是给你所有点，让你拓扑排序
    //解法一：算一个点出发能到的深度，深度大的肯定在拓扑序里排左边
    // 深度大的排左
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        Map<DirectedGraphNode,Record> map = new HashMap<>();
        for(int i=0;i<graph.size();i++){
            f(graph.get(i),map);
        }
        List<Record> list = new ArrayList<>();
        list.addAll(map.values());
        list.sort(new MyComparator());
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        for(Record record:list){
            result.add(record.node);
        }
        return result;
    }


    public static class Record {
        public DirectedGraphNode node;
        public long deep;

        public Record(DirectedGraphNode n, long o) {
            node = n;
            deep = o;
        }
    }

    public static class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o1.deep == o2.deep ? 0 : (o1.deep > o2.deep ? -1 : 1);
        }
    }


//    当前来到cur点，请返回cur点索道之处，所有的点次
//    如果之前算过一个点，那么那个点就在缓存里存着，最原始的动态规划：不做重复的事
    public static Record f(DirectedGraphNode cur, Map<DirectedGraphNode,Record> order){
        if(order.containsKey(cur)){
            return order.get(cur);
        }
        long deep = 0;
        for (DirectedGraphNode next : cur.neighbors){
            deep = Math.max(deep,f(next,order).deep);
        }
        Record ans = new Record(cur,deep+1);
        order.put(cur,ans);
        return ans;
    }
}
