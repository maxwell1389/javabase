package com.github.maxwell.base.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogicMainTest {

    /**
     * 截取字符
     *
     * @param str
     * @param len
     * @param step
     */
    private static void subString(String str, int len, int step) {
        for (int i = 0; i < step; i++) {
            System.out.println(len + ":" + str.substring(i, i + (len)));
        }
        if (len > 1)
            subString(str, (len - 1), step + 1);
    }

    /**
     * 两数之和等于目标数
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 求相反数
     *
     * @param nums
     * @param target
     * @return
     */
    private static List twoSumZero(int[] nums, int target) {
        List<int[]> lst = new ArrayList<int[]>();
        Map<Integer, Integer> mapInt = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (mapInt.containsKey(value)) {
                lst.add(new int[]{value, -value});
            }
            mapInt.put(nums[i], nums[i]);
        }
        return lst;
    }

    /**
     * 走台阶，求共有多少种走法(动态规划)
     *
     * @param n
     * @return
     */
    private static long climbStairs(long n) {
        if (n <= 2)
            return n;
        //第一台阶，两种走
        long oneStep = 2;
        //第二台阶，一种走
        long twoStep = 1;
        //总共走法
        long totalStep = 0;
        for (int step = 2; step < n; step++) {
            totalStep = oneStep + twoStep;
            twoStep = oneStep;
            oneStep = totalStep;
        }
        return totalStep;
    }

    public static void main(String[] args) {
        /**
         * 规则拆分
         String str = "1234560";
         subString(str, str.length(), 1);
         **/
        /**
         * 两数之和等于目标数
         int[] ints = {2, 7, 10, 13};
         int[] rints = twoSum(ints, 9);
         System.out.println(rints);
         **/

/*        int [] oints = {9, 1, 3, 8, -10, 7, -9, -2, -8, 2};
        List<int[]> rlst = twoSumZero(oints, 0);
        System.out.println(rlst.size());
        if(rlst != null && rlst.size() > 0){
            StringBuilder sbd;
            for(int[] ints:rlst){
                sbd = new StringBuilder("(");
                for (int i:ints){
                    sbd.append( i + ",");
                }
                sbd.deleteCharAt(sbd.length()-1);
                sbd.append(")");
                System.out.println(sbd.toString());
            }
        }*/

        /***台阶走法测试开始 **/
        long n = 100;
        long step = climbStairs(n);
        System.out.printf("%s", step);
        /***台阶走法测试结束 **/
    }
}
