package com.wmh;

import  java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Created by 周大侠
 * 2019-01-10 21:50
 */
public class Day06 {
    public static void main(String[] args) throws IOException {
        showFile("C:/src/image");
    }
    public static void showFile(String directory) throws IOException {
        File file = new File(directory);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if(file1.isDirectory()){
                showFile(file1.getAbsolutePath());
            }else{
                    System.out.println(file1.getName());
            }
        }
        System.out.println(file.getName());


    }
    @Test
    public void fun1(){

    }

}
