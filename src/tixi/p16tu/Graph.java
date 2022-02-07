package tixi.p16tu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/30-14:02
 * @Version: 1.0.0
 */
public class Graph {

    //图结构

    public Map<Integer,Node> nodes;
    public Set<Edge> edges;

    public Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}