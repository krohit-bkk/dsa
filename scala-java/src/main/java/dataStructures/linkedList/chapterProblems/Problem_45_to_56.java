package dataStructures.linkedList.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

public class Problem_45_to_56 {

    public static void main(String[] args) throws Exception {
        // Problem 45: Find modular node (last node when starting from beginning)
        // Given a singly linked list, write a function to find the  last element from the beginning whose
        // n%k == 0, where n is the number of elements in the list and k is an integer constant
        System.out.println("Problem 45: Find modular node (last node when starting from beginning)");
        ListNode head = ListNodeUtils.getListOfSize(10);
        int k = 3;
        System.out.println("Input LinkedList...");
        ListNodeUtils.printList(head);
        System.out.println("K: " + k);
        problem45_printModularNodeBeginning(head, k);

        // Problem 46: Finding modular node from end
        // Given a singly linked list, write a function to find the first from the end whose n%k ==0,
        // where n is the number of elements in the list and k is an integer constant
        System.out.println("\nProblem 46: Find modular node from the end");
        System.out.println("Input LinkedList...");
        ListNodeUtils.printList(head);
        System.out.println("K: " + k);
        problem46_printModularNodeEnding(head, k);

        // Problem 47: Finding fractional node
        // Given a singly linked list, write a function to find n/k-th node,
        // where n is the number of elements in the list and k is an integer constant
        System.out.println("\nProblem 47: Find fractional node");
        System.out.println("Input LinkedList...");
        ListNodeUtils.printList(head);
        System.out.println("K: " + k);
        problem47_printFractionalNode(head, k);

        // Problem 48: Finding sqrt(n)-th node
        // Given a singly linked list, write a function to find sqrt(n)-th node,
        // where n is the number of elements in the list
        System.out.println("\nProblem 48: Find fractional node");
        System.out.println("Input LinkedList...");
        ListNodeUtils.printList(head);
        problem48_printSqrtNNode(head);

        // Problem 49: Merge two sorted LinkedLists into a new sorted List
        int[] arr1 = new int[]{1,3,5,7,9};
        int[] arr2 = new int[]{2,4,6,8};
        System.out.println("\nProblem 49: Merge two sorted LinkedLists into a single sorted LinkedList...");
        ListNode ll1 = ListNodeUtils.getArrayAsLinkedList(arr1);
        ListNode ll2 = ListNodeUtils.getArrayAsLinkedList(arr2);
        ListNodeUtils.printList(ll1);
        ListNodeUtils.printList(ll2);
        System.out.println("Resultant LinkedList after merging the above two LinkedLists...");
        ListNodeUtils.printList(mergeSortedList(ll1, ll2));

        // Problem 50: Median of an infinite series of integers
        // TBD in Priority Queues and Heaps chapter

        // Problem 51: Given a linked list, how do you modify it such that all the even numbers appear
        // before all the odd numbers in the modified linked list?
        // Same as Problem 43

        // Problem 52: Given two linked lists, each list node with one integer digit, add these two
        // linked lists. The result should be stored in the third linked list.
        // Also note that the head node contains the most significant digit of the number.

        // Lets reuse ll1 and ll2 only for summing
        // ll1 represents 1357, ll2 represents 2468
        System.out.println("\nProblem 52: Adding two numbers represented as LinkedLists...");
        ll1 = ListNodeUtils.getArrayAsLinkedList(arr1);
        ll2 = ListNodeUtils.getArrayAsLinkedList(arr2);
        ListNodeUtils.printList(ll1);
        ListNodeUtils.printList(ll2);
        System.out.println("Sum of above two LinkedLists...");
        ListNode ll3 = sumOfListNumbers(ll1, ll2);
        ListNodeUtils.printList(ll3);

    }

    // Problem 53: Add two numbers represented in form of LinkedLists
    // Note that, the head points to most significant digit
    // Time  complexity: O[N]
    // Space complexity: O[1]

