/*
 *You are given an integer array bloomDay, an integer m and an integer k.

You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.

The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.

Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1. 
 * 
 * Link: https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */


public class MinimumDaystoMakeMBouquet {
    public int minDays(int[] bloomDay, int m, int k) {
        long noOfFlowers = (long) m * k;
        int n = bloomDay.length;
        if (noOfFlowers > n)
            return -1; //Edge case returning -1 Impossible

        int mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            mini = Math.min(mini, bloomDay[i]);
            maxi = Math.max(maxi, bloomDay[i]);
        }

        // Linear Approach 
        /*
        for(int i = mini; i <= maxi; i++){
            if(possible(nbloomDayums, i, m, k) == true) return i;
        }
        return -1;
        */

        // Binary Search Approach
        int left = mini, right = maxi, ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (possible(bloomDay, mid, m, k)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public boolean possible(int[] arr, int day, int bouquet, int flower) {
        int counter = 0, noOfBouquet = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= day)
                counter++; //adjacent flower increasing counter
            else {
                noOfBouquet += (counter / flower);
                counter = 0;
            }
        }

        noOfBouquet += (counter / flower);
        if (noOfBouquet >= bouquet)
            return true;
        return false;
    } 
}
