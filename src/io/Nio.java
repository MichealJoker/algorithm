package io;

import java.nio.ByteBuffer;

/**
 * @author: jzh
 * @date: created in 2021/12/14
 * @description:
 * @version: 1.0
 */
public class Nio {



    public static void testNio(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);//堆上
        ByteBuffer outBuffer = ByteBuffer.allocateDirect(1024);//堆外
        System.out.println("position" + buffer.position());
        System.out.println("limit" + buffer.limit());
        System.out.println("capacity" + buffer.capacity());
        System.out.println("mark" + buffer);
        buffer.put("123".getBytes());
        System.out.println("put123");
        System.out.println("mark" + buffer);
        buffer.flip();  //读写交替  想读时flip
        System.out.println("mark" + buffer);
        buffer.get();
        System.out.println("mark" + buffer);
        buffer.compact(); //想写时 compact
        System.out.println("mark" + buffer);
        buffer.clear();
        System.out.println("mark" + buffer);
    }

    public static void main(String[] args) {
        testNio();
    }
}
