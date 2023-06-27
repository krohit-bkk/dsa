package dataStructures.linkedList.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

public class Problem_29_to_44 {

    public static void main(String[] args) throws Exception {
        ListNode evenList = ListNodeUtils.getListOfSize(10);
        ListNode oddList  = ListNodeUtils.getListOfSize(11);

        ListNodeUtils.printList(evenList);

        // Problem 29: Display a LinkedList from the end
        System.out.println("\nProblem 29: Displaying LinkedLists in reverse.");
        problem_29_displayFromEnd(evenList);

        // Problem 30: Check if the length od LinkedList is even or odd
        System.out.println();
        problem_30_isLengthEvenOdd(evenList);
        problem_30_isLengthEvenOdd(oddList);

        // Problem 32: Given two sorted LinkedLists, merge them in sorted order in third LinkedList
        // Approach 1: Iterative method
        System.out.println();
        System.out.println("\nProblem 32: Merging two sorted LinkedLists");
        int[] arr1 = new int[]{1,3,5};
        int[] arr2 = new int[]{2,4,6,8};
        ListNode ll1 = ListNodeUtils.getArrayAsLinkedList(arr1);
        ListNodeUtils.printList(ll1);
        ListNode ll2 = ListNodeUtils.getArrayAsLinkedList(arr2);
        ListNodeUtils.printList(ll2);
        ListNode sortedList1 = mergeSortedIterative(ll1, ll2);
        System.out.print("Merged Iterative : ");
        ListNodeUtils.printList(sortedList1);

        // Problem 33: Given two sorted LinkedLists, merge them in sorted order in third LinkedList
        // Approach 1: Recursive method
        ListNode sortedList2 = mergeSortedRecursive(ll1, ll2);
        System.out.print("Merged Recursive : ");
        ListNodeUtils.printList(sortedList2);

        // Problem 34: Reverse LinkedList in pairs
        // Approach 1: Recursive method
        System.out.println("\nProblem 34: Pair reverse the LinkedList - Recursively");
        ll1 = ListNodeUtils.getListOfSize(4);
        ListNodeUtils.printList(ll1);
        ListNode pairReversed1 = pairReversedRecursive(ll1);
        ListNodeUtils.printList(pairReversed1);

        // Problem 34: Reverse LinkedList in pairs
        // Approach 2: Iterative method
        System.out.println("\nProblem 34: Pair reverse the LinkedList - Iteratively");
        ll1 = ListNodeUtils.getListOfSize(6);
        ListNodeUtils.printList(ll1);
        ListNode pairReversed2 = pairReversedIterative(ll1);
        ListNodeUtils.printList(pairReversed2);

        // Problem 40: Exchange adjacent elements of the LinkedList
        System.out.println("\nProblem 40: Exchange adjacent elements of the LinkedList");
        ll1 = ListNodeUtils.getListOfSize(5);
        ListNodeUtils.printList(ll1);
        ListNode adjacentExchanged = adjacentExchanged(ll1);
        ListNodeUtils.printList(adjacentExchanged);

        // Problem 41: Reverse a LinkedList in block of K (K > 0)
        System.out.println("\nProblem 41: Reverse a LinkedList in block of K");
        ll1 = ListNodeUtils.getListOfSize(8);
        ListNodeUtils.printList(ll1);
        ListNode kBlockReverse = kBlockReverseRecursive(ll1, 10);
        ListNodeUtils.printList(kBlockReverse);
    }

    // Problem 29: Display LinkedList from the end
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static void problem_29_displayFromEnd(ListNode head) {
        // Sanity check
        if(head == null){
            System.out.println("ERROR: Nothing to display. Head is NULL");
            return;
        }
        // Edge case
        if(head.getNext() == null){
            System.out.println("\nNode Data: " + head.getData());
        }
        // Recursion logic
        if(head.getNext() != null){
            ListNode tail = head.getNext();
            problem_29_displayFromEnd(tail);
            System.out.println("Node Data: " + head.getData());
        }
    }

    // Problem 30: Check if the length od LinkedList is even or odd
    // Using two pointer approach (fast/slow pointers)
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static void problem_30_isLengthEvenOdd(ListNode head) {
        if(head == null){
            System.out.println("ERROR: Empty list!");
            return;
        }

        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.getNext() != null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        if(fast != null)
            System.out.println("Problem 30: Given LinkedList has odd length.");
        else
            System.out.println("Problem 30: Given LinkedList has even length.");
    }

