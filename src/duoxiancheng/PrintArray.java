package duoxiancheng;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: jzh
 * @date: created in 2021/12/19
 * @description:
 * @version: 1.0
 */
public class PrintArray {

    public static char[] arrA = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public int type;

    public static LinkedBlockingQueue <Integer>queue = new LinkedBlockingQueue();

    public static LinkedBlockingQueue <Integer>queue2 = new LinkedBlockingQueue();

    public static PrintArray a1;

    public static PrintArray a2;

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        queue.offer(1);
        t1 = new Thread(()->{
            while (true){
                if(queue.isEmpty()){
                    LockSupport.park();
                }else{
                    Integer poll = queue.poll();
                    System.out.println(arrA[poll-1]);
                    queue2.offer(poll);
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        });
        t2 = new Thread(()->{
            while (true){
                if(queue2.isEmpty()){
                    LockSupport.park();
                }else{
                    Integer poll = queue2.poll();
                    System.out.println(poll);
                    queue.offer(++poll);
                    LockSupport.unpark(t1);
                    LockSupport.park();
                }
            }
        });
        t1.start();
        t2.start();

    }





}
