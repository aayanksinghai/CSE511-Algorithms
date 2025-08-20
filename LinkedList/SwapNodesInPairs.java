/*
 * Given a linked list, swap every two adjacent nodes and return its head. 
 * You must solve the problem without modifying the values in the list's nodes
 *  (i.e., only nodes themselves may be changed.)
 * 
 * Link: https://leetcode.com/problems/swap-nodes-in-pairs/description/?envType=problem-list-v2&envId=linked-list
 */


package LinkedList;

public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
                // If the list is empty or has only one node, no swap is needed.
        if (head == null || head.next == null) {
            return head;
        }

        // A dummy node simplifies the logic, especially for the head of the list.
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // `prev` will always point to the node just before the pair to be swapped.
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            // 1. Identify the nodes to be swapped.
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // 2. Perform the swap by re-wiring the pointers.
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // 3. Move `prev` to the next pair.
            // `first` is now the second node in the swapped pair, so we set prev to it.
            prev = first;
        }

        // The dummy node's `next` points to the new head of the list.
        return dummy.next;
    }
}