package com.wmh;

import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.sound.midi.Soundbank;
import java.util.Stack;

/**
 * Created by 周大侠
 * 2019-01-04 21:51
 */
public class Day01 {
    @Test
    public void fun3() {
        // 转换进制
//        System.out.println(toHexString(123123));

    }

    public int arrayMax(int[] a) {
        int max = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[max]) {
                max = i;
            }
        }
        return max;
    }


    /**
     * 二分查找法
     *
     * @param array
     * @param data
     * @return
     */
    public int binarySearch(int[] array, int data) {
        // 二分查找法
        int min = 0;
        int max = array.length - 1;
        int mid = (max + min) / 2;
        if (data == array[mid]) {
            return mid;
        }

        while (min <= max) {
            if (array[mid] > data) {
                max = mid - 1;
            } else if (array[mid] < data) {
                min = mid + 1;
            } else {
                return mid;
            }
            mid = (max + min) / 2;
        }
        return -1;
    }

    /**
     * 二分查找递归
     *
     * @param array
     * @param data
     * @param min
     * @param max
     * @return
     */
    public int binarySearch(int[] array, int data, int min, int max) {
        if (min > max) {
            return -1;
        }
        int mid = (min + max) / 2;
        if (array[mid] > data) {
            return binarySearch(array, data, min, max - 1);
        } else if (array[mid] < data) {
            return binarySearch(array, data, mid + 1, max);
        } else {
            return mid;
        }
    }

    /**
     * 转换进制 第一个参数为进行与操作的数值，第二个数为需要右移的位数
     *
     * @param data
     * @return
     */
    public String trans(int data, int base, int bite) {
        StringBuilder result = new StringBuilder();
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        Stack stack = new Stack();
        while (data != 0) {
            int single = data & base;
            stack.push(chars[single]);
            data = data >>> bite;
        }
        for (int i = 0; i < stack.size(); i++) {
            result.append(stack.pop());
            i--;
        }
        return result.toString();
    }

    /**
     * 将十进制转换为16进制
     *
     * @param data
     * @return
     */
    public String toHexString(int data) {
        return trans(data, 15, 4);
    }

    /**
     * 将十进制转换为8进制
     *
     * @param data
     * @return
     */
    public String toOctalString(int data) {
        return trans(data, 7, 3);
    }

    /**
     * 将十进制转换为2进制
     *
     * @param data
     * @return
     */
    public String toBinaryString(int data) {
        return trans(data, 1, 1);
    }



}
