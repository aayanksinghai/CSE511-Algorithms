/*
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).

Author: AAYANK SINGHAI (MT2025001)
Link: https://leetcode.com/problems/count-number-of-teams/description/?envType=problem-list-v2&envId=binary-indexed-tree
 * 
 */

public class CountNumberOfTeams {

    public int numTeams(int[] rating) {
        int maxRating = 0;
        for (int r : rating) {
            maxRating = Math.max(maxRating, r);
        }

        BIT leftBit = new BIT(maxRating + 1);
        BIT rightBit = new BIT(maxRating + 1);

        for (int r : rating) {
            rightBit.update(r, 1);
        }

        int teamCount = 0;

        for (int currentRating : rating) {
            rightBit.update(currentRating, -1);

            int leftSmaller = leftBit.query(currentRating - 1);
            int rightLarger = rightBit.query(maxRating) - rightBit.query(currentRating);
            teamCount += leftSmaller * rightLarger;

            int leftLarger = leftBit.query(maxRating) - leftBit.query(currentRating);
            int rightSmaller = rightBit.query(currentRating - 1);
            teamCount += leftLarger * rightSmaller;

            leftBit.update(currentRating, 1);
        }

        return teamCount;
    }
}

class BIT {
    private int[] tree;
    private int size;

    public BIT(int size) {
        this.size = size;
        this.tree = new int[size];
    }

    public void update(int i, int delta) {
        while (i < size) {
            tree[i] += delta;
            i += i & (-i);
        }
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & (-i);
        }
        return sum;
    }
}