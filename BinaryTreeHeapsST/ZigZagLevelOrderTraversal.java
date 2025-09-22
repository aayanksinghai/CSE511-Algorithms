/*
 * Given the root of a binary tree, return the zigzag level order traversal of its
 *  nodes' values. (i.e., from left to right, then right to left for the next level 
 * and alternate between)
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * 
 * Link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/?envType=problem-list-v2&envId=binary-tree
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagLevelOrderTraversal {

    int val;
    ZigZagLevelOrderTraversal left;
    ZigZagLevelOrderTraversal right;

    ZigZagLevelOrderTraversal() {
    }

    ZigZagLevelOrderTraversal(int val) {
        this.val = val;
    }

    ZigZagLevelOrderTraversal(int val, ZigZagLevelOrderTraversal left, ZigZagLevelOrderTraversal right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}

class Solution {
    public List<List<Integer>> zigzagLevelOrder(ZigZagLevelOrderTraversal root) {
        // TC: O(N) SC: O(N)
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Queue<ZigZagLevelOrderTraversal> queue = new LinkedList<>();
        queue.offer(root);

        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> list = new ArrayList<>(Collections.nCopies(size, 0));
            for (int i = 0; i < size; i++) {
                ZigZagLevelOrderTraversal node = queue.poll();
                int index = leftToRight ? i : size - 1 - i;

                list.set(index, node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            leftToRight = !leftToRight;
            ans.add(list);
        }
        return ans;
    }
}
