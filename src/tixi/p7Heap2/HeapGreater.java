package tixi.p7Heap2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @description: 加强堆
 * @author: 姜志豪
 * @date: 2022/1/7-18:48
 * @Version: 1.0.0
 */
public class HeapGreater<T> {

    //系统默认的堆 没有反向索引表，
    //它做不到一件事  告诉堆，堆内某个元素值变了，把它调到它变化后新的位置
    //默认的堆，元素加进去后，值变更了，值不会主动变化

    //[a,b,c]
    private ArrayList<T> heap;
    //[a->0],[b->1],[c->2]
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comparator;

    public HeapGreater(Comparator<T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comparator = c;
    }

    public boolean isEmpty(){
        return heap.size()==0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj){
        heap.add(obj);
        indexMap.put(obj,heapSize);
        heapInsert(heapSize++);
    }

    public void heapInsert(int index){
        while (
                //自己比父小，上浮  while (arr[index] > arr[(index - 1) / 2]) {
                comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0
        ) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);//尾部和头互换
        indexMap.remove(ans);//弹出
        heap.remove(--heapSize);
        heapify(0);//头部是刚换上来的，让它下沉
        return ans;
    }

    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            best = comparator.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }

    //通知堆，堆内的某个元素发生了变化
    public void resign(T obj){
        //虽然是顺序写，但是Insert和ify 最多只会发生一个， 一个发生了，另外一个进去了就弹出
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    //删除堆内元素
    public void remove(T obj){
        //把目标元素和堆最后一个元素互换，然后让换上来的元素resign
        T replace = heap.get(heapSize - 1);
        Integer index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (obj != replace) {//如果删的是最后一个元素
            heap.set(index,replace);
            indexMap.put(replace,index);
            resign(replace);
        }
    }


    private void swap(int i,int j){
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i,o2);//值交换
        heap.set(j,o1);
        indexMap.put(o2,i);//反向索引表也交换
        indexMap.put(o1,j);
    }


    public static void main(String[] args) {
        HeapGreater<Inner<Integer>> heapGreater = new HeapGreater<>(new Comparator<Inner<Integer>>() {
            @Override
            public int compare(Inner<Integer> o1, Inner<Integer> o2) {
                return o1.value - o2.value;
            }
        });
        heapGreater.push(new Inner<Integer>(1));
        heapGreater.push(new Inner<Integer>(2));
        System.out.println(heapGreater.peek().value);
    }


    // 请返回堆上的所有元素
    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }

}