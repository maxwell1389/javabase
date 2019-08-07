package com.github.maxwell.base.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
@Slf4j
public class TempTest {

    @Test
    public void test1() {
        int i = 0;
        i = ++i;
        System.out.println(i);
    }

    /**
     * 整字反转
     */
    @Test
    public void testReserveNum() {
        int num = 956876;
        int result = 0;
        int tmp = 0;
        while (num != 0) {
            tmp = num % 10;
            result = result * 10 + tmp;
            num /= 10;
        }
        System.out.print(result);
    }

    /**
     * 字符串反转
     */
    @Test
    public void reverseString() {
        String str = "aedcede";
//        String output = reverseStr(str);
        String output = reverseStrFor(str);
        System.out.printf("output: %s", output);
    }

    /**
     * 100阶乘的结尾0的个数
     *
     * @return
     */
    // 只用计算5因子的个数即可，因为5因子的个数肯定小于2因子的个数
    @Test
    public void trailingZeros() {
        int input = 2009;
        int quotient;
        int fiveFactors = 0;
        quotient = input / 5;
        int i = 1;
        while (quotient != 0) {
            fiveFactors += quotient;
            i++;
            quotient = (int) (input / Math.pow(5, i));
        }
        log.info("result {}", fiveFactors);
    }

    /**
     * 递归实现返回字符串
     *
     * @param input
     * @return
     */
    private String reverseStr(String input) {
        if (input.length() == 1)
            return input;
/*        String substr = input.substring(1);
        String reverstr = reverseStr(substr);
        String returnstr = reverstr + input.charAt(0);
        return returnstr;*/
        return reverseStr(input.substring(1)) + input.charAt(0);
    }

    private String reverseStrFor(String input) {
        if (input.length() == 1) {
            return input;
        }
        int left = 0;
        int right = input.length() - 1;
        char temp;
        char[] orign = input.toCharArray();
        while (left < right) {
            temp = input.charAt(left);
            orign[left] = orign[right];
            orign[right] = temp;
            left++;
            right--;
        }
        return String.valueOf(orign);
    }
}
