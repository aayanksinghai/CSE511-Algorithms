/*
 * 
 * Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * 
 * Link: https://leetcode.com/problems/diameter-of-binary-tree/description/?envType=problem-list-v2&envId=binary-tree
 * 
 */

//Definition for a binary tree node.
public class DiameterofBinaryTree {
    int val;
    DiameterofBinaryTree left;
    DiameterofBinaryTree right;

    DiameterofBinaryTree() {
    }

    DiameterofBinaryTree(int val) {
        this.val = val;
    }

    DiameterofBinaryTree(int val, DiameterofBinaryTree left, DiameterofBinaryTree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int diameterOfBinaryTree(DiameterofBinaryTree root) {
        int[] diameter = new int[1];
        diameter[0] = 0;

        heightOfBT(root, diameter);
        return diameter[0];
    }

    private int heightOfBT(DiameterofBinaryTree node, int[] diameter) {
        if (node == null)
            return 0;

        int[] lh = new int[1];
        int[] rh = new int[1];
        lh[0] = heightOfBT(node.left, diameter);
        rh[0] = heightOfBT(node.right, diameter);

        diameter[0] = Math.max(diameter[0], lh[0] + rh[0]);
        return 1 + Math.max(lh[0], rh[0]);
    }
}