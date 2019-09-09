package com.georgeinfo.algorithm.array;

import java.util.*;

/**
 * @author George
 * Create on 2019/9/9
 * 有一个N个整数组成的数组，例如[1,3,5,2,5,3,1,5,2]
 * Q1: 如何找到最大的数
 * Q2: 最大的数可能出现多次，返回所有最大的数所在的下标（例子中返回[2,4,7]）
 * Q3: 等概率地返回最大的数出现的所有下标中的一个
 * Q4: 只扫描一遍数组(时间复杂度N)，并且只使用O(1)常数个空间，等概率地返回最大的数出现的所有下标中的一个
 */
public class ArraySearch {
    private static int[] data = new int[]{1, 3, 5, 2, 5, 3, 1, 5, 2};

    /**
     * 找到最大的数
     */
    public static int printMaxNum() {
        //使用交换排序，寻找最大的数
        int maxNum = -1;
        for (int n : data) {
            if (n > maxNum) {
                maxNum = n;
            }
        }

        System.out.println("最大的数字是：" + maxNum);
        return maxNum;
    }

    /**
     * 打印最大的数所在的游标地址
     * data=[1,3,5,2,5,3,1,5,2]
     */
    public static List<Integer> printIndexOfMaxNum() {
        List<Integer> indexList = new ArrayList<>();
        //使用交换排序，寻找最大的数
        int maxNum = -1;
        for (int index = 0; index < data.length; index++) {
            int n = data[index];
            if (n > maxNum) {
                maxNum = n;
                indexList.clear();
                indexList.add(index);
            } else if (n == maxNum) {
                indexList.add(index);
            }
        }

        //打印最大数出现位置的下标
        Object[] array = indexList.toArray();
        System.out.println(Arrays.toString(array));
        return indexList;
    }

    /**
     * 打印最大的数所在的游标地址
     * data=[1,3,5,2,5,3,1,5,2]
     */
    public static List<Integer> printIndexOfMaxNum2() {
        Map<Integer, List<Integer>> numIndexMap = new HashMap<Integer, List<Integer>>();
        //使用交换排序，寻找最大的数
        int maxNum = -1;
        for (int index = 0; index < data.length; index++) {
            int n = data[index];
            if (n > maxNum) {
                maxNum = n;
            }

            //记录数的下标
            List<Integer> indexList = numIndexMap.get(n);
            if (indexList == null || indexList.isEmpty()) {
                indexList = new ArrayList<>();
                numIndexMap.put(n, indexList);
            }
            indexList.add(index);
        }

        //打印最大数出现位置的下标
        List<Integer> indexList = numIndexMap.get(maxNum);
        Object[] array = indexList.toArray();
        System.out.println(Arrays.toString(array));

        return indexList;
    }


    /**
     * 等概率地返回最大的数出现的所有下标中的一个
     * data=[1,3,5,2,5,3,1,5,2]
     */
    public static void randomGetMaxValueIndex() {
        List<Integer> indexList = printIndexOfMaxNum();

        //从最大数下标列表中，随机取一个
        Random random = new Random();
        int indexIndex = random.nextInt(indexList.size());
        System.out.println(indexList.get(indexIndex));
    }

    public static void main(String[] args) {
        randomGetMaxValueIndex();
    }
}
