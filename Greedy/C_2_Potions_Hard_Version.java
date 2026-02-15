// AAYANK SINGHAI (MT2025001)

import java.util.PriorityQueue;
import java.util.Scanner;

public class C_2_Potions_Hard_Version {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }

        // A min-priority queue will store the negative potions we've drunk
        PriorityQueue<Long> pq = new PriorityQueue<>();

        long currHealth = 0;
        int potsDrunk = 0;

        for (int i = 0; i < n; i++) {
            long potion = a[i];

            if (potion >= 0) {
                // Always drink positive potions
                currHealth += potion;
                potsDrunk++;
            } else {
                // Tentatively drink negative potions
                currHealth += potion;
                potsDrunk++;
                pq.add(potion);
            }

            if (currHealth < 0) {
                if (!pq.isEmpty()) {
                    long mostNeg = pq.poll();

                    currHealth -= mostNeg;
                    potsDrunk--;
                }
            }
        }

        System.out.println(potsDrunk);
        sc.close();
    }
}