/*
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).
 * 
 * Link: https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * Author: AAYANK SINGHAI (MT2025001)
 */

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Brute Force Approach
        // Merge the two sorted array into another sorted array
        // Then find the median
        // TC: O(N1+N2) SC: O(N1+N2)
        /*
         * int len1 = arr1.length;
         * int len2 = arr2.length;
         * int newlen = len1 + len2;
         * int[] ans = new int[newlen];
         * 
         * int i = 0, j = 0, index = 0;
         * while(i < len1 && j < len2){
         * if(arr1[i] < arr2[j]){
         * ans[index++] = arr1[i++];
         * }else{
         * ans[index++] = arr2[j++];
         * }
         * }
         * if(i >= len1){
         * while(j < len2){
         * ans[index++] = arr2[j++];
         * }
         * }else{
         * while(i < len1){
         * ans[index++] = arr1[i++];
         * }
         * }
         * double median = 0.0;
         * if(newlen%2 == 0){
         * median = (ans[newlen/2] + ans[(newlen/2) - 1])/2.0;
         * }else{
         * median = ans[newlen/2];
         * }
         * 
         * return median;
         */

        // Optimal Approach using Binary search TC: O(min(logn, logm))
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1); // ensuring size arr1 not larger than arr2

        int n = n1 + n2;
        int left = (n1 + n2 + 1) / 2;

        int low = 0, high = n1;
        while (low <= high) {
            int mid1 = (low + high) >>> 1;
            int mid2 = left - mid1;

            int l1 = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? nums1[mid1] : Integer.MAX_VALUE;
            int l2 = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;
            int r2 = (mid2 < n2) ? nums2[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 == 1)
                    return Math.max(l1, l2);
                else
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }
        return 0;
    }
}
