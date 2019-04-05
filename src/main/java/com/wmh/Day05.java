package com.wmh;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * 分割文件并合并
 * Created by 周大侠
 * 2019-01-09 21:24
 */
public class Day05 {
    public static final int fileSize = 1024 * 1024;

    public static void main(String[] args) throws IOException {
//        spileFile();
//        merge();
        Properties properties = new Properties();
        properties.load(new FileInputStream("file.properties"));
        String directory = (String) properties.get("directory");
        String suufix = (String) properties.get("suffix");
        // 拆分文件
//        spileFile(directory,"wmh.JPG",suufix);

        // 合并文件
        merge("NewWmh.JPG", directory, suufix);

    }


    /**
     * 拆分文件
     *
     * @throws IOException
     */
    public static void spileFile(String directory, String fileName, String suffix) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File(directory, fileName));
        byte[] bytes = new byte[fileSize];
        int ch = 0;
        int count = 1;
        while ((ch = fileInputStream.read(bytes)) != -1) {

            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(directory + count++ + suffix));
            outputStream.write(bytes, 0, ch);
            outputStream.close();
        }
    }

    /**
     * 合并文件
     *
     * @throws IOException
     */
    public static void merge(String newFileName, String directory, final String suffix) throws IOException {
        List<FileInputStream> list = new ArrayList<FileInputStream>();

        File file = new File(directory);
        File[] files = file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(suffix);
            }
        });

        for (int i = 0; i <files.length; i++) {
            list.add(new FileInputStream(files[i]));
        }

        FileOutputStream fileOutputStream = new FileOutputStream(new File(directory, newFileName));
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);


        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(list));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(sequenceInputStream);
        int ch = 0;
        while ((ch = bufferedInputStream.read()) != -1) {
            outputStream.write(ch);
            outputStream.flush();
        }
        outputStream.close();
        bufferedInputStream.close();
    }


}
