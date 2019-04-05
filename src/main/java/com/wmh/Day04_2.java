package com.wmh;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 周大侠
 * 2019-01-08 23:08
 */
public class Day04_2 {
    /**
     * SequenceInputStream 将多个输入流合并成一个流 接收参数为枚举 可以用Collections工具类转换
     * @throws IOException
     */
    @Test
    public void fun1() throws IOException {
        List<FileInputStream> list = new ArrayList<FileInputStream>();
        list.add(new FileInputStream("src/wmh.txt"));
        list.add(new FileInputStream("wmh1.txt"));
        list.add(new FileInputStream("wyy.txt"));

        SequenceInputStream inputStream = new SequenceInputStream(Collections.enumeration(list));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }
    @Test
    public void fun2() throws IOException {
     /*   OutputStream outputStream = new FileOutputStream("wmh.txt",true);
        PrintWriter printWriter = new PrintWriter(outputStream,true);
        printWriter.printf("发士大");
        printWriter.flush();*/
     PrintStream printStream = new PrintStream("src/wmh.txt");
     printStream.write(2);

    }
}
