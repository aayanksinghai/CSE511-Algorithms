// AAYANK SINGHAI MT2025001
class Solution {
    public int minimumTime(String s) {
        int n = s.length();
        int min = n;
        int left = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                left = Math.min(left + 2, i + 1);
            }

            int right = n - (i + 1);
            min = Math.min(min, left + right);
        }

        return min;

    }
}