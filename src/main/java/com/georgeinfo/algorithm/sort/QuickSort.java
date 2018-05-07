package com.georgeinfo.algorithm.sort;

import java.util.Arrays;

/**
 * @author George (GeorgeWorld@qq.com)
 */
public class QuickSort {
    public static void quickSort(int[] a) {
        if (a.length > 0) {
            quickSortArray(a, 0, a.length - 1);
        }
    }

    /**
     * 对数组进行快速排序，原始数组如：<br>
     * { 5 ,2 ,8 , 9 , 2 , 3 , 4 , 9 }<br>
     * 开始【两段式循环比较】<br>
     * 开始：       5    2    8    9    2    3    4    9
     * -------------i                                  j
     * 第一次找     5    2    8    9    2    3    4    9
     * -----------------------i                   j
     * 交换：       5    2    4    9    2    3    8    9
     * -----------------------i                   j
     * 第二次找     5    2    4    9    2    3    8    9
     * ----------------------------i         j
     * 交换：       5    2    4    3    2    9    8    9
     * ----------------------------i         j
     * 第三次找     5    2    4    3    2    9    8    9
     * ---------------------------------ij
     * 调整key：    2    5    4    3    5    9    8    9
     * ---------------------------------ij
     **/
    public static void quickSortArray(int[] dataArray, int first, int last) {
        //如果第一个索引和最后一个索引交叉了，说明已经找到头了，跳出递归
        if (first > last) {
            return;
        }

        //将第一个索引和最后一个索引的位置缓存起来，以便下次递归时使用
        int i = first;
        int j = last;

        //选取一个期望中的数组中间值，用作对比参考值，以便选取数组第一个元素值，期望它是数组中数值大小位列中间的数值
        int key = dataArray[first];

        //当i与j不交叉时，就一直循环比较数组左右两侧的值与key的比较
        while (i < j) {
            //从右向左，寻找比key小的值，如果找不到，则移动j游标，向前滚动
            while (i < j && dataArray[j] > key) {
                j--;
            }

            //从左向右，寻找比key大的值，如果找不到，则移动i游标，向后滚动
            while (i < j && dataArray[i] <= key) {
                i++;
            }

            //如果程序走到这一步，说明左右两侧的循环都终止了，也就是左侧遇到了比key大的值，
            //右侧遇到了比key小的值，此时，当然是交换i和
            //j位置的值，这样才能确保以key为中心，左小右大。
            if (i < j) {
                //交换i 和 j 位置的值
                int[] ab = swap(dataArray[i], dataArray[j]);
                dataArray[i] = ab[0];
                dataArray[j] = ab[1];
            }
        }

        //一轮循环完成，交换当前ij汇聚点与当前key位置的值
        int[] ab = swap(dataArray[first], dataArray[i]);
        dataArray[first] = ab[0];
        dataArray[i] = ab[1];

        //然后，以当前key位置为界，对左半数组再进行排序
        quickSortArray(dataArray, first, i - 1);
        //对右半数组进行快速排序
        quickSortArray(dataArray, i + 1, last);
    }

    //交换两个值
    private static int[] swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        return new int[]{a, b};
    }
}