// Link : https://leetcode.com/problems/max-chunks-to-make-sorted/?envType=problem-list-v2&envId=v5rancit

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int sum = 0;
        int count = 0;

        // Prefix Sum kind of ::: checking till ith index the sum of element and
        // difference with sum of index are getting balanced or not; if yes then they
        // are in order and becomes a chunk
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] - i;
            if (sum == 0)
                count++;
        }

        return count;

    }
}