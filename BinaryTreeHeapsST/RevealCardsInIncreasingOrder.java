/*
 * You are given an integer array deck. There is a deck of cards where every card has a unique integer. The integer on the ith card is deck[i].

You can order the deck in any order you want. Initially, all the cards start face down (unrevealed) in one deck.
Return an ordering of the deck that would reveal the cards in increasing order.

Author: AAYANK SINGHAI (MT2025001)
Link: https://leetcode.com/problems/reveal-cards-in-increasing-order/description/
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RevealCardsInIncreasingOrder {
    public int[] deckRevealedIncreasing(int[] deck) {
        int[] ans = new int[deck.length];
        Arrays.sort(deck);

        Queue<Integer> qu = new LinkedList<>();

        for (int i = 0; i < deck.length; i++) {
            qu.add(i);
        }

        for (int j = 0; j < deck.length; j++) {
            int top = qu.poll();
            ans[top] = deck[j];
            qu.add(qu.poll());
        }
        return ans;
    }
}
