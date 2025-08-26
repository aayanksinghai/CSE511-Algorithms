/*
 * Given two integer arrays nums1 and nums2, return an array of their intersection. 
 * Each element in the finalArrayult must appear as many times as it shows in both arrays and you 
 * may return the finalArrayult in any order.
 * 
 * Link: https://leetcode.com/problems/intersection-of-two-arrays-ii/description/?envType=problem-list-v2&envId=sorting
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IntersectionOfTwoArrays {
    public int[] intersect(int[] nums1, int[] nums2) {
        //Sorting approach and two pointer traversal
        /*
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> ans = new ArrayList<>();

        int i = 0, j = 0;
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] < nums2[j]) i++;
            else if(nums2[j] < nums1[i]) j++;
            else{
                ans.add(nums1[i]);
                i++;
                j++;
            }
        }
        if(ans.size() == 0) return new int[]{};
        int[] ansArray = new int[ans.size()];
        for(int m = 0; m < ans.size(); m++){
            ansArray[m] = ans.get(m);
        }

        return ansArray;
        */

        // Hashmap approach
        HashMap<Integer,Integer> arrayMap=new HashMap<>();
        List<Integer> finalArray = new ArrayList<>();
        for(int i = 0; i < nums1.length; i++){
            arrayMap.put(nums1[i],arrayMap.getOrDefault(nums1[i],0)+1);
        }
        for(int i = 0; i < nums2.length; i++){
            if(arrayMap.containsKey(nums2[i]) && arrayMap.get(nums2[i]) > 0){
                finalArray.add(nums2[i]);
                arrayMap.put(nums2[i],arrayMap.get(nums2[i])-1);
            }
        }
        int[] ans = new int[finalArray.size()];
        int j = 0;
            for(int i : finalArray){
                ans[j++] = i;
            }
        return ans;
    }
}