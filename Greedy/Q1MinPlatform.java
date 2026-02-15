
// AAYANK SINGHAI (MT2025001)
import java.util.*;

class Solution {
    public int minPlatform(int arr[], int dep[]) {
        int n = arr.length;

        Arrays.sort(arr);
        Arrays.sort(dep);

        int platforms = 0;
        int max = 0;
        int i = 0;
        int j = 0;

        while (i < n) {
            if (arr[i] <= dep[j]) {
                platforms++;
                i++;
            } else {
                platforms--;
                j++;
            }
            max = Math.max(max, platforms);
        }

        return max;
    }
}