    // Helper method
    static ListNode reverseList(ListNode head){
        ListNode curr = head;
        ListNode prev = null;
        ListNode temp = null;
        while(curr != null){
            temp = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = temp;
        }
        return prev;
    }
    private static ListNode sumOfListNumbers(ListNode ll1, ListNode ll2) {
        // Step 1: Reverse both LinkedLists
        ll1 = reverseList(ll1);
        ll2 = reverseList(ll2);

        // Step 2: Add corresponding integers starting position 0 and towards tail
        ListNode head = null;
        ListNode curr = null;
        int carry = 0;
        ListNode p1 = ll1;
        ListNode p2 = ll2;
        while(p1 != null && p2 != null){
            int sum = p1.getData() + p2.getData() + carry;
            int value = sum % 10;
            carry = sum / 10;
            ListNode node = new ListNode(value);
            if(head == null){
                head = node;
                curr = node;
            }
            else {
                curr.setNext(node);
                curr = curr.getNext();
            }
            p1 = p1.getNext();
            p2 = p2.getNext();
        }

        // Check if one of the LinkedList is not fully processed
        if(p1 == null){
            int sum = p2.getData() + carry;
            int value = sum % 10;
            carry = sum / 10;
            ListNode node = new ListNode(value);
            curr.setNext(node);
            curr = curr.getNext();
            p2 = p2.getNext();
        }
        else{
            int sum = p1.getData() + carry;
            int value = sum % 10;
            carry = sum / 10;
            ListNode node = new ListNode(value);
            curr.setNext(node);
            curr = curr.getNext();
            p1 = p1.getNext();
        }

        // Check if carry remains non-zero
        if(carry != 0)
            curr.setNext(new ListNode(1));

        // Step 3: Reverse the resultant LinkedList to make head point the most significant digit
        head = reverseList(head);
        // ListNodeUtils.printList(head);
        return head;
    }

    // Problem 49: Merge two sorted LinkedLists and return a new sorted LinkedList
    // Time  complexity: O[N] or O[min(m,n)], where m & n are length of LL1 & LL2 resp
    // Space complexity: O[1]
    private static ListNode mergeSortedList(ListNode ll1, ListNode ll2) {
        // Base cases
        if(ll1 == null)
            return ll2;
        if(ll2 == null)
            return ll1;

        // Head of the resultant LinkedList
        ListNode temp = new ListNode(0);

        ListNode curr = temp;
        ListNode c1 = ll1;
        ListNode c2 = ll2;
        while(c1 != null && c2 != null){
            if(c1.getData() < c2.getData()) {
                curr.setNext(c1);
                c1 = c1.getNext();
            }
            else{
                curr.setNext(c2);
                c2 = c2.getNext();
            }
            curr = curr.getNext();
        }

        if(c1 == null)
            curr.setNext(c2);

        if(c2 == null)
            curr.setNext(c1);

        // ListNodeUtils.printList(temp.getNext());
        return temp.getNext();
    }

    // Problem 48: Find sqrt(n)-th node
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static void problem48_printSqrtNNode(ListNode head) {
        ListNode curr = head;
        ListNode sqrtNode = null;
        int i = 1;
        int j = 1;

        while(curr != null){
            if(i == j*j) {
                if(sqrtNode == null)
                    sqrtNode = curr;
                else
                    sqrtNode = sqrtNode.getNext();
                j += 1;
            }
            i += 1;
            curr = curr.getNext();
        }
        System.out.println("Sqrt(n)-th element: " + sqrtNode.getData() + ", where n = " + (i-1));
    }

    // Problem 47: Find fractional node
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static void problem47_printFractionalNode(ListNode head, int k) {
        // Edge case
        if(k < 1){
            System.out.println("ERROR: Invalid input for K [" + k + "]! Exiting...");
            return;
        }

        ListNode curr = head;
        ListNode fracNode = null;
        int counter = 1;
        while(curr != null){
            if(counter % k == 0)
                fracNode = curr;
            curr = curr.getNext();
            counter += 1;
        }
        System.out.println("Fractional node: " + fracNode.getData());
    }

    // Problem 46: Find modular node from the end
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static void problem46_printModularNodeEnding(ListNode head, int k) {
        // This is same as finding the k-th node from the end
        // Approach: Initialize two pointers p and q - both pointing to head
        // Keep moving p forward till distance between p and q is k
        // Once distance between p and q is k, keep moving both till p reaches the end
        // Print q's data

        // Edge case
        if(k < 1){
            System.out.println("ERROR: Invalid input for K [" + k + "]! Exiting...");
            return;
        }

        ListNode p = head;
        ListNode q = head;
        int counter = 0;
        while(p != null){
            if(counter >= k)
                q = q.getNext();
            p = p.getNext();
            counter += 1;
        }
        System.out.println("Modular node from end: " + q.getData());
    }

    // Problem 45: Find modular node (last node when starting from beginning)
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static void problem45_printModularNodeBeginning(ListNode head, int k) {
        // Edge case
        if(k < 1){
            System.out.println("ERROR: Invalid input for K [" + k + "]! Exiting...");
            return;
        }
        // Step 1: Start iterating on LinkedList with counter starting from 1
        ListNode curr = head;
        int counter = 1;
        ListNode lastModNode = null;
        while(curr != null){
            // Step 2: While iterating, if counter%k == 0, update lastModNode
            if(counter % k == 0){
                lastModNode = curr;
            }
            counter += 1;
            curr = curr.getNext();
        }
        // Step 3: Print the data of lastModNode
        System.out.println("Last modular node (starting from beginning): " + lastModNode.getData());
    }
}
