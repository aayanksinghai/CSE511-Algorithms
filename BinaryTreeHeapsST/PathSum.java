/*
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
A leaf is a node with no children.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * 
 * Link: https://leetcode.com/problems/path-sum/description/?envType=problem-list-v2&envId=binary-tree
 */

public class PathSum {
    int val;
    PathSum left;
    PathSum right;

    PathSum() {
    }

    PathSum(int val) {
        this.val = val;
    }

    PathSum(int val, PathSum left, PathSum right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean hasPathSum(PathSum root, int targetSum) {
        if (root == null)
            return false;

        int remainingSum = targetSum - root.val;

        if (root.left == null && root.right == null) {
            return remainingSum == 0;
        }

        boolean left = hasPathSum(root.left, remainingSum);
        boolean right = hasPathSum(root.right, remainingSum);

        return left || right; // since one valid path suffice hence ||
    }
}
