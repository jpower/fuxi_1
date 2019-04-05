package com.wmh;


import org.junit.Test;

import java.io.*;

/**
 * Created by 周大侠
 * 2019-01-09 22:51
 */
public class Day05_1 {
    /**
     * 随机读写流 不在IO包中既可以读也可以写 内部是一个char数组
     *
     * @throws IOException
     */
    @Test
    public void fun1() throws IOException {
        RandomAccessFile file = new RandomAccessFile("C:/src/SpiltFile/wmh.txt", "rwd");
//        file.write("我爱你".getBytes());
//        file.write('a');
        file.seek(0);
//        System.out.println(file.readLine());
        file.write('o');
    }


    /**
     * 专门操作基本数据类型的流
     *
     * @throws IOException
     */
    @Test
    public void fun2() throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("src/wmh.txt"));
        dataOutputStream.writeUTF("大师风范");
//        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("C:/src/SpiltFile/wmh.txt"));
//        BufferedInputStream stream = new BufferedInputStream(dataInputStream);

    }

    /**
     * 管道流 利用多线程将输出流和输入流连接起来
     * @throws IOException
     */
    @Test
    public void fun3() throws IOException {
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();
        inputStream.connect(outputStream);
        new Thread(new Input(inputStream)).start();
        new Thread(new Output(outputStream)).start();
    }


}

class Input implements Runnable {
    private PipedInputStream pipedInputStream;

    public Input(PipedInputStream pipedInputStream) {
        this.pipedInputStream = pipedInputStream;
    }

    public void run() {
        byte[] bytes = new byte[1024];
        int ch = 0;

        try {
            while ((ch = pipedInputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes,0,ch));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Output implements Runnable {
    private PipedOutputStream pipedOutputStream;

    public Output(PipedOutputStream pipedOutputStream) {
        this.pipedOutputStream = pipedOutputStream;
    }

    public void run() {
        try {
            pipedOutputStream.write("我爱你".getBytes());
            pipedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



