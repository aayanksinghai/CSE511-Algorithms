/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    Map<TreeNode, Integer[]> dp;

    public int rob(TreeNode root) {
        dp = new HashMap();
        return sol(root, false);
    }

    private int sol(TreeNode root, boolean probbed) {
        if (root == null)
            return 0;

        dp.putIfAbsent(root, new Integer[2]);
        if (dp.get(root)[probbed ? 1 : 0] != null)
            return dp.get(root)[probbed ? 1 : 0];

        int one = 0;
        if (!probbed) {
            one = root.val + sol(root.left, true) + sol(root.right, true);
        }

        int two = sol(root.left, false) + sol(root.right, false);
        return dp.get(root)[probbed ? 1 : 0] = Math.max(one, two);
    }
}