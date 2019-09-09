package com.util;

import java.util.Random;

/**
 * @author George
 * Create on 2019/9/9
 */
public class MathUtil {
    /**
     * 生成[min,max]区间的随机数
     *
     * @param min 随机数区间开始值（包括）
     * @param max 随机数区间结束值（包括）
     * @return [min, max]之间的随机数
     */
    public static int getRandom(int min, int max) {
        Random random = new Random();

        int num = random.nextInt(max) % (max - min + 1) + min;
        return num;
    }
}
