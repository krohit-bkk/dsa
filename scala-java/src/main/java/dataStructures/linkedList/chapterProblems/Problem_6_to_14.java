package dataStructures.linkedList.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

import java.util.HashSet;
import java.util.Set;

// Check whether a LinkedList is NULL terminated or not.
// Check the point of origination of loop, if any.
// Floyd's cycle finding algorithm
public class Problem_6_to_14 {
    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static boolean problem_6_and_7_hashmap(ListNode head) {
        // Case 1: Empty list
        if(head == null) {
            System.out.println("ERROR: Empty list!");
            return false;
        }

        // Hashmap - node vs its index
        Set<ListNode> hashSet = new HashSet<ListNode>();

        // Iterate and check
        ListNode curr = head;
        while(curr != null){
            // Node already exists in hashmap
            if(hashSet.contains(curr))
                return true;
            hashSet.add(curr);
            curr = curr.getNext();
        }
        // Loop doesn't exist
        return false;
    }

    // Problem 9: Fast-Slow pointer approach
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static boolean floydsCycleFindingAlgo(ListNode head) {
        if(head != null){
            ListNode fast = head;
            ListNode slow = head;
            while(fast != null && slow != null){
                fast = fast.getNext();
                slow = slow.getNext();
                // Fast reached the end of list
                if(fast == null)
                    return false;
                // Increment fast once again
                if(fast.getNext() != null)
                    fast = fast.getNext();

                // Fast and Slow pointers meet
                if(fast == slow)
                    return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        // Create a linkedList
        ListNode head = ListNodeUtils.getListOfSize(10);
        ListNodeUtils.printList(head);

        // Create a loop, say at 5th node
        ListNode fifthNode = null;
        ListNode curr = head;
        int counter = 0;
        while(curr.getNext() != null){
            curr = curr.getNext();
            counter += 1;
            if(counter == 4)
                fifthNode = curr;
        }
        curr.setNext(fifthNode);

        // Proof that loop has been created
        System.out.println();
        System.out.println("fifthNode data: " + fifthNode.getData());
        System.out.println("last node data: " + curr.getData());
        System.out.println("last next node data: " + curr.getNext().getData());

        // Problem 6-7: Verify of the loop exists in a LinkedList
        boolean doesLoopExists1 = problem_6_and_7_hashmap(head);
        System.out.println("\nProblem 6-7: Does loop exist: " + doesLoopExists1);

        // Problem 8: Add nodes into array and sort them and blah...
        // Good for thought exercise, but not meaningful to implement just to prove

        // Problem 9: Solve using Floyd's cycle finding algorithm
        boolean doesLoopExists2 = floydsCycleFindingAlgo(head);
        System.out.println("\nProblem 9: Floyd's - does loop exist: " + doesLoopExists2 + "\n\n");

        ListNode ll2 = ListNodeUtils.getListOfSize(12);     // LinkedList without loops
        ListNodeUtils.printList(ll2);
        boolean doesLoopExists3 = floydsCycleFindingAlgo(ll2);
        System.out.println("Problem 9: Floyd's - does loop exist: " + doesLoopExists3);
    }
}
