/*
 * You are given an integer array nums of length n which represents a permutation of all the integers in the range [0, n - 1].
The number of global inversions is the number of the different pairs (i, j) where:
0 <= i < j < n
nums[i] > nums[j]
The number of local inversions is the number of indices i where:

0 <= i < n - 1
nums[i] > nums[i + 1]
Return true if the number of global inversions is equal to the number of local inversions.
 * 
 * Link: https://leetcode.com/problems/global-and-local-inversions/description/
 * Author: AAYANK SINGHAI (MT2025001)
 * 
 */

public class GlobalAndLocalInversion {
    public boolean isIdealPermutation(int[] nums) {
        // Optimal Approach TC: O(nlogn)
        // Global Inversion is like Counting Inversion
        int n = nums.length;
        long localInversion = getLocalInversion(nums);
        long globalInversion = mergeSort(nums, 0, n - 1);

        if (globalInversion == localInversion)
            return true;
        return false;
    }

    private int getLocalInversion(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                count++;
        }
        return count;
    }

    private long merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1]; // temp array for merging

        int left = low;
        int right = mid + 1;
        int index = 0;
        long cnt = 0; // Count variable to count the pairs
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp[index++] = arr[left++];
            } else {
                temp[index++] = arr[right++];
                cnt += (mid - left + 1);
            }
        }

        while (left <= mid) {
            temp[index++] = arr[left++];
        }

        while (right <= high) {
            temp[index++] = arr[right++];
        }
        System.arraycopy(temp, 0, arr, low, high - low + 1);
        return cnt;
    }

    private long mergeSort(int[] arr, int low, int high) {
        long cnt = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;
            cnt += mergeSort(arr, low, mid);
            cnt += mergeSort(arr, mid + 1, high);
            cnt += merge(arr, low, mid, high);
        }
        return cnt;
    }
}
