package tixi.p14GreedyAlgorithm2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @description: 并查集
 * @author: 姜志豪
 * @date: 2022/1/26-16:35
 * @Version: 1.0.0
 */
public class Code05_UnionFind {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionSet<V>{

        //样本和包含样本的圈
        public Map<V,Node<V>> nodes;
        //父亲节点关系表
        public Map<Node<V>,Node<V>> parents;
        //用来描述顶头节点 包含的总节点数，只记录顶头节点
        public Map<Node<V>,Integer> sizeMap;
        public UnionSet(List<V> values){
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for(V cur:values){
                Node<V> node = new Node<>(cur);
                nodes.put(cur,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        //扁平化
        // 只要这个方法调用次数超过O(N)的规模，加入扁平化处理后，平均下来单次的查询复杂度就是O(1)
        //把 a->b->c->e->f 变成
        //      f
        //   / / \ \
        //  a  b  c e
        // 给你一个节点，请你往上到不能再往上，把代表返回
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            //找顶点沿途所有节点入栈
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            //之后再查子节点就是一步直接到顶
            return cur;
        }


        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }

    public static void main(String[] args) {
        Stack<Node<String>> path = new Stack<>();
        Node<String> cur = new Node<>("qwe");
        Map<Node<String>,Node<String>> parents = new HashMap<>();
        while (cur != parents.get(cur)) {
            path.push(cur);
            cur = parents.get(cur);
        }
        System.out.println("123");
    }

}