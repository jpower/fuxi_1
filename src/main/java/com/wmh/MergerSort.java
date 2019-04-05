package com.wmh;

/**
 * Created by 周大侠
 * 2019-02-03 10:39
 */
public class MergerSort {
    /**
     * 归并排序
     *
     * @param a
     */
    public void mergerSort(int[] a) {

        // 定义一个临时数组 避免递归中频繁开辟空间
        int[] temp = new int[a.length];

        mergerSort(a, temp, 0, a.length - 1);


    }

    /**
     * 归并排序实现递归
     *
     * @param a
     * @param temp
     * @param left
     * @param right
     */
    private void mergerSort(int[] a, int[] temp, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            // 分割左边进行合并排序
            mergerSort(a, temp, left, middle);
            // 分割右边 进行合并排序
            mergerSort(a, temp, middle + 1, right);
            // 合并2个有序的子数组
            merge(a, temp, left, middle, right);
        }

    }

    /**
     * 合并2个有序的用指针指向的子数组
     *
     * @param a
     * @param temp
     * @param left
     * @param middle
     * @param right
     */
    private void merge(int[] a, int[] temp, int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        int t = 0;
        // 将2个指针进行比较 小的放进临时数组中 并且指针加1 继续比较
        // 临时数组中的 数量t 也要++
        while (i <= middle && j <= right) {
            if (a[i] <= a[j]) {
                temp[t++] = a[i++];
            } else {
                temp[t++] = a[j++];
            }

        }
        // 将左边剩余元素放进temp中
        while (i <= middle) {
            temp[t++] = a[i++];
        }
        // 将右边剩余元素放进temp中
        while (j <= right) {
            temp[t++] = a[j++];
        }

        // 将原数组中需要合并排序的内容 替换中成临时数组中排好序的内容

        // 索引置0 准备复制
        t = 0;
        while (left <= right) {
            a[left++] = temp[t++];
        }


    }




}
