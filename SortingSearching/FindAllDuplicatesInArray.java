/*
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and 
 * each integer appears at most twice, return an array of all the integers that appears twice.
 * 
 * Link: https://leetcode.com/problems/find-all-duplicates-in-an-array/description/?envType=problem-list-v2&envId=hash-table
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesInArray {
    public List<Integer> findDuplicates(int[] nums) {
        // Brute Force Approach TC: O(N) with SC: O(N) HashMap
        /*
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }

        List<Integer> ans = new ArrayList<>();

        for(Map.Entry<Integer, Integer> mp : map.entrySet()){
            int value = mp.getValue();
            if(value == 2) ans.add(mp.getKey());
        }
        return ans;
        */

        // Optimal Approach 
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            int ele = Math.abs(nums[i])-1;  //math.abs bcz sign of no may be changed. fetching value and mapping it to index

            if(nums[ele] < 0){  //negative number, it is already been seen hence duplicate
                ans.add(ele + 1); //original value addition
            }else{
                nums[ele] = -nums[ele]; //flip sign hence visited
            }
        }
        return ans;
    }
}
