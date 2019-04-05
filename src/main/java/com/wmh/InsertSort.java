package com.wmh;

/**
 * Created by 周大侠
 * 2019-02-03 10:41
 */
public class InsertSort {

    /**
     * 直接插入排序
     *
     * @param a
     */
    public void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            // 复制当前元素
            int temp = a[i];
            int j = i;
            for (; j > 0 && temp < a[j - 1]; j--) {
                // 将前一个小的元素向后移动一个
                a[j] = a[j - 1];
            }
            // 此时表示前面的元素都比temp元素小 可以把temp的值赋值给当前位置
            a[j] = temp;
        }
    }
}
