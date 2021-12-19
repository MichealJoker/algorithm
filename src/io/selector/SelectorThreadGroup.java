package io.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: jzh
 * @date: created in 2021/12/19
 * @description:
 * @version: 1.0
 */
public class SelectorThreadGroup {

    SelectorThread[] sts;
    ServerSocketChannel server = null;
    AtomicInteger xid = new AtomicInteger(0);

    SelectorThreadGroup stg = this;

    public void setWorker(SelectorThreadGroup stg){
        this.stg = stg;
    }


    SelectorThreadGroup(int num){
        sts = new SelectorThread[num];
        for(int i=0;i<sts.length;i++){
            sts[i] = new SelectorThread(this,new LinkedBlockingQueue<>());
            new Thread(sts[i]).start();
        }
    }

    public void bind(int port){

        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            nextSelectorV3(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void nextSelectorV3(Channel c) {
        try {
            if(c instanceof  ServerSocketChannel){
                SelectorThread st = next();  //listen 选择了 boss组中的一个线程后，要更新这个线程的work组
                st.lbq.put(c);
                st.setWorker(stg);
                st.selector.wakeup();
            }else {
                SelectorThread st = nextV3();
                st.lbq.add(c);
                st.selector.wakeup();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private SelectorThread next() {
        int index = xid.incrementAndGet() % sts.length;
        return sts[index];
    }
    private SelectorThread nextV3() {
        int index = xid.incrementAndGet() % stg.sts.length;
        return stg.sts[index];
    }
}