    // Problem 32: Given two sorted LinkedLists, merge them in sorted order in third LinkedList
    // Approach 1: Iterative method
    // Time  complexity: O[N]
    // Space complexity: O[N] - because we created a new LinkedList
    private static ListNode mergeSortedIterative(ListNode ll1, ListNode ll2) {
        // Base Case 1: Both lists are empty
        if(ll1 == null && ll2 == null)
            return null;

        // Base Case 2: One of the lists is empty
        if(ll1 == null && ll2 != null)
            return ll2;
        if(ll1 != null && ll2 == null)
            return ll1;

        // Regular scenario
        ListNode head = null; // head of new LinkedList
        ListNode curr = null;
        ListNode curr1 = ll1;
        ListNode curr2 = ll2;

        // Compare the two sorted lists till one of them is exhausted
        while(curr1 != null && curr2 != null){
            if(curr1.getData() < curr2.getData()){
                ListNode node = new ListNode(curr1.getData());
                if(head == null){
                    head = node;
                    curr = head;
                }
                else{
                    curr.setNext(node);
                    curr = node;
                }
                curr1 = curr1.getNext();
            }
            else{
                ListNode node = new ListNode(curr2.getData());
                if(head == null){
                    head = node;
                    curr = head;
                }
                else{
                    curr.setNext(node);
                    curr = node;
                }
                curr2 = curr2.getNext();
            }
        }

        // One of the lists must be exhausted by now
        if(curr1 == null){
            while(curr2 != null){
                ListNode node = new ListNode(curr2.getData());
                curr.setNext(node);
                curr = node;
                curr2 = curr2.getNext();
            }
        }
        if(curr2 == null){
            while(curr1 != null){
                ListNode node = new ListNode(curr1.getData());
                curr.setNext(node);
                curr = node;
                curr1 = curr1.getNext();
            }
        }
        return head;
    }

    // Problem 33: Given two sorted LinkedLists, merge them in sorted order without creating third LinkedList
    // Approach 1: Recursive method
    // Time  complexity: O[N]
    // Space complexity: O[1] - because we are not creating a new LinkedList
    private static ListNode mergeSortedRecursive(ListNode ll1, ListNode ll2) {
        // Edge Cases
        if(ll1 == null)
            return ll2;
        if(ll2 == null)
            return ll1;

        ListNode newHead = null;
        if(ll1.getData() < ll2.getData()){
            newHead = ll1;
            newHead.setNext(mergeSortedRecursive(ll1.getNext(), ll2));
        }
        else{
            newHead = ll2;
            newHead.setNext(mergeSortedRecursive(ll1, ll2.getNext()));
        }
        return newHead;
    }

    // Problem 34: Reverse LinkedList in pairs
    // Approach 1: Recursive method
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static ListNode pairReversedRecursive(ListNode head) {
        // Base case checks
        if(head == null || head.getNext() == null)
            return head;

        ListNode two = head.getNext();
        ListNode rest = two.getNext();

        // Swapping values between one and two
        int temp = head.getData();
        head.setData(two.getData());
        two.setData(temp);

        // Two pointing to the rest of the LinkedList
        two.setNext(pairReversedRecursive(rest));
        return head;
    }

    // Problem 34: Reverse LinkedList in pairs
    // Approach 2: Iterative method
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static ListNode pairReversedIterative(ListNode head) {
        // Base case checks
        if(head == null || head.getNext() == null)
            return head;

        ListNode curr = head;
        while(curr != null && curr.getNext() != null){
            ListNode next = curr.getNext();
            int temp = curr.getData();
            curr.setData(next.getData());
            next.setData(temp);
            curr = curr.getNext().getNext();
        }
        return head;
    }

    // Problem 40: Exchange adjacent elements of the LinkedList
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static ListNode adjacentExchanged(ListNode head) {
        // Edge cases
        if(head == null || head.getNext() == null){
            return head;
        }

        ListNode one = head;
        ListNode two = head.getNext();
        ListNode rest = two.getNext();
        two.setNext(one);
        one.setNext(adjacentExchanged(rest));
        head = two;
        return head;
    }

    // Problem 41: Reverse a LinkedList in block of K (K > 0)
    // Approach 1: Recursive algorithm
    private static ListNode kBlockReverseRecursive(ListNode head, int k) {
        // Edge case
        if(head == null || head.getNext() == null)
            return head;

        // Reverse 1st k nodes
        int counter = 0;
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        while(curr != null && counter < k){
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
            counter += 1;
        }
        if(next != null)
            head.setNext(kBlockReverseRecursive(next, k));
        // Return head node
        return prev;
    }

}
