package tixi.p8Tree;

import java.util.HashMap;

/**
 * @description: 练习，这结构很重要
 * @author: 姜志豪
 * @date: 2022/1/14-17:25
 * @Version: 1.0.0
 */
public class Code01_SelfTestTrieTree {

    public static class Node1{
        int path;
        int end;
        Node1[] nodes;

        public Node1(){
            path = 0;
            end = 0;
            nodes = new Node1[26];
        }
    }

    public static class Trie1{

        Node1 root;

        public Trie1(){
            root = new Node1();
        }

        public void insert(String word){
            if(word==null){
                return;
            }
            char[] chars = word.toCharArray();
            int index = 0;
            Node1 node = root;
            node.path++;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i]-'a';
                if(node.nodes[index]==null){
                    node.nodes[index] = new Node1();
                }
                node = node.nodes[index];
                node.path++;
            }
            node.end++;
        }

        public void delete(String word){
            if (search(word) == 0) {
                return;
            }
            char[] chars = word.toCharArray();
            int index = 0;
            Node1 node = root;
            node.path--;
            for(int i=0;i<chars.length;i++){
                index = chars[i]-'a';
                if(--node.nodes[index].path==0){
                    node.nodes[index]=null;
                    return;
                }
                node = node.nodes[index];
            }
            node.end--;
        }


        public int search(String word){
            if(word==null){
                return 0 ;
            }
            char[] chars = word.toCharArray();
            int index = 0;
            Node1 node = root;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i]-'a';
                if(node.nodes[index]==null){
                    return 0;
                }
                node = node.nodes[index];
            }
            return node.end;
        }


        public int prefixNumber(String word){
            if(word==null){
                return 0 ;
            }
            char[] chars = word.toCharArray();
            int index = 0;
            Node1 node = root;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i]-'a';
                if(node.nodes[index]==null){
                    return 0;
                }
                node = node.nodes[index];
            }
            return node.path;
        }
    }


    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1  != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 !=  ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}