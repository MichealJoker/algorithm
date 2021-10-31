package leetcode.p101_150;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author: jzh
 * @date: created in 2021/5/2
 * @description:
 * @version: 1.0
 */
public class LRUCache146 {


    LinkedHashMap<Integer,Integer> linkedHashMap;


    public LRUCache146(int capacity) {
        linkedHashMap = new LinkedHashMap<>(capacity);
    }

    public int get(int key) {

        if(linkedHashMap.containsKey(key)){
            return linkedHashMap.get(key);
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {

        linkedHashMap.put(key,value);
    }


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
