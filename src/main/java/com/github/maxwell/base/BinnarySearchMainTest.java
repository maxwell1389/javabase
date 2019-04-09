package com.github.maxwell.base;

import java.util.*;

public class BinnarySearchMainTest {

    /**
     * 二分法查找
     *
     * @param input
     * @param low
     * @param high
     * @param value
     * @return
     */
    private static int binSearch(int[] input, int low, int high, int value) {
        if (low >= high)
            return low;
        int mid = low + ((high - low) >> 1);
        if (input[mid] == value) {
            return mid;
//        if(input[mid] == value){
            //第一个元素相同
/*            if(mid == 0 || input[mid-1]  != 0 )
                return mid;
            else
                return binSearch(input, low, high-1, value);*/
        } else if (input[mid] < value) {
            return binSearch(input, mid + 1, high, value);
        } else {
            return binSearch(input, low, mid - 1, value);
        }
    }

    private static int[] searchNearNums(int[] input, int index, int num) {
        int[] results = new int[num];
        if (index == 0) {
            for (int i = 0; i < num; i++) {
                results[i] = i;
            }
            return results;
        }
        if (index == input.length - 1) {
            int start = input.length - 1;
            int end = input.length - num;
            int rindex = 0;
            for (int i = start; i >= end; i--) {
                results[rindex] = i;
                rindex++;
            }
            return results;
        }
        int mid = num / 2 + 1;
        int rindex = 0;
        results[rindex] = index;
        int left = index;
        int right = index;
        for (int i = 0; i < mid; i++) {
            if (i == 0) {
                left--;
                right++;
                results[++rindex] = left;
                if (rindex == num - 1)
                    return results;
                results[++rindex] = right;
                if (rindex == num - 1)
                    return results;
            } else {
                left--;
                if (left >= 0) {
                    results[++rindex] = left;
                    if (rindex == num - 1)
                        return results;
                    if ((++right) < input.length) {
                        results[++rindex] = right++;
                        if (rindex == num - 1)
                            return results;
                    } else {
                        results[++rindex] = --left;
                        if (rindex == num - 1)
                            return results;
                    }
                } else {
                    results[++rindex] = ++right;
                    if (rindex == num - 1)
                        return results;
                    results[++rindex] = ++right;
                    if (rindex == num - 1)
                        return results;
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<Integer> lst = Arrays.asList(8, 11, 19, 23, 27, 33, 45, 67, 98);
/*        int[] b = lst.stream().mapToInt(Integer::valueOf).toArray();
//        int[] b = {8, 11, 11, 19, 23, 27, 33, 45, 67, 98};
        int value = 67;
        int index = binSearch(b, 0, b.length - 1, value);
        System.out.println("index:" + index);
        int nearTotals = 7;
        int[] nearNums = searchNearNums(b, index, nearTotals);
        for (int i = 0; i < nearNums.length; i++) {
            System.out.print(nearNums[i] + " ");
        }
        System.out.println("\n");
        for (int i = 0; i < nearNums.length; i++) {
            System.out.print(lst.get(nearNums[i]) + " ");
        }
        System.out.println("\n");
        System.out.println(lst.contains(20));
        System.out.println(lst.stream().filter(a -> (a>=19 && a<=27)).count());
        System.out.println(lst.stream().filter(a -> a>=19 ).filter(a -> a<=27).count());*/
        Set<Integer> sets = new LinkedHashSet<>();
        Random r = new Random();
        List<Integer> lstint = new ArrayList<>();
        while (true) {
            sets.add(r.nextInt(9) + 1);
//            r.nextInt(10);
//            lstint.add(r.nextInt(10));
            if(sets.size() == 9) break;
        }
        int[] seats = sets.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(sets);
        System.out.println(seats);
        for (int a:seats) {
            System.out.printf(a + " ");
        }
//        System.out.println(lstint);
    }
}
