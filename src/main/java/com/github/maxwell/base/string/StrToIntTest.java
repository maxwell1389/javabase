package com.github.maxwell.base.string;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/
public class StrToIntTest extends Thread {

    private static int MAX_INT = Integer.MAX_VALUE;
    private static int MIN_INT = Integer.MIN_VALUE;

    /**
     * @param input
     * @return
     */
    private int strToInt(String input) {
        if (null == input || "".equals(input))
            return 0;
        char[] arrayStr = input.toCharArray();
        // 字符数组位置
        int index = 0;
        // 处理字符串首位空格
        while (arrayStr[index] == ' ')
            index++;
        // 正负数标识 1：正数 -1：负数
        int negative = 1;
        //判断是否数字的正负
        if (arrayStr[index] == '+' || arrayStr[index] == '-') {
            if (arrayStr[index] == '-')
                negative = -1;
            index++;
        }
        int result = 0;
        while (index < arrayStr.length && Character.isDigit(arrayStr[index])) {
            int c = arrayStr[index] - '0';
            //为正数，且大于MAX_INT
            if (negative > 0 && (result > MAX_INT / 10 || (result == MAX_INT / 10 && c > MAX_INT % 10))) {
                result = MAX_INT;
                break;
            }
            //为负数，且小于MIN_INT
            if (negative < 0 && (result + MIN_INT / 10 > 0 || (result + MIN_INT / 10 == 0 && c + MIN_INT % 10 > 0))) {
                result = MIN_INT;
                break;
            }
            //之前数字乘10，再加上当前字符数字
            result = result * 10 + c;
            index++;
        }
        return (negative > 0) ? result : -result;
    }

    private static int StrToInt(String str) {
        if (str.length() == 0)
            return 0;
        char[] chars = str.toCharArray();
        // 判断是否存在符号位
        int flag = 0;
        if (chars[0] == '+')
            flag = 1;
        else if (chars[0] == '-')
            flag = 2;
        int start = flag > 0 ? 1 : 0;
        int res = 0;// 保存结果
        for (int i = start; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {// 调用Character.isDigit(char)方法判断是否是数字，是返回True，否则False
                int temp = chars[i] - '0';
                res = res * 10 + temp;
            } else {
                return 0;
            }
        }
        return flag != 2 ? res : -res;

    }

    public static void main(String[] args) throws InterruptedException {
        StrToIntTest strToIntTest = new StrToIntTest();
        StringBuffer stringBuffer = new StringBuffer();
        String input = "1213";
        input = "-1213";
//        input = "+1213";
//        input = "a1213";
        int result = strToIntTest.strToInt(input);
        System.out.println(result);
    }

}
