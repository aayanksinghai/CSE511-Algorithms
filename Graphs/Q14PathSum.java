class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
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