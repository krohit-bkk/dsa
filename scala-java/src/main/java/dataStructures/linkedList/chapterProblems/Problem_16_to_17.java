package dataStructures.linkedList.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

public class Problem_16_to_17 {
    // Problem 16: Insert a node in a sorted LinkedList
    public static ListNode insertIntoSortedList(ListNode head, int item){
        // Input LinkedList is empty
        if(head == null){
            ListNode node = new ListNode(item);
            return node;
        }

        // New node - to be inserted
        ListNode node = new ListNode(item);

        // Case 1: Insertion at the beginning of LinkedList
        if(head.getData() > item){
            node.setNext(head);
            return node;

            /*
            // Some platform do not let reference to head get modified
            // In that case we do this:
            // Add node after the head and swap values
            node.setNext(head.getNext());
            head.setNext(node);
            // Swap values
            int temp = head.getData();
            head.setData(node.getData());
            node.setData(temp);

            return head;
            */
        }
        // Case 2: Insertion at the end of the LinkedList
        // Case 3: Insertion in the middle of the LinkedList
        ListNode curr = head;
        while(curr.getNext() != null && curr.getNext().getData() < item && curr != null)
            if(curr.getNext() != null)
                curr = curr.getNext();

        // Check if curr is last element of the LinkedList
        if(curr.getNext() != null)
            node.setNext(curr.getNext());
        curr.setNext(node);

        return head;
    }

    // Problem 17: Reverse a LinkedList iteratively
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static ListNode reverseLinkedListIterative(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while(curr != null){
            ListNode temp = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    // Problem 17: Reverse a LinkedList recursively
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static ListNode reverseLinkedListRecursive(ListNode head) {
        if(head == null || head.getNext() == null)
            return head;

        ListNode newHead = reverseLinkedListRecursive(head.getNext());
        ListNode headNext = head.getNext();
        headNext.setNext(head);
        head.setNext(null);
        return newHead;
    }

    public static void main(String[] args) {
        // Problem 16: Insert a node in a sorted LinkedList
        // Step-1: Create a sorted LinkedList, say of size 5
        ListNode head = new ListNode(1);
        ListNode temp = head;
        int counter = 1;
        while(counter < 5){
            counter += 1;
            if(counter != 3){
                ListNode node = new ListNode(counter);
                temp.setNext(node);
                temp = temp.getNext();
            }
        }
        // Verify
        ListNodeUtils.printList(head);

        // Step-2: Insert item into sorted LinkedList
        // Case 1: Insertion at the beginning of LinkedList
        head = insertIntoSortedList(head, 0);
        System.out.println("Item is smaller than the smallest element of LL\n" + ListNodeUtils.getListAsString(head) + "\n");

        // Case 2: Insertion at the end of LinkedList
        head = insertIntoSortedList(head, 6);
        System.out.println("Item is larger than the largest element of LL\n" + ListNodeUtils.getListAsString(head) + "\n");

        // Case 3: Insertion in the middle of the LinkedList
        head = insertIntoSortedList(head, 3);
        System.out.println("Item is in-between the the smallest & largest elements of LL\n" + ListNodeUtils.getListAsString(head) + "\n");


        // Problem 17: Reverse a LinkedList
        // Iterative approach
        head = reverseLinkedListIterative(head);
        System.out.println("Reversed LinkedList (Iterative): \n" + ListNodeUtils.getListAsString(head));
        // recursive approach
        head = reverseLinkedListRecursive(head);
        System.out.println("Reversed LinkedList (Recursive): \n" + ListNodeUtils.getListAsString(head));
    }
}
