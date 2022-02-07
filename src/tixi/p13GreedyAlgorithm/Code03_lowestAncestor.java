package tixi.p13GreedyAlgorithm;

import java.util.*;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/25-12:51
 * @Version: 1.0.0
 */
public class Code03_lowestAncestor {


    //给定一棵二叉树的头节点head，和另外两个节点a和b。
    // 返回a和b的最低公共祖先




    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //方案一，遍历一遍，记录一个hashmap<节点，其父节点>,
    //然后根据hashmap把o1一直到顶记录进set
    //然后o2一直到顶遍历，第一个在set里发现的就是第一个交点
    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        Map<Node,Node> parentMap = new HashMap<>();
        parentMap.put(head,null);
        filterParentMap(head,parentMap);
        Set<Node> leftSet = new HashSet<>();
        while (o1!=null){
            leftSet.add(o1);
            o1 = parentMap.get(o1);
        }
        while (o2!=null){
            if(leftSet.contains(o2)){
                return o2;
            }
            o2 = parentMap.get(o2);
        }
        return head;
    }

    public static void filterParentMap(Node head,Map<Node,Node> parentMap){
        if(head.left!=null){
            parentMap.put(head.left,head);
            filterParentMap(head.left,parentMap);
        }
        if(head.right!=null){
            parentMap.put(head.right,head);
            filterParentMap(head.right,parentMap);
        }
    }

    //方案2

    public static class Info{
        public boolean haveA;
        public boolean haveB;
        public Node lowertAncestor;

        public Info(boolean haveA,boolean haveB,Node lowertAncestor){
            this.haveA = haveA;
            this.haveB = haveB;
            this.lowertAncestor = lowertAncestor;
        }
    }

    public static Node lowestAncestor2(Node head, Node a, Node b) {
        return process(head,a,b).lowertAncestor;
    }

    public static Info process(Node head, Node a, Node b) {
        if(head==null){
            return new Info(false,false,null);
        }
        Info leftInfo = process(head.left,a,b);
        Info rightInfo = process(head.right,a,b);
        boolean haveA = head == a || leftInfo.haveA || rightInfo.haveA;
        boolean haveB = head == b || leftInfo.haveB || rightInfo.haveB;
        Node lowertAncestor = null;
        if(leftInfo.lowertAncestor!=null){
            lowertAncestor = leftInfo.lowertAncestor;
        }else if(rightInfo.lowertAncestor !=null){
            lowertAncestor = rightInfo.lowertAncestor;
        }else {
            if(haveA&&haveB){
                lowertAncestor = head;
            }
        }
        return new Info(haveA,haveB,lowertAncestor);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}