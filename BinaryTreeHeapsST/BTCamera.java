/*
 * You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.

Return the minimum number of cameras needed to monitor all nodes of the tree.
 * 
 * AUTHOR: Aayank Singhai (MT2025001)
 * 
 * Link: https://leetcode.com/problems/binary-tree-cameras/description/?envType=problem-list-v2&envId=vapxduwj
 * 
 */

import java.util.Set;

public class BTCamera {

    int val;
    BTCamera left;
    BTCamera right;

    BTCamera() {
    }

    BTCamera(int val) {
        this.val = val;
    }

    BTCamera(int val, BTCamera left, BTCamera right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    int camera;
    Set<BTCamera> coveredNode;

    public int minCameraCover(BTCamera root) {
        /*
         * if(root == null) return 0;
         * camera = 0;
         * coveredNode = new HashSet<>();
         * coveredNode.add(null); //wont be adding the leaf nodes and starting from one
         * level above leaf
         * dfs(root, null);
         * return camera;
         */

        // Optimal Without DS
        // 0 -> camera+1, (1 or 2) return camera itself;
        return dfsTraversal(root) == 0 ? camera + 1 : camera;
    }

    public void dfs(BTCamera node, BTCamera parent) {
        // TC : O(N) SC: O(Nt)
        if (node != null) {
            dfs(node.left, node);
            dfs(node.right, node);

            // Cases
            // Check if camera is needed at node
            // Parent null && node uncovered OR left or right child not covered

            if (parent == null && !coveredNode.contains(node) || !coveredNode.contains(node.left)
                    || !coveredNode.contains(node.right)) {
                camera++;
                coveredNode.add(node);
                coveredNode.add(node.left);
                coveredNode.add(node.right);
                coveredNode.add(parent);
            }
        }
    }

    public int dfsTraversal(BTCamera node) {

        // Optimal TC: O(N) SC: O(H)
        // Value 2: has camera
        // Value 1; covered with the camera
        // Value 0: no camera is covered

        if (node == null)
            return 1;
        int left = dfsTraversal(node.left);
        int right = dfsTraversal(node.right);

        // Checking if a camera is needed or not
        if (left == 0 || right == 0) {
            camera++;
            return 2;
        } else if (left == 2 || right == 2)
            return 1;
        else
            return 0;
    }
}