package com.wmh;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 周大侠
 * 2019-01-07 11:51
 */
public class Day03 {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(System.currentTimeMillis());
        System.out.println(date.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        System.out.println(DateFormat.getDateInstance().format(new Date()));
        System.out.println(DateFormat.getDateTimeInstance().format(new Date()));
        String format = dateFormat.format(date);


        System.out.println(format);
    }

    /**
     * 查找单个字符在字符串中出现的次数
     */
    @Test
    public void fun1() {
        String str = "aaaaaavsdfgdweradfdddadgax,afaxczcadsdfd";
        int count = 0;
        int start = 0;
        while (str.indexOf(',', start) != -1) {
            count++;
            start = str.indexOf(',', start) + 1;
        }
        System.out.println(count);

    }

    /**
     * 去除字符串两端空白
     */
    @Test
    public void fun2() {
        String str = "      ";
        int start = 0;

        int end = str.length()-1;
       while(start<=end&&str.charAt(start)==' '){
           start++;
       }
       while (start<=end&&str.charAt(end)==' '){
           end--;
       }
        System.out.println(start + " " + end);
        System.out.println(str.substring(start, end+1));

    }

    /**
     * 查询两字符串中包含最大字符串
     */
    @Test
    public void fun3(){
        String str="gsfgsdfgwerabcde";
        String str1="gsfgsdfgad";
        String min = str.length() > str1.length() ? str1 : str;
        String max = min.equals(str) ? str1 : str;
        if(max.contains(min)){
            System.out.println(min);
        }
        for (int i = 0; i <min.length() ; i++) {
            for(int x=0, t=min.length()-i;t!=min.length()+1;x++,t++){
                String substring = min.substring(x, t);
                if(max.contains(substring)){
                        System.out.println(substring);
                        return;
                    }
            }
        }
    }

}
