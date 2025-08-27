/*
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.
 * 
 * Link: https://leetcode.com/problems/3sum/description/?envType=problem-list-v2&envId=two-pointers
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
    // Better Approach TC: O(N^2 * logK)
        /*
        Set<List<Integer>> tripleSet = new HashSet<>();
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            Set<Integer> hashElementSeen = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int thirdElement = -(nums[i] + nums[j]);
        
                if (hashElementSeen.contains(thirdElement)) {
                    List<Integer> elements = new ArrayList<>();
                    elements.add(nums[i]);
                    elements.add(nums[j]);
                    elements.add(thirdElement);
        
                    Collections.sort(elements);
                    tripleSet.add(elements);
                }
        
                hashElementSeen.add(nums[j]);
            }
        }
        List<List<Integer>> ansArray = new ArrayList<>(tripleSet);
        return ansArray;
        */

        // Optimal Approach TC: O(Nlogn * N^2)

        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue; //adjacent same eleIndex

            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum < 0)
                    j++;
                else if (sum > 0)
                    k--;
                else {
                    List<Integer> element = new ArrayList<>();
                    element.add(nums[i]);
                    element.add(nums[j]);
                    element.add(nums[k]);
                    ans.add(element);

                    j++; //skipping duplicates
                    k--;
                    while (j < k && nums[j] == nums[j - 1])
                        j++;
                    while (j < k && nums[k] == nums[k + 1])
                        k--;
                }
            }
        }
        return ans;
    }
}
