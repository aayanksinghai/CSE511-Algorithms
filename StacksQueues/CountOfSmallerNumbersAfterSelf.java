/*
 * Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to 
 * the right of nums[i].
 * 
 * Link: https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/?envType=problem-list-v2&envId=binary-search
 * Author: AAYANK SINGHAI (MT2025001)
 */


import java.util.ArrayList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {

     private class ValueIndexPair { //inner class store number & original index together
        int value;
        int originalIndex;

        ValueIndexPair(int value, int originalIndex) {
            this.value = value;
            this.originalIndex = originalIndex;
        }
    }


    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int n = nums.length;
        ValueIndexPair[] items = new ValueIndexPair[n];
        for (int i = 0; i < n; i++) {
            items[i] = new ValueIndexPair(nums[i], i);
        }

        int[] counts = new int[n];
        mergeSort(items, 0, n - 1, counts);

        // Convert the final counts array to a List 
        List<Integer> resultList = new ArrayList<>(n);
        for (int count : counts) {
            resultList.add(count);
        }
        return resultList;
    }

    private void mergeSort(ValueIndexPair[] items, int start, int end, int[] counts) {
        if (start >= end) {
            return; // Base case: the segment has 0 or 1 elements.
        }

        int mid = start + (end - start) / 2;
        mergeSort(items, start, mid, counts);
        mergeSort(items, mid + 1, end, counts);
        merge(items, start, mid, end, counts);
    }

    private void merge(ValueIndexPair[] items, int start, int mid, int end, int[] counts) {
        List<ValueIndexPair> temp = new ArrayList<>();
        int leftPtr = start;
        int rightPtr = mid + 1;

        while (leftPtr <= mid && rightPtr <= end) {
            // If the element from the left half is larger than the element from the right half,
            // it's an inversion.
            if (items[leftPtr].value > items[rightPtr].value) {
                // This means items[leftPtr] is also larger than all remaining elements
                // in the right half of the array.
                int remainingInRight = end - rightPtr + 1;
                counts[items[leftPtr].originalIndex] += remainingInRight;

                // Add the larger element (from the left) to our temp sorted list.
                temp.add(items[leftPtr]);
                leftPtr++;
            } else {
                // The element from the right half is larger or equal.
                temp.add(items[rightPtr]);
                rightPtr++;
            }
        }

        // Add any remaining elements from the left half.
        while (leftPtr <= mid) {
            temp.add(items[leftPtr]);
            leftPtr++;
        }

        // Add any remaining elements from the right half.
        while (rightPtr <= end) {
            temp.add(items[rightPtr]);
            rightPtr++;
        }

        // Copy the sorted (and merged) elements from temp back into the original array.
        for (int i = 0; i < temp.size(); i++) {
            items[start + i] = temp.get(i);
        }
    }
}