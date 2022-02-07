package tixi.p16tu;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/30-13:54
 * @Version: 1.0.0
 */
public class Edge {

    //边结构

    //权重，长度
    public int weight;

    public Node from;
    public Node to;

    public Edge(int weight,Node from,Node to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}