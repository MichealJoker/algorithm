package tixi.p16tu;

import java.util.*;

/**
 * @author: jzh
 * @date: created in 2022/2/7
 * @description:  拓扑排序
 * @version: 1.0
 */
public class Code03_TopologySort {

    //1）在图中找到所有入度为0的点输出
    //2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
    //3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;

    }
}
