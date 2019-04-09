package com.github.maxwell.base.algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 贪心算法
 * 给定一个整数，求从整数当中删去k个数字后的新整数最小值。
 * 解法：
 * 如何把高位的数字降低呢？很简单，我们把原整数的所有数字从左到右进行比较，
 * 如果发现某一位的数字大于它右面的数字，那么在删除该数字后，
 * 必然会使得该数位的值降低，因为右面比它小的数字顶替了它的位置。
 */
public class Greedy {

    /**
     * 删除整数的K个数字，获得删除后的最小值
     *
     * @param num 原整数
     * @param k   删除数量
     * @return
     */
    private static String removeKDights(String num, int k) {
        //新整数的最终长度 = 原整数长度 - k
        int newLength = num.length() - k;
        //创建一个栈，用于接收所有的数字
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); ++i) {
            //遍历当前数字
            char c = num.charAt(i);
            //当栈顶数字大于遍历到当前数字，栈顶数字出栈（相当于删除数字）
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            stack[top++] = c;
        }
        //找到栈中第一个非0数字的位置，以此构建新的整数字符串
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++;
        }
        return offset == newLength ? "0" : new String(stack, offset, newLength - offset);

    }

    public static void main(String[] args) {
/*        System.out.println(removeKDights("1593212", 3));
        System.out.println(removeKDights("30200", 1));
        System.out.println(removeKDights("10", 2));
        System.out.println(removeKDights("541270936", 3));*/
/*        List<Map<String,String>> lst = new ArrayList<>();
        Random r = new Random();
        Map<String, String> map;
        for (int i = 0; i < 10; i++) {
            int num = (int) (Math.random() * 5 + 1);
//            int a = r.nextInt(5) + 1;
//            System.out.println("a:" + a);
            System.err.println(num);
            map = new HashMap<>();
            map.put(String.valueOf(num), String.valueOf(num));
            lst.add(map);
        }
        lst.stream().map(a -> (a.containsKey("3"))?(a.put("3","4")):("")).collect(Collectors.toList());
        System.out.println(lst);*/
        String sYmdFormat = "yyyy-MM-dd";
        String sYmdhmsFormat = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dfYmd = DateTimeFormatter.ofPattern(sYmdFormat);
        DateTimeFormatter dfYmdhms = DateTimeFormatter.ofPattern(sYmdhmsFormat);
        String syesdate = LocalDateTime.now().minusDays(1).format(dfYmd);
        String stomdate = LocalDateTime.now().plusDays(1).format(dfYmd);
        System.out.println(syesdate);
        System.out.println(stomdate);
        LocalDateTime ldtYester = LocalDateTime.parse(syesdate + " 23:59:59", dfYmdhms);
        LocalDateTime ldtTomorrow = LocalDateTime.parse(stomdate + " 00:00:00", dfYmdhms);
        System.out.println(ldtYester.format(dfYmdhms));
        System.out.println(ldtTomorrow.format(dfYmdhms));
        System.out.println(ldtYester.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(ldtTomorrow.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
/*        Instant instant = Date.from(Instant.now()).toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));*/
        Double dd = 27.33;
        System.out.println(dd.intValue());
    }
}
