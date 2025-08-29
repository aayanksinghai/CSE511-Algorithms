/*
 * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 * 
 * Link: https://leetcode.com/problems/continuous-subarray-sum/description/
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubArraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1);

        long prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            int remainder = (int) (prefixSum % k);

            if (remainderMap.containsKey(remainder)) {
                int previousIndex = remainderMap.get(remainder);
                if (i - previousIndex >= 2) {
                    return true;
                }
            } else {
                remainderMap.put(remainder, i);
            }
        }
        return false;
    }
}
