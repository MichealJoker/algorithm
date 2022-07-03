package duoxiancheng;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

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

    public static void main2(String[] args) {
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


    public static void main(String[] args) {
        System.out.println(born2(13));
        System.out.println(born2(12));
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
    }

    //有一对兔子从出生后第三个月起，每个月都生一对兔子，小兔子长到第三个月又生一对兔子，每个月兔子的总数是？
    // 寿命到十二个月会死
    public static int born2(int n){
        if (n == 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        } else if (n > 12) {
            return born2(n - 1) + born2(n - 2) - 1;
        } else {
            return born2(n - 1) + born2(n - 2);
        }
    }

}
