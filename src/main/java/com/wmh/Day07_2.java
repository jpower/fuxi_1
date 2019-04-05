package com.wmh;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by 周大侠
 * 2019-01-15 20:01
 */
public class Day07_2 {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Thread thread = new Thread(new Send(pipe));
        Thread thread1 = new Thread(new Accept(pipe));
        thread.start();
        thread1.start();
    }
}

class Send implements Runnable {

    private Pipe pipe;

    public Send(Pipe pipe) {
        this.pipe = pipe;
    }

    @Override
    public void run() {
        Pipe.SinkChannel sinkChannel = pipe.sink();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("我爱你wyy".getBytes());
        byteBuffer.flip();
        try {
            sinkChannel.write(byteBuffer);
            byteBuffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Accept implements Runnable {
    private Pipe pipe;

    public Accept(Pipe pipe) {
        this.pipe = pipe;
    }

    @Override
    public void run() {
        Pipe.SourceChannel source = pipe.source();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int len = 0;
            while ((len = source.read(byteBuffer))!=-1){
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(),0,len));
                byteBuffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
