// AAYANK SINGHAI MT2025001
class Solution {
    public int numTeams(int[] r) {
        int n = r.length;
        if (n < 3) {
            return 0;
        }

        int[] less = new int[n];
        int[] greater = new int[n];

        int teams = 0;

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (r[i] < r[j]) {
                    less[j]++;
                } else if (r[i] > r[j]) {
                    greater[j]++;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < k; j++) {
                if (r[j] < r[k]) {
                    teams += less[j];
                } else if (r[j] > r[k]) {
                    teams += greater[j];
                }
            }
        }
        return teams;
    }
}