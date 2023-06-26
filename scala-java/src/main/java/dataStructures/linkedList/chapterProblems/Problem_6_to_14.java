package dataStructures.linkedList.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

import java.sql.SQLOutput;
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
            while(fast != null && fast.getNext() != null){
                fast = fast.getNext().getNext();
                slow = slow.getNext();

                // Fast and Slow pointers meet
                if(fast == slow)
                    return true;
            }
        }
        return false;
    }

    // Problem 12: Find is loop exists and return the start of the loop
    // Full implementation of Floyd's cycle finding algorithm
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static int findLoopStartNode(ListNode head) {
        // Find the loop, if exists
        ListNode fast = head;
        ListNode slow = head;
        boolean loopExists = false;
        while(fast != null && fast.getNext() != null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if(fast == slow) {
                loopExists = true;
                break;
            }
        }

        // If loop doesn't exist
        if(!loopExists)
            return -1;
        // Loop exists, finding the starting point
        slow = head;
        while(slow != fast){
            slow = slow.getNext();
            fast = fast.getNext();
        }
        // Return answer
        return slow.getData();
    }
    
    public static void main(String[] args) throws Exception {
        // Create a linkedList
        ListNode ll1 = ListNodeUtils.getListOfSize(10);
        ListNodeUtils.printList(ll1);

        // Create a loop, say at 5th node
        ListNode fifthNode = null;
        ListNode curr = ll1;
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

        // LinkedList with no loops
        ListNode ll2 = ListNodeUtils.getListOfSize(12);
        ListNodeUtils.printList(ll2);

        // Problem 6-7: Verify of the loop exists in a LinkedList
        // Problem  11: Same as Problem 6 find whether Snake or Snail
        //              - Snake (null terminated) or Snail(contains loop)]
        boolean doesLoopExists1 = problem_6_and_7_hashmap(ll1);
        boolean doesLoopExists2 = problem_6_and_7_hashmap(ll2);
        System.out.println("\nProblem 6-7: Does loop exist (expected -> True) : " + doesLoopExists1);
        System.out.println("Problem 6-7: Does loop exist (expected -> False): " + doesLoopExists2);

        // Problem 8: Add nodes into array and sort them and blah...
        // Good for thought exercise, but not meaningful to implement just to prove

        // Problem 9: Solve using Floyd's cycle finding algorithm
        boolean doesLoopExists3 = floydsCycleFindingAlgo(ll1);
        boolean doesLoopExists4 = floydsCycleFindingAlgo(ll2);
        System.out.println("\nProblem 9: Floyd's - does loop exist (expected -> True) : " + doesLoopExists3);
        System.out.println("Problem 9: Floyd's - does loop exist (expected -> False): " + doesLoopExists4);

        // Problem 12: Check if loop exists in a LinkedList.
        // If yes, find the start of the loop.
        int loopStartsAt1 = findLoopStartNode(ll1);
        int loopStartsAt2 = findLoopStartNode(ll2);
        System.out.println("\nProblem 12: Loop exists in LL1 at node: " + loopStartsAt1);
        System.out.println("\nProblem 12: Loop exists in LL2 at node: " + loopStartsAt2);
    }

}
