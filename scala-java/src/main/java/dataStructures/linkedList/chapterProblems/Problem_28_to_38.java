package dataStructures.linkedList.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

public class Problem_28_to_38 {

    // Problem 28: Display LinkedList from the end
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static void problem_28_displayFromEnd(ListNode head) {
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
            problem_28_displayFromEnd(tail);
            System.out.println("Node Data: " + head.getData());
        }
    }

    // Problem 29: Check if the length od LinkedList is even or odd
    // Using two pointer approach (fast/slow pointers)
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static void problem_29_isLengthEvenOdd(ListNode head) {
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

    // Problem 31: Given two sorted LinkedLists, merge them in sorted order in third LinkedList
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

    // Problem 31: Given two sorted LinkedLists, merge them in sorted order without creating third LinkedList
    // Approach 2: Recursive method
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

    // Problem 32: Reverse LinkedList in pairs
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

    // Problem 32: Reverse LinkedList in pairs
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

    // Problem XX: Exchange adjacent elements of the LinkedList
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

    // Problem 37: Check if a LinkedList is a palindrome or not
    // Approach 1: Without using any additional space (iterating twice)
    // Steps:
    //    - break array in half
    //    - reverse the second half
    //    - iterate on both till positional nodes are equal and LLs last
    //      - break and return false if any set of positional element do not match in two halves
    //      - else return true
    private static boolean isPalindrome1(ListNode head) {
        ListNodeUtils.printList(head);
        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Finding middle of the LinkedList
        while(fast != null && fast.getNext() != null){ //
            fast = fast.getNext();
            if(fast.getNext() != null) {
                fast = fast.getNext();
                slow = slow.getNext();
            }
        }

        // Step 2: Break the LinkedLists into two halves
        ListNode newHead = slow.getNext();
        slow.setNext(null);

        // Reverse the second half of the original LinkedList
        ListNode curr = newHead;
        ListNode prev = null;
        ListNode temp = null;
        while(curr != null){
            temp = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = temp;
        }
        newHead = prev;

        // TEST - if steps till now are going as expected
        // ListNodeUtils.printList(head);
        // ListNodeUtils.printList(newHead);

        // Step 3: Check if the two halves are having same elements
        ListNode c1 = head;
        ListNode c2 = newHead;
        while(c1 != null && c2 != null){
            if(c1.getData() != c2.getData())
                return false;
            c1 = c1.getNext();
            c2 = c2.getNext();
        }
        // Default case - true
        return true;
    }

    // Problem 38: Reverse a LinkedList in block of K (K > 0)
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

    public static void main(String[] args) throws Exception {
        ListNode evenList = ListNodeUtils.getListOfSize(10);
        ListNode oddList  = ListNodeUtils.getListOfSize(11);

        ListNodeUtils.printList(evenList);

        // Problem 28: Display a LinkedList from the end
        System.out.println("\nProblem 28: Displaying LinkedLists in reverse.");
        problem_28_displayFromEnd(evenList);

        // Problem 29: Check if the length of LinkedList is even or odd
        System.out.println();
        problem_29_isLengthEvenOdd(evenList);
        problem_29_isLengthEvenOdd(oddList);

        // Problem 31: Given two sorted LinkedLists, merge them in sorted order in third LinkedList
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

        // Problem 31: Given two sorted LinkedLists, merge them in sorted order in third LinkedList
        // Approach 2: Recursive method
        ListNode sortedList2 = mergeSortedRecursive(ll1, ll2);
        System.out.print("Merged Recursive : ");
        ListNodeUtils.printList(sortedList2);

        // Problem 32: Reverse LinkedList in pairs
        // Approach 1: Recursive method
        System.out.println("\nProblem 34: Pair reverse the LinkedList - Recursively");
        ll1 = ListNodeUtils.getListOfSize(4);
        ListNodeUtils.printList(ll1);
        ListNode pairReversed1 = pairReversedRecursive(ll1);
        ListNodeUtils.printList(pairReversed1);

        // Problem 32: Reverse LinkedList in pairs
        // Approach 2: Iterative method
        System.out.println("\nProblem 34: Pair reverse the LinkedList - Iteratively");
        ll1 = ListNodeUtils.getListOfSize(6);
        ListNodeUtils.printList(ll1);
        ListNode pairReversed2 = pairReversedIterative(ll1);
        ListNodeUtils.printList(pairReversed2);

        // Problem XX: Exchange adjacent elements of the LinkedList
        System.out.println("\nProblem XX: Exchange adjacent elements of the LinkedList");
        System.out.println("[XX] - Means the question is from different version of the book.");
        ll1 = ListNodeUtils.getListOfSize(5);
        ListNodeUtils.printList(ll1);
        ListNode adjacentExchanged = adjacentExchanged(ll1);
        ListNodeUtils.printList(adjacentExchanged);

        // Problem 33-34: Binary Tree related, Sorting related - in respective chapters
        // Problem 35-36: Splitting CircularLinkedList in half - Meh...

        // Problem 37: Check if a LinkedList is a palindrome or not
        // Approach 1: Without using any additional space (iterating twice)
        int[] seq1 = new int[]{1,2,3,4,5,4,3,2,1};  // True - odd length
        int[] seq2 = new int[]{1,2,3,4,4,3,2,1};    // True - even length
        int[] seq3 = new int[]{1,2,3,4,5,4,3,2,0};  // False - odd length
        int[] seq4 = new int[]{1,2,3,4,2,1};        // False - even length
        boolean res1, res2, res3, res4 = false;
        String msg1 = "Problem 37: [Approach 1] Is the LinkedList a palindrome: ";
        res1 = isPalindrome1(ListNodeUtils.getArrayAsLinkedList(seq1));
        res2 = isPalindrome1(ListNodeUtils.getArrayAsLinkedList(seq2));
        res3 = isPalindrome1(ListNodeUtils.getArrayAsLinkedList(seq3));
        res4 = isPalindrome1(ListNodeUtils.getArrayAsLinkedList(seq4));
        System.out.println("\n" + msg1 + res1);
        System.out.println(msg1 + res2);
        System.out.println(msg1 + res3);
        System.out.println(msg1 + res4);

        // Problem 38: Reverse a LinkedList in block of K (K > 0)
        System.out.println("\nProblem 38: Reverse a LinkedList in block of K");
        ll1 = ListNodeUtils.getListOfSize(8);
        ListNodeUtils.printList(ll1);
        ListNode kBlockReverse = kBlockReverseRecursive(ll1, 10);
        ListNodeUtils.printList(kBlockReverse);
    }

}
