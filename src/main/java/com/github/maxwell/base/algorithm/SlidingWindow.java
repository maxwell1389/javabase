package com.github.maxwell.base.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * 滑动窗口算法
 */
@Slf4j
public class SlidingWindow {

    /**
     * 长度最小的子数组
     * 利用滑动窗口思路：时间复杂度: O(n) 空间复杂度: O(1)
     * 1. 让 right 向右移，直到子数组和大于等于给定值或者 right 达到数组末尾；
     * 2. 更新最短距离，将 left 像右移一位, sum 减去移去的值；
     * 3. 重复（1）（2）步骤，直到 right 到达末尾，且 left 到达临界位置
     * 示例：
     * 输入: s = 7, nums = [2,3,1,2,4,3]
     * 输出: 2
     * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int l = 0, r = -1; //nums[l...r]为我们的滑动窗口
        int sum = 0;
        int result = nums.length + 1;
        while (l < nums.length) { //窗口的左边界在数组范围内，则循环继续
            if (r + 1 < nums.length && sum < s) {
                r++;
                sum += nums[r];
            } else { //r已经到头 或者 sum >= s
                sum -= nums[l];
                l++;
            }
            if (sum >= s)
                result = (r - l + 1) < result ? (r - l + 1) : result;

        }
        if (result == nums.length + 1) return 0;
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int s = 7;
        log.info("minSubArrayLen {}", minSubArrayLen(s, nums));
    }
}
