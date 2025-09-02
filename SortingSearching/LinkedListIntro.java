import java.util.Scanner;

class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListIntro {

    // --- Helper Function to Print the List (same as before) ---
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter");

        String line = sc.nextLine();
        String[] numbers = line.trim().split("\\s+");

        Node dummyNode = new Node(-1);
        Node tail = dummyNode;
        for (String num : numbers) {
            if (!num.isEmpty()) {
                int value = Integer.parseInt(num);
                tail.next = new Node(value);
                tail = tail.next;
            }

        }

        Node head = dummyNode.next;
        System.out.println("LL Succ");
        printList(head);
        sc.close();
    }
}
