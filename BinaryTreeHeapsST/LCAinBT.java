/*
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=problem-list-v2&envId=binary-tree
 */

public class LCAinBT {
    int val;
    LCAinBT left;
    LCAinBT right;

    LCAinBT() {
    }

    LCAinBT(int val) {
        this.val = val;
    }

    LCAinBT(int val, LCAinBT left, LCAinBT right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public LCAinBT lowestCommonAncestor(LCAinBT root, LCAinBT p, LCAinBT q) {
        // TC: O(N) SC: O(N)
        if (root == null || root == p || root == q)
            return root;

        // Searching left and right subtree
        LCAinBT left = lowestCommonAncestor(root.left, p, q);
        LCAinBT right = lowestCommonAncestor(root.right, p, q);

        if (left == null)
            return right;
        else if (right == null)
            return left;
        else
            return root; // both left and right are not null, return result
    }
}
