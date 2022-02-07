package tixi.p16tu;

/**
 * @description: 图转换器
 * @author: 姜志豪
 * @date: 2022/1/30-14:06
 * @Version: 1.0.0
 */
public class GraphGenerator {


    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    //
    // [ 5 , 0 , 7]
    // [ 3 , 0,  1]
    //
    public static Graph createGraph(Integer[][] matrix){
        Graph graph = new Graph();
        for(int i=0;i<matrix.length;i++){
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(newEdge);
            fromNode.out++;
            toNode.in++;
            graph.edges.add(newEdge);
        }
        return graph;
    }
}