package com.wmh;

/**
 * Created by 周大侠
 * 2019-02-03 10:40
 */
public class SelectSort {
    /**
     * 优化选择排序
     *
     * @param a
     */
    public void selectSort1(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int index = i;

            for (int x = i + 1; x < a.length; x++) {
                if (a[x] < a[index]) {
                    index = x;
                }
            }
            // 交换位置
            swap(a, i, index);
        }
    }


    /**
     * 选择排序
     *
     * @param a
     */
    public void selectSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int x = i + 1; x < a.length; x++) {
                if (a[x] < a[i]) {
                    // 交换位置
                    swap(a, x, i);
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
