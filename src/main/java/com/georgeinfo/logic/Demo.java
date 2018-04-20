/*
* Copyright (c) George software studio, All Rights Reserved.
* George <GeorgeWorld@qq.com> | QQ:178069108 | www.georgeinfo.com 
*/
package com.georgeinfo.logic;

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
}
