package tixi.p16tu;

import java.util.*;

/**
 * @author: jzh
 * @date: created in 2022/2/8
 * @description:
 * @version: 1.0
 */
public class Code04_Kruskal {

    //最小生成树算法之Kruskal
    //1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
    //2）当前的边要么进入最小生成树的集合，要么丢弃
    //3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
    //4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
    //5）考察完所有边之后，最小生成树的集合也得到了

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        // 从小的边到大的边，依次弹出，小根堆！
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) { // M 条边
            priorityQueue.add(edge);  // O(logM)
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) { // M 条边
            Edge edge = priorityQueue.poll(); // O(logM)
            if (!unionFind.isSameSet(edge.from, edge.to)) { // O(1)
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }

    //用并查集
    public static class UnionFind{
        private HashMap<Node,Node> fatherMap ;

        private HashMap<Node,Integer> sizeMap;

        public UnionFind(){
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for (Node node:nodes){
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public Node findFather(Node n){
            Stack<Node> path = new Stack<>();
            while (n != fatherMap.get(n)){
                path.add(n);
                n = fatherMap.get(n);
            }
            while (!path.isEmpty()){
                fatherMap.put(path.pop(),n);
            }
            return n;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a,Node b){
            if(a ==null||b==null){
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if(aDai!=bDai){
                int aSize = sizeMap.get(aDai);
                int bSize = sizeMap.get(bDai);
                if(aSize<=bSize){
                    fatherMap.put(aDai,bDai);
                    sizeMap.put(bDai, aSize + bSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSize + bSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }
}
