/*
 * Given the head of a singly linked list and two integers left and right where left <= right, 
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 * 
 * Link: https://leetcode.com/problems/reverse-linked-list-ii/description/?envType=problem-list-v2&envId=linked-list
 */



package LinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) 
    { 
        this.val = val; 
    }
    ListNode(int val, ListNode next) {
         this.val = val; this.next = next; 
    }
}


public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // Handle edge cases: no list, no reversal needed, or invalid input.
        if (head == null || left == right) {
            return head;
        }

        // Use a dummy node to simplify handling the case where left = 1.
        ListNode dummy = new ListNode(0, head);

        // 1. Move `leftPrev` to the node just before the reversal section.
        ListNode leftPrev = dummy;
        for (int i = 0; i < left - 1; i++) {
            leftPrev = leftPrev.next;
        }

        // `current` will be the tail of the reversed sublist after the loop.
        ListNode current = leftPrev.next;

        // 2. Perform the in-place reversal.
        // For each iteration, move the node after `current` to the front of the sublist.
        for (int i = 0; i < right - left; i++) {
            ListNode nodeToMove = current.next;
            
            // Detach nodeToMove
            current.next = nodeToMove.next;

            // Attach nodeToMove to the front of the reversed section
            nodeToMove.next = leftPrev.next;
            leftPrev.next = nodeToMove;
        }

        // The dummy node's next pointer points to the new head of the list.
        return dummy.next;
    }
}