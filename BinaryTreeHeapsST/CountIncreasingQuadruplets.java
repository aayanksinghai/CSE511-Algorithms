/*
 * Given a 0-indexed integer array nums of size n containing all numbers from 1
 * to n, return the number of increasing quadruplets.
 * 
 * A quadruplet (i, j, k, l) is increasing if:
 * 
 * 0 <= i < j < k < l < n, and
 * nums[i] < nums[k] < nums[j] < nums[l].
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link:
 * https://leetcode.com/problems/count-increasing-quadruplets/description/?
 * envType=problem-list-v2&envId=binary-indexed-tree
 */

public class CountIncreasingQuadruplets {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        if (n < 4) {
            return 0;
        }

        long totalQuadruplets = 0;
        BIT left_bit = new BIT(n);

        for (int j = 1; j < n - 2; j++) {
            left_bit.update(nums[j - 1], 1);

            BIT right_bit = new BIT(n);
            int right_elements_count = 0;
            for (int l_idx = j + 1; l_idx < n; l_idx++) {
                right_bit.update(nums[l_idx], 1);
                right_elements_count++;
            }

            for (int k = j + 1; k < n - 1; k++) {
                right_bit.update(nums[k], -1);
                right_elements_count--;

                if (nums[k] < nums[j]) {
                    long count_i = left_bit.query(nums[k] - 1);
                    long count_l = right_elements_count - right_bit.query(nums[j]);

                    totalQuadruplets += count_i * count_l;
                }
            }
        }
        return totalQuadruplets;
    }
}

class BIT {
    private int[] tree;
    private int size;

    public BIT(int n) {
        this.size = n + 1;
        this.tree = new int[this.size];
    }

    public void update(int i, int delta) {
        while (i < size) {
            tree[i] += delta;
            i += i & (-i);
        }
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & (-i);
        }
        return sum;
    }
}