package com.wmh;

import org.junit.Test;

/**
 *
 * Created by 周大侠
 * 2019-02-03 14:54
 */
public class HeapSort {
    @Test
    public void fun1() {
        int[] a = {3, 2, 1, 123, 1, 45,23,123,45,78,456,34,234,1,23, 46, 2, 34, 768, 23, 42, 4, 56, 54, 2, 25};
        heapSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

    /**
     * 堆排
     * @param a
     */
    public void heapSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            heapInsert(a, i);
        }
        int size = a.length;
        while (size > 0) {
            swap(a, 0, --size);
            heapify(a, 0, size);
        }


    }


    /**
     * 维护大根堆
     * 每多维护一个元素 需要将该元素 上浮
     *
     * @param a
     * @param i
     */
    private void heapInsert(int[] a, int i) {
        while (a[i] > a[(i - 1) / 2]) {
            swap(a, i, (i - 1) / 2);
            i =  (i - 1) / 2;
        }
    }

    /**
     * 堆的下沉
     * @param a
     * @param index
     * @param size
     */
    public void heapify(int[] a, int index, int size) {
        int left = 2 * index + 1;
        while (left < size) {
            left = left + 1 < size && a[left+1] > a[left] ? left+1 : left;
            if (a[index] < a[left]) {
                swap(a, index, left);
            } else {
                break;
            }
            index = left;
            left = 2 * index + 1;
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
