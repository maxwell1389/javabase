package com.github.maxwell.base.array;

import org.junit.Test;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/
public class ArrayTest {

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
}
