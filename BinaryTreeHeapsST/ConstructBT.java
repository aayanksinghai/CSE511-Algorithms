/*
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a 
 * binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * 
 * Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/?envType=problem-list-v2&envId=binary-tree
 */

import java.util.HashMap;
import java.util.Map;

public class ConstructBT {

    int val;
    ConstructBT left;
    ConstructBT right;

    ConstructBT() {
    }

    ConstructBT(int val) {
        this.val = val;
    }

    ConstructBT(int val, ConstructBT left, ConstructBT right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public ConstructBT buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();

        // Populate Hashmap with (element,indices)
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        ConstructBT root = buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1, inMap);
        return root;
    }

    private ConstructBT buildTree(int[] preorder, int preStart, int preEnd,
            int[] inorder, int inStart,
            int inEnd, Map<Integer, Integer> inMap) {

        if (preStart > preEnd || inStart > inEnd)
            return null;

        ConstructBT root = new ConstructBT(preorder[preStart]);

        int inRoot = inMap.get(root.val); // current root
        int numsLeft = inRoot - inStart; // number of elements in left subtree

        // recursively left subtree construction
        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRoot - 1, inMap);

        // Same with right subtree
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRoot + 1, inEnd, inMap);

        return root;
    }

}
