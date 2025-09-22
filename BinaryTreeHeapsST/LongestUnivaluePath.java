/*
 * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.

The length of the path between two nodes is represented by the number of edges between them.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/longest-univalue-path/description/?envType=problem-list-v2&envId=binary-tree 
 */

public class LongestUnivaluePath {
    int val;
    LongestUnivaluePath left;
    LongestUnivaluePath right;

    LongestUnivaluePath() {
    }

    LongestUnivaluePath(int val) {
        this.val = val;
    }

    LongestUnivaluePath(int val, LongestUnivaluePath left, LongestUnivaluePath right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    private int maxLength = 0;

    public int longestUnivaluePath(LongestUnivaluePath root) {
        if (root == null)
            return 0;
        dfs(root);
        return maxLength;
    }

    private int dfs(LongestUnivaluePath node) {
        if (node == null)
            return 0;

        int leftSubtree = dfs(node.left);
        int rightSubtree = dfs(node.right);

        int left = 0; // calc length left arm
        int right = 0; // for right arm

        if (node.left != null && node.left.val == node.val) {
            left = 1 + leftSubtree; // extending the path if exist
        }

        if (node.right != null && node.right.val == node.val) {
            right = 1 + rightSubtree;
        }

        maxLength = Math.max(maxLength, left + right);

        return Math.max(left, right);
    }
}
