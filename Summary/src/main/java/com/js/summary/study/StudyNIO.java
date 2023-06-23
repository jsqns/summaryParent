package com.js.summary.study;


import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class StudyNIO {
    public static void main(String[] args) {
        testSelector();
    }
    public static void testBuffer(){
        String str = "abcde";
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.isDirect());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        buffer.put(str.getBytes());
        System.out.println("-------------put----------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        buffer.flip();
        System.out.println("-------------flip----------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        System.out.println("-------------get----------------");
        byte[] def = new byte[buffer.limit()];
        buffer.get(def);
        System.out.println(new String(def, 0,def.length));
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        buffer.rewind();
        System.out.println("-------------rewind----------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
    }

    public static void testSelector(){
        try {
            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
//            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            while (true){
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SocketChannel channel = (SocketChannel)iterator.next().channel();
                ByteBuffer allocate = ByteBuffer.allocate(1024);
                channel.read(allocate);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
