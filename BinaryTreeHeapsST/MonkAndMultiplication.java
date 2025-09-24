/*
 * Monk and Multiplication
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://www.hackerearth.com/practice/data-structures/trees/heapspriority-queues/practice-problems/algorithm/monk-and-multiplication/
 */

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MonkAndMultiplication {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        sc.close();

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            pq.add(A[i]);
            if (pq.size() < 3) {
                System.out.println(-1);
                continue;
            }

            int top = pq.poll();
            int second = pq.poll();
            int third = pq.poll();
            long product = (long) top * second * third;
            System.out.println(product);
            pq.add(top);
            pq.add(second);
            pq.add(third);
        }

    }

}
