package com.github.maxwell.base;

import org.junit.Test;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/
public class TempTest {

    @Test
    public void test1() {
        int i = 0;
        i = ++i;
        System.out.println(i);
    }

    /**
     * 合并两个有序数组
     */
    @Test
    public void testMergeSortedArray() {
        int a[] = {1, 4, 7, 9, 11, 15, 17, 18};
        int b[] = {2, 3, 12, 16, 20, 24, 25, 27, 29, 36, 39, 56, 79};
        int ab[] = new int[a.length + b.length];
        int ai = 0;
        int bj = 0;
        int abk = 0;
        while (ai < a.length && bj < b.length) {
            if (a[ai] <= b[bj]) {
                ab[abk++] = a[ai++];
            } else {
                ab[abk++] = b[bj++];
            }
        }
        while (ai < a.length) {
            ab[abk++] = a[ai++];
        }
        while (bj < b.length) {
            ab[abk++] = b[bj++];
        }
        for (int abv : ab) {
            System.out.print(abv + " ");
        }
    }

    /**
     * 整字反转
     */
    @Test
    public void testReserveNum() {
        int num = 956876;
        int result = 0;
        int tmp = 0;
        while (num != 0){
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
    public void reverseString(){
        String str = "aedc";
        String output = reverseStr(str);
        System.out.printf("output: %s",output);
    }

    /**
     * 递归实现返回字符串
     * @param input
     * @return
     */
    private String reverseStr(String input){
        if(input.length() == 1)
            return input;
/*        String substr = input.substring(1);
        String reverstr = reverseStr(substr);
        String returnstr = reverstr + input.charAt(0);
        return returnstr;*/
        return reverseStr(input.substring(1)) + input.charAt(0);
    }
}
