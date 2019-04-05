package com.wmh;

import org.junit.Test;

/**
 * Created by 周大侠
 * 2019-02-03 11:46
 */
public class QuickSort {
    @Test
    public void fun1(){
        int[] a = {3,2,1,2342,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,123,43,11,1,1,1,1,1,1,1645,7,23,423,4,345,6,457,56,8,3,4,23,4,567,34,3,43,43,3,3,432,4,4,3,3,5,5,7,34534,11,22,11,22,465,87,122,334,46,98};
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            quickSort1(a);
        }
        long l1 = System.currentTimeMillis();

        System.out.println(l1-l);
    }
    /**
     * 快速排序
     *
     * @param a
     */
    public void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }
    /**
     * 快速排序  运用荷兰国旗问题
     *
     * @param a
     */
    public void quickSort1(int[] a) {
        quickSort1(a, 0, a.length - 1);
    }

    /**
     * 递归实现
     *
     * @param a
     * @param right
     * @param left
     */
    private void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int middleIndex = partition(a, left, right);
            quickSort(a, left, middleIndex - 1);
            quickSort(a, middleIndex + 1, right);
        }

    }

    /**
     * 为数组设立一个中间值 值左边的数都比中间值小，值右边的数都比中间值大
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] a, int left, int right) {
        // 设立数组第一个元素为中间值
        int middle = a[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (j > i && a[j] >= middle) {
                j--;
            }
            while (i < j && a[i] <= middle) {
                i++;
            }
            swap(a, i, j);
        }
        swap(a, i, left);
        return i;
    }
    /**
     * 用荷兰国旗问题解决快速排序 一次遍历就可将与参考值相等的元素 一起放在中间
     * @param a
     * @param left
     * @param right
     * @return
     */
    private void quickSort1(int[] a, int left, int right) {
        if (left < right) {
            // 随机取参考值
//            swap(a, left + (int) (Math.random() * (right - left + 1)), left);
            int[] middleIndex = partition1(a, left, right);
            quickSort1(a, left, middleIndex[0]-1);
            quickSort1(a, middleIndex[1]+1, right);
        }

    }

    /**
     * 用荷兰国旗问题解决快速排序 一次遍历就可将与参考值相等的元素 一起放在中间
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int[] partition1(int[] arr, int left, int right) {
        int less = left - 1;
        int more = right + 1;
        int middle = arr[left];
        while (left < more) {
            if (arr[left] == middle) {
                left++;
            } else if (arr[left] < middle) {
                swap(arr, left++, ++less);
            } else {
                swap(arr, left, --more);
            }
        }
        return new int[]{less+1 , more-1 };


    }


    /**
     * 交换2个元素的位置
     *
     * @param a
     * @param i
     * @param index
     */
    private void swap(int[] a, int i, int index) {
//        int t = a[i];
//        a[i] = a[index];
//        a[index] = t;

        if (i == index) {
            return;
        }
        a[i] = a[index] ^ a[i];
        a[index] = a[index] ^ a[i];
        a[i] = a[i] ^ a[index];


//        a[i] = a[i]+a[index];
//        a[index] = a[i]-a[index];
//        a[i] = a[i] - a[index];


    }
}
