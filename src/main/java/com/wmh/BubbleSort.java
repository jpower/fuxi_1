package com.wmh;

/**
 * Created by 周大侠
 * 2019-02-03 10:41
 */
public class BubbleSort {

    /**
     * 冒泡排序
     *
     * @param a
     */
    public void bubbleSort(int[] a) {

        for (int i = 0; i < a.length - 1; i++) {
            for (int x = 0; x < a.length - 1 - i; x++) {
                if (a[x] > a[x + 1]) {
                    swap(a, x, x + 1);
                }
            }
        }
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

        if(i==index) {
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
