/*
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

 *
 * Link: https://leetcode.com/problems/4sum/description/?envType=problem-list-v2&envId=two-pointers 
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
    // Better Solution TC: O(N^3 * logM)
        /*
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        
        Set<List<Integer>> hashInt = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Set<Long> hashset = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    long sum = (long) nums[i] + nums[j] + nums[k];
                    long fourthElement = target - sum;
        
                    if (hashset.contains(fourthElement)) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k], (int) fourthElement);
                        Collections.sort(list);
                        hashInt.add(list);
                    }
        
                    hashset.add((long) nums[k]);
                }
            }
        }
        
        ans.addAll(hashInt);
        return ans;
        */

        // Optimal Solution TC: O(N^3)
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);

        
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) // Skip duplicates for i
                continue;

            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) // Skip duplicates for j
                    continue;

                int k = j + 1;
                int l = n - 1;

                while (k < l) {
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];

                    if (sum == target) {
                        //=quadruplet found summing up to target
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                        ans.add(temp);

                        
                        k++; // Skipping dupl for k and l
                        l--;
                        while (k < l && nums[k] == nums[k - 1])
                            k++;
                        while (k < l && nums[l] == nums[l + 1])
                            l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        return ans;
    }  
}
