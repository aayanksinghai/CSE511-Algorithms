// Link: https://leetcode.com/problems/asteroid-collision/?envType=problem-list-v2&envId=v5rancit

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AsteroidCollision {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt();
            }

            List<Integer> st = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (arr[i] > 0) {
                    st.add(arr[i]);
                }

                else {
                    while (!st.isEmpty() && st.get(st.size() - 1) > 0
                            && st.get(st.size() - 1) < Math.abs(arr[i])) {
                        st.remove(st.size() - 1);
                    }

                    if (!st.isEmpty() && st.get(st.size() - 1) == Math.abs(arr[i])) {
                        st.remove(st.size() - 1);
                    } else if (st.isEmpty() || st.get(st.size() - 1) < 0) {
                        st.add(arr[i]);
                    }
                }
            }

            int[] result = new int[st.size()];
            for (int i = 0; i < st.size(); i++) {
                result[i] = st.get(i);
            }

            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }

        }
    }
}