/*
 * Link: https://leetcode.com/problems/balanced-binary-tree/?envType=problem-list-v2&envId=binary-tree
 * Given a binary tree, determine if it is height-balanced.
 * Author: AAYANK SINGHAI (MT2025001)
 * 
 * Link: https://leetcode.com/problems/balanced-binary-tree/description/?envType=problem-list-v2&envId=binary-tree
 */

//Definition for a binary tree node.
public class BalancedBinaryTree {
    int val;
    BalancedBinaryTree left;
    BalancedBinaryTree right;

    BalancedBinaryTree() {
    }

    BalancedBinaryTree(int val) {
        this.val = val;
    }

    BalancedBinaryTree(int val, BalancedBinaryTree left, BalancedBinaryTree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isBalanced(BalancedBinaryTree root) {
        return depth(root) != -1;
    }

    private int depth(BalancedBinaryTree root) {
        if (root == null)
            return 0;

        int left = depth(root.left);
        if (left == -1)
            return -1;

        int right = depth(root.right);
        if (right == -1)
            return -1;

        if (Math.abs(left - right) > 1)
            return -1;

        return Math.max(left, right) + 1;
    }
}