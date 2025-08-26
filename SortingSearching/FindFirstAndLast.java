/*
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.
 * 
 * Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/?envType=problem-list-v2&envId=binary-search
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */



public class FindFirstAndLast {

    public int[] searchRange(int[] nums, int target) {
        // Linear Search 
        /*
        int firstEncounter = -1, lastEncounter = -1;
        int[] ans = new int[2];

        for(int i = 0; i < nums.length; i++){
            if(target == nums[i]){
                if(firstEncounter == -1) firstEncounter = i;
                lastEncounter = i;
            }
        }
        ans[0] = firstEncounter;
        ans[1] = lastEncounter;
        return ans;
        */
    

     // Optimal Approach - Binary Search
        int firstOcc = firstOccurrence(nums, target);
        if(firstOcc == -1) return new int[]{-1,-1};
        int lastOcc = lastOccurrence(nums, target);
        return new int[]{firstOcc, lastOcc};
    }

    public int firstOccurrence(int[] arr, int target){
        int low = 0, high = arr.length-1, first = -1;
        while(low <= high){
            int mid = (low+high)/2;
            if(arr[mid] == target){
                high = mid-1;
                first = mid;
            }
            else if(arr[mid] < target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        return first;
    }

    public int lastOccurrence(int[] arr, int target){
        int low = 0, high = arr.length-1, last = -1;
        while(low <= high){
            int mid = (low+high)/2;
            if(arr[mid] == target){
                low = mid+1;
                last = mid;
            }
            else if(arr[mid] < target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        return last;
    }
}