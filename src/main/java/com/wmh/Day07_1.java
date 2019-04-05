package com.wmh;

import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Set;


/*
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）：负责连接
 *
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 *
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer）：负责数据的存取
 *
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 */
public class Day07_1 {

    // 客户端
    @Test
    public void fun1() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8878));
//        CharBuffer charBuffer = CharBuffer.allocate(520);
//        charBuffer.put("我爱你wyy");
//        charBuffer.flip();
//        ByteBuffer byteBuffer = Charset.forName("utf8").newEncoder().encode(charBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        FileChannel fileChannel = FileChannel.open(Paths.get("banner.jpg"), StandardOpenOption.READ);
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
//        关闭连接进行阅读，不关闭频道。
        socketChannel.shutdownOutput();
        // 接收服务器反馈信息
        int len = 0;
        while ((len = socketChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), 0, len));
            byteBuffer.clear();
        }
        socketChannel.close();
    }


    // 服务端
    @Test
    public void fun2() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(8878));

        // 打开与客户端连接 获取通道
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer byteBuffer = ByteBuffer.allocate(520);

        FileChannel out = FileChannel.open(Paths.get("banner1.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        while (socketChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            out.write(byteBuffer);

            byteBuffer.clear();
        }

        // 发送反馈给客户端
        byteBuffer.put("我知道啦!".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);

        out.close();
        socketChannel.close();
        serverSocketChannel.close();
    }
    //  -------------------------------------------------
    // 非阻塞式IO


    // 客户端
    @Test
    public void fun3() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8888));
        // 设置非阻塞式
        socketChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("我爱你wyy".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();

        socketChannel.close();

    }
    // 服务端
    @Test
    public void fun4() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞式
        serverSocketChannel.configureBlocking(false);
        // 建立连接
        serverSocketChannel.bind(new InetSocketAddress(8888));
        // 得到选择器
        Selector selector = Selector.open();
        // 将通道注册到选择器上, 并且指定“监听接收事件”

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select()>0) {
            // 得到选择器上所有的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectedKey : selectionKeys) {
                // 如果是连接事件
                if (selectedKey.isAcceptable()) {
                    // 得到连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 设置非阻塞式
                    socketChannel.configureBlocking(false);
                    // 注册为读事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectedKey.isReadable()) { // 如果是读事件
                    // 得到该连接
                    SocketChannel socketChannel = (SocketChannel) selectedKey.channel();
                    // 读取信息
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(byteBuffer)) != -1) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                    socketChannel.close();
                }
                // 取消选择键 SelectionKey
                selectionKeys.remove(selectedKey);
            }
        }


    }

}
