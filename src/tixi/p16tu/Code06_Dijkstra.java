package tixi.p16tu;

import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: jzh
 * @date: created in 2022/2/9
 * @description:   456都是贪心
 * @version: 1.0
 */
public class Code06_Dijkstra {

//    1）Dijkstra算法必须指定一个源点
//2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
//3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
//4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了

    //基础实现，每次都要遍历
    public static HashMap<Node,Integer> dijkstra1(Node from){
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        // 打过对号的点
        HashSet<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            //  原始点  ->  minNode(跳转点)   最小距离distance
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) { //第一次出现，取直线
                    distanceMap.put(toNode, distance + edge.weight);
                } else { // toNode          第N次出现了，看看绕路能不能更近
                    distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    //map里到达距离最短，并且不在set里的
    //  a  b c d
    //  0  1 6 2
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node,Integer>distanceMap,HashSet<Node> touchedNodes){
        Node minNode = null;
        int minDinstance = Integer.MAX_VALUE;
        for(Map.Entry<Node,Integer>entry:distanceMap.entrySet()){
            Node node = entry.getKey();
            int distance = entry.getValue();
            if(!touchedNodes.contains(node)&& distance<minDinstance){
                minNode = node;
                minDinstance = distance;
            }
        }
        return minNode;
    }


    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }


    //可以用加强堆优化
    //逻辑，取小，发现绕路更近时，resign
    public static class NodeHeap{
        //堆
        private Node[] nodes;
        //节点在堆里位置的反向索引
        private HashMap<Node,Integer> heapIndexMap;
        //节点的最短距离
        private HashMap<Node,Integer> distanceMap;
        private int size;//堆大小

        public NodeHeap(int size){
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node,int distance){
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeap(node, heapIndexMap.get(node));//更新，值变小后只会上浮
            }
            if(!isEntered(node)){//添加，插尾上浮
                nodes[size] = node;
                heapIndexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeap(node,size++);
            }
        }

        private void insertHeap(Node node,int index){
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index,(index-1)/2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index,int size){
            int left = index * 2 + 1;
            while (left<size){
                int right = left + 1;
                int smallest = left;
                //求出 左子和右子谁小
                if (right < size && distanceMap.get(nodes[right]) < distanceMap.get(nodes[left])) {
                    smallest = right;
                }
                //如果最小的子，比头小就互换
                if (distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index])) {
                    swap(smallest, index);
                    index = smallest;
                    left = index * 2 + 1;
                } else {
                    break;
                }
            }
        }

        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);//反向索引 这里设-1代表那个节点被弹出去了
            distanceMap.remove(nodes[size - 1]);//这行其实多余
            nodes[size - 1] = null;//堆里要删
            heapify(0, --size);
            return nodeRecord;
        }


        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }
    }
    // 改进后的dijkstra算法
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    //进阶版的Dijkstra，利用加强堆和反向索引，省去每次遍历节点
    public static HashMap<Node,Integer> dijkstra2(Node head,int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head,0);
        HashMap<Node,Integer>result = new HashMap<>();
        while (!nodeHeap.isEmpty()){
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            result.put(cur,distance);
            for(Edge edge: cur.edges){
                nodeHeap.addOrUpdateOrIgnore(edge.to,edge.weight+distance);
            }
        }
        return result;
    }
}
