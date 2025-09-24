/*
 * Monk And Champion League
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://www.hackerearth.com/practice/data-structures/trees/heapspriority-queues/practice-problems/algorithm/monk-and-champions-league/?purpose=login&source=problem-page&update=google
 */

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MonkAndChampionsLeague {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        while (M > 0) {
            pq.add(sc.nextInt());
            M--;
        }
        sc.close();
        int sum = 0;
        while (N > 0) {
            int val = pq.poll();
            sum += val;
            pq.add(--val);
            N--;
        }

        System.out.println(sum);
    }
}
