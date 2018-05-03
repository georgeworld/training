package com.georgeinfo.algorithm.sort;

import com.util.CommonException;

/**
 * 冒泡排序算法
 *
 * @author George<GeorgeWorld   @   qq.com>
 */
public class BubbleSort {
    private int[] arrayData;

    public BubbleSort(int[] arrayData) {
        this.arrayData = arrayData;
    }

    /**
     * 常规冒泡排序算法：
     * 对数组obj中的元素以冒泡排序算法进行排序
     */
    public void sort() {
        if (arrayData == null) {
            throw new NullPointerException("The argument can not be null!");
        }
        if (arrayData.length == 1) {
            throw new CommonException("## 一个值，你跟谁比啊？");
        }

        for (int i = 0; i < arrayData.length-1; i++) {
            //一轮内循环比较以后，arrayData[i]右侧的数值都比它大了（相当于是每次内循环，都把最小值放到i的位置）
            for (int j = i + 1; j < arrayData.length; j++) {
                if (arrayData[i] > arrayData[j]) {
                    //交换相邻值
                    arrayData[i] = arrayData[i] ^ arrayData[j];
                    arrayData[j] = arrayData[i] ^ arrayData[j];
                    arrayData[i] = arrayData[i] ^ arrayData[j];
                }
            }
        }
    }
}