package tixi.p16tu;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 点结构
 * @author: 姜志豪
 * @date: 2022/1/30-13:51
 * @Version: 1.0.0
 */
public class Node {

    //用来描述图里的点
    //做图算法的第一步是把图转成自己熟悉的结构
    public int value;

    //有几个点指向 这
    public int in;

    //自己指向几个点
    public int out;

    //哪些邻居,只包含自己指向的， 其他点指向自己的不算
    public List<Node> nexts;

    //从自己出发有哪些直接的边
    public List<Edge> edges;


    public Node(int value){
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}