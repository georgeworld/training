package com.georgeinfo.string;

import java.util.Scanner;

/**
 * @author George
 * Create on 2019/9/9
 */
public class StringTest {
    /**
     * 打印有规律的字符，不重复的字符打印一次，连续重复的打印：出现次数*字符，
     * 如：输入"bbbbbbb"输出7b，输入“ascf”输出“ascf”，输入“aabbcc”输出”2a2b2c"
     */
    public static String printRegularCharacters(String text) {
        if (text == null || text.trim().isEmpty()) {
            System.out.println("请输入英文字符串");
            return null;
        } else {
            //遍历字符，寻找连续相同的字符
            StringBuffer strb = new StringBuffer();
            char[] chars = text.toCharArray();
            int len = chars.length;
            int count = 0;
            for (int i = 0; i < len; i++) {
                char c = chars[i];
                if (i < len - 1) {
                    if (c == chars[i + 1]) {
                        count++;
                    } else {
                        if (count > 0) {
                            count++;
                            strb.append(count + "" + c);
                            count = 0;
                        } else {
                            strb.append(String.valueOf(c));
                        }
                    }
                } else {
                    if (count > 0) {
                        count++;
                        strb.append(count + "" + c);
                        count = 0;
                    } else {
                        strb.append(String.valueOf(c));
                    }
                }
            }

            return strb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            if (text.trim().equals("exit")) {
                break;
            }
            String formatText = printRegularCharacters(text);
            System.out.println("您输入的字符串格式化后为：" + formatText);
        }
    }
}
