package com.wmh;

import org.junit.Test;

import java.io.*;

/**
 * IO操作
 * Created by 周大侠
 * 2019-01-08 14:23
 */
public class Day04_1 {

    @Test
    public void fun1() throws IOException {
        Writer writer = new FileWriter("src/wmh.txt", true);
//        writer.write("我是王敏豪");
        writer.append("\rsdf");
        writer.close();
    }

    @Test
    public void fun2() throws IOException {

        Reader reader = new FileReader("src/wmh.txt");
       /* int ch = 0;
        while ((ch = reader.read()) != -1) {
            System.out.print((char) ch + "");
        }*/

        MyBufferedReader bufferedReader = new MyBufferedReader(reader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Test
    public void fun3() throws IOException {
        Writer writer = new FileWriter("wyy.txt");
        Reader reader = new FileReader("src/wmh.txt");
        int ch = 0;
        while ((ch = reader.read()) != -1) {
            writer.write(ch);
            if (ch % 2 == 0) {
                writer.flush();
            }
        }
        writer.close();
    }

    @Test
    public void fun4() throws IOException {
        Writer writer = new FileWriter("wyy.txt");
        Reader reader = new FileReader("src/wmh.txt");
        int ch = 0;
        char[] chs = new char[1024];
        while ((ch = reader.read(chs)) != -1) {
            writer.write(chs, 0, ch);
            if (ch % 2 == 0) {
                writer.flush();
            }
        }
        writer.close();
    }

    @Test
    public void fun5() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("wyy.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("src/wmh.txt"));
        String str = null;
        while ((str = reader.readLine()) != null) {
            writer.write(str);
            writer.newLine();
        }
        writer.close();
    }

    @Test
    public void fun6() throws IOException {
        OutputStream outputStream = new FileOutputStream("wmh1.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        InputStream inputStream = new FileInputStream("wyy.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int a = 0;
        while ((a = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(a);
        }
        outputStream.close();
    }

    public static void main(String[] args) throws IOException {

//        charToUpper();
//        charToUpperByReader();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        outputStreamWriter.write("sdfsd");
        outputStreamWriter.flush();
    }

    /**
     * 利用转换流
     * 將輸入的字节转换为字符再转换为大写然后输出
     */
    private static void charToUpperByReader() throws IOException {
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("end")) {
                break;
            }
            System.out.println(line.toUpperCase());
        }
    }

    /**
     * 將輸入的字节转换为大写然后输出
     */
    private static void charToUpper() throws IOException {
        InputStream input = System.in;
        StringBuilder stringBuilder = new StringBuilder();
        int ch = 0;
        while ((ch = input.read()) != -1) {
            if (ch == '\n') {
                if (stringBuilder.toString().equals("end")) {
                    stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
                    break;
                }
                System.out.println(stringBuilder.toString().toUpperCase());
                stringBuilder.delete(0, stringBuilder.length());
            } else {

                stringBuilder.append((char) ch);
            }
        }
    }

    class MyBufferedReader extends Reader {
        private Reader reader;

        private char[] ch = new char[1024];
        private int count = 0;
        private int read = 0;

        public MyBufferedReader(Reader reader) {
            this.reader = reader;
        }


        @Override
        public int read() throws IOException {

            if (read == 0) {
                read = reader.read(ch);
                count = 0;
            }
            if (read == -1) {
                return -1;
            }
            char result = ch[count];
            count++;
            read--;
            return result;
        }

        public String readLine() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            int ch = 0;
            while ((ch = read()) != -1) {

                if ((char) ch == '\r') {
                    continue;
                }
                if ((char) ch == '\n') {
                    return stringBuilder.toString();
                }
                stringBuilder.append((char) ch);
            }
            if (stringBuilder.length() > 0) {
                return stringBuilder.toString();
            }
            return null;
        }

        public int read(char[] cbuf, int off, int len) throws IOException {


            return 0;
        }

        public void close() throws IOException {
            reader.close();
        }
    }
}
