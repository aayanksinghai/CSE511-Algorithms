// Link : https://leetcode.com/problems/max-chunks-to-make-sorted/?envType=problem-list-v2&envId=v5rancit

import java.util.Scanner;

public class MaxChunksToMakeSorted {

    public static int maxChunksToSorted(int[] arr) {
        int sum = 0;
        int count = 0;

        // Prefix Sum kind of ::: checking till ith index the sum of element and
        // difference with sum of index are getting balanced or not; if yes then they
        // are in order and becomes a chunk
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] - i;
            if (sum == 0)
                count++;
        }

        return count;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.println(maxChunksToSorted(arr));
        }
        sc.close();
    }
}