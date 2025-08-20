/*
 * Given the head of a linked list and a value x, partition it such that all nodes less than x 
 * come before nodes greater than or equal to x.
    You should preserve the original relative order of the nodes in each of the two partitions.

    Link: https://leetcode.com/problems/partition-list/description/?envType=problem-list-v2&envId=linked-list
 */

package LinkedList;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        // Dummy head for the list of nodes with values less than x.
        ListNode lessHead = new ListNode(0);
        // Tail pointer for the "less than" list.
        ListNode lessTail = lessHead;

        // Dummy head for the list of nodes with values greater than or equal to x.
        ListNode greaterHead = new ListNode(0);
        // Tail pointer for the "greater than or equal to" list.
        ListNode greaterTail = greaterHead;

        ListNode current = head;

        // Iterate through the original list once.
        while (current != null) {
            if (current.val < x) {
                // Add the current node to the "less than" list.
                lessTail.next = current;
                lessTail = lessTail.next;
            } else {
                // Add the current node to the "greater than or equal to" list.
                greaterTail.next = current;
                greaterTail = greaterTail.next;
            }
            current = current.next;
        }

        // Terminate the "greater than" list to avoid cycles.
        greaterTail.next = null;

        // Connect the "less than" list with the "greater than" list.
        lessTail.next = greaterHead.next;

        // The head of the final list is the start of the "less than" list.
        return lessHead.next;
    }
    
    
}
