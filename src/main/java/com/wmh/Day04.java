package com.wmh;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 周大侠
 * 2019-01-08 9:50
 */
public class Day04 {

    public static void main(String[] args) {

        String str1 = "sdf@qq.c1m.ccc.ww";
        // 匹配邮箱
        Pattern pattern = Pattern.compile("\\w+@\\w+(\\.[a-z]{1,3})+");
        Matcher matcher = pattern.matcher(str1);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }

        String username = "dfddfg";
        //匹配用户名
        System.out.println(username.matches("^[a-z0-9_-]{3,16}$"));
    }

    /**
     * 将字符串中重复的字符转换为单独一个
     *
     * @return
     */
    @Test
    public void replaceAll() {
        String str = "我我我我我...爱爱爱..@aa.....你你你你";
        String str1 = str.replaceAll("\\.+", "").replaceAll("(.)\\1+", "$1");
        System.out.println(str1);

    }
}
