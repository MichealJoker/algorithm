package io.selector;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: jzh
 * @date: created in 2021/12/19
 * @description:
 * @version: 1.0
 */
public class SelectorThread implements Runnable{

    Selector selector = null;

    LinkedBlockingQueue<Channel> lbq ;

    SelectorThreadGroup stg;


    SelectorThread(SelectorThreadGroup stg,LinkedBlockingQueue<Channel> lbq){
        this.stg = stg;
        this.lbq = lbq;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                //1 select
                int num = selector.select();//1
                //2 process
                if(num>0){
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey next = iterator.next();
                        iterator.remove();
                        if(next.isAcceptable()){
                            acceptHandler(next);
                        }else if (next.isReadable()){
                            readHander(next);
                        }else if (next.isWritable()){
                            writeHandler(next);
                        }
                    }
                }
                //3 runTask
                if(!lbq.isEmpty()){
                    Channel channel = lbq.take();
                    if(channel instanceof  ServerSocketChannel){
                        ServerSocketChannel server = (ServerSocketChannel) channel;
                        server.register(selector,SelectionKey.OP_ACCEPT);
                        System.out.println(Thread.currentThread().getName()+" register listen");
                    }else if(channel instanceof SocketChannel){
                        SocketChannel client = (SocketChannel) channel;
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println(Thread.currentThread().getName()+" register client: " + client.getRemoteAddress());
                    }
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void acceptHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName()+"   acceptHandler......");
        ServerSocketChannel server = (ServerSocketChannel)key.channel();
        try {
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            stg.nextSelectorV3(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readHander(SelectionKey key) {
        System.out.println(Thread.currentThread().getName()+" read......");
        ByteBuffer buffer = (ByteBuffer)key.attachment();
        SocketChannel client = (SocketChannel)key.channel();
        buffer.clear();
        while(true){
            try {
                int num = client.read(buffer);
                if(num > 0){
                    buffer.flip();  //将读到的内容翻转，然后直接写出
                    while(buffer.hasRemaining()){
                        client.write(buffer);
                    }
                    buffer.clear();
                }else if(num == 0){
                    break;
                }else if(num < 0 ){
                    //客户端断开了
                    System.out.println("client: " + client.getRemoteAddress()+"closed......");
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setWorker(SelectorThreadGroup stgWorker) {
        this.stg =  stgWorker;
    }


    private void writeHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName()+" write......");
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.flip();
        while (buffer.hasRemaining()) {
            try {
                client.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        buffer.clear();
    }
}
