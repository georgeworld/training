/*
* Copyright (c) George software studio, All Rights Reserved.
* George <GeorgeWorld@qq.com> | QQ:178069108 | www.georgeinfo.com 
*/
package com.georgeinfo.logic;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author George <GeorgeWorld@qq.com>
 */
public class Demo {
    /**
     * 笔试题：打印出如下规律的字符串
     * 1
     * 2*3
     * 4*5*6
     * 7*8*9*10
     * 7*8*9*10
     * 4*5*6
     * 2*3
     * 1
     */
    public static void printStar(int n) {
        String[] stra = new String[n];
        int preNum = 0;
        for (int i = 1; i <= n; i++) {
            StringBuffer strb = new StringBuffer();
            for (int j = 1; j <= i; j++) {
                preNum++;
                strb.append(preNum).append("*");
            }
            String line = null;
            if (strb.toString().endsWith("*")) {
                line = strb.toString().substring(0, strb.length() - 1);
            } else {
                line = strb.toString();
            }
            strb.setLength(0);
            System.out.println(line);
            stra[i - 1] = line;
        }

        int len = stra.length - 1;
        for (int i = len; i >= 0; i--) {
            System.out.println(stra[i]);
        }

    }

    /**
     * 数组倒置<br>
     * 把类似   int[] nums = new int[]{3,2,5,1,8,0};
     * 这样的数组，倒置反转成另外一个数组。
     */
    public static int[] arrayReverse(int[] incomming) {
        if (incomming == null) {
            return null;
        }

        int len = incomming.length;
        int halfLen = len / 2;
        for (int i = 0; i < halfLen; i++) {
            incomming[i] = incomming[i] ^ incomming[len - i - 1];
            incomming[len - i - 1] = incomming[i] ^ incomming[len - i - 1];
            incomming[i] = incomming[i] ^ incomming[len - i - 1];
        }
        return incomming;
    }

    /**
     * 在一层循环中删除List中的元素(采用倒序删除法)
     * 因为删除list中的一个元素后，List会重拍，后面的元素向前挤，采用倒序删除，可以确保每次都删除最后一个元素。
     **/
    public static List<AtomicInteger> removeListOnRunning(List<AtomicInteger> list, AtomicInteger beRemoved) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        //倒序删除
        int len = list.size();
        for (int i = len - 1; i >= 0; i--) {
            AtomicInteger ai = list.get(i);
            if (beRemoved == ai) {
                list.remove(ai);
                len--;
            }
        }

        return list;
    }

    /**
     * 在一层循环中删除List中的元素(采用Iterator遍历删除)
     **/
    public static List<AtomicInteger> removeListOnRunningByIterator(List<AtomicInteger> list, AtomicInteger beRemoved) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Iterator<AtomicInteger> iterator = list.iterator();
        while (iterator.hasNext()) {
            AtomicInteger ai = iterator.next();
            if (ai.equals(beRemoved)) {
                iterator.remove();
            }
        }

        return list;
    }

}
