package com.wmh;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 * <p>
 * 二、通道的主要实现类
 * java.nio.channels.Channel 接口：
 * |--FileChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 本地 IO：
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * <p>
 * 网络IO：
 * Socket
 * ServerSocket
 * DatagramSocket
 * <p>
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 * <p>
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 * <p>
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 * <p>
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 */

public class Day07 {
    @Test
    public void fun1() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("我爱你".getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("--------------------");
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("--------------------1");
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("--------------------");
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("--------------------");

    }

    // 非直接缓冲区实现文件复制
    @Test
    public void fun2() throws IOException {
        long l = System.currentTimeMillis();
        FileInputStream inputStream = new FileInputStream("src/wmh.txt");
        FileOutputStream outputStream = new FileOutputStream("wmh2.txt");
        FileChannel channel = inputStream.getChannel();
        FileChannel channel1 = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        while (channel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            channel1.write(byteBuffer);
            byteBuffer.clear(); //清空缓冲区

        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);


    }

    // 使用直接缓冲区进行文件复制
    @Test
    public void fun3() throws IOException {
        long l = System.currentTimeMillis();
        FileChannel in = FileChannel.open(Paths.get("src/wmh.txt"), StandardOpenOption.READ);
        FileChannel out = FileChannel.open(Paths.get("wmh3.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        in.transferTo(0,in.size(),out);
        out.transferFrom(in,0,in.size());
        out.close();;
        in.close();
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
    }
    // 使用直接缓冲区进行文件复制
    @Test
    public void fun4() throws IOException {
        long l = System.currentTimeMillis();

        FileInputStream inputStream = new FileInputStream("src/wmh.txt");
        FileOutputStream outputStream = new FileOutputStream("wmh2.txt");

        FileChannel channel = inputStream.getChannel();
        FileChannel channel1 = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        while (channel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            channel1.write(byteBuffer);
            byteBuffer.clear(); //清空缓冲区

        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
    }
    // 分散与聚集 就是读取时 放入多个缓冲区中，写的时候同时用多个缓冲区写
    @Test
    public void fun5() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("src/wmh.txt","rw");
        FileChannel in = randomAccessFile.getChannel();
        FileChannel out = FileChannel.open(Paths.get("wmh520.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(52);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(52);
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(52);
        ByteBuffer[] byteBuffers = {byteBuffer1,byteBuffer2,byteBuffer3};
        in.read(byteBuffers);

        for (ByteBuffer byteBuffer : byteBuffers) {
            byteBuffer.flip();
        }
        out.write(byteBuffers);
    }
    // 测试字符集
    @Test
    public void fun6() throws CharacterCodingException {
        Charset charset = Charset.forName("utf8");
        // 获得编码器
        CharsetEncoder encoder = charset.newEncoder();
        CharBuffer charBuffer = CharBuffer.allocate(520);
        charBuffer.put("我爱你wyy");
        charBuffer.flip();
        // 将字符缓冲区 用指定的码表 编码成字节缓冲区
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        for (byte b : byteBuffer.array()) {
            System.out.println(b);
        }

        // 获得解码器
        CharsetDecoder decoder = charset.newDecoder();
        // 将字节缓冲区 用指定的码表 解码成字符缓冲区
        CharBuffer decodeBuffer = decoder.decode(byteBuffer);
        System.out.println(decodeBuffer);
    }

}


