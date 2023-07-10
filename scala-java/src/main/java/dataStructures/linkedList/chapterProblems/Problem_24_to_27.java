package dataStructures.linkedList.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

import java.util.HashMap;

// Finding the middle of the LinkedList
public class Problem_24_to_27 {

    // Problem 25: Scanning twice
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static void problem_25_print_middle_nodes(ListNode head) {
        // Sanity check
        if(head == null){
            System.out.println("ERROR: Empty list!");
            return;
        }

        int length = 0;
        ListNode curr = head;
        while(curr != null){
            curr = curr.getNext();
            length += 1;
        }

        int middle = length / 2;
        curr = head;
        int counter = 0;
        while(counter < middle){
            curr = curr.getNext();
            counter += 1;
        }
        System.out.println("Problem 26: Two scans - Middle element: " + curr.getData());
    }

    // Problem 26: Using HashMap
    // First scan -> populate the HashMap and get the length
    // Second scan -> Retrieve middle node from HashMap
    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static void problem_26_print_middle_nodes(ListNode head) {
        // Sanity check
        if(head == null){
            System.out.println("ERROR: Empty list!");
            return;
        }

        ListNode curr = head;
        HashMap<Integer, ListNode> map = new HashMap<Integer, ListNode>();
        int counter = 0;
        while(curr != null){
            map.put(counter, curr);
            curr = curr.getNext();
            counter += 1;
        }
        int middle = counter / 2;
        System.out.println("Problem 27: HashMap - Middle element: " + map.get(middle).getData());
    }

    // Problem 27: Most efficient - two pointers
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static void problem_27_print_middle_nodes(ListNode head) {
        // Sanity check
        if(head == null){
            System.out.println("ERROR: Empty list!");
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.getNext() != null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        System.out.println("Problem 28: Two pointers - Middle element: " + slow.getData());
    }

    public static void main(String[] args) throws Exception {
        ListNode evenList = ListNodeUtils.getListOfSize(10);
        ListNode oddList  = ListNodeUtils.getListOfSize(11);
        ListNodeUtils.printList(evenList);
        ListNodeUtils.printList(oddList);

        // Problem 24: Find middle of LinkedList using brute force
        // For each node, check how far we have come vs how far we need to go
        // Good for thought exercise, but not fruitful to implement

        // Problem 25: Scanning twice
        // First scan -> get the length
        // Second scan -> go till the middle
        System.out.println();
        problem_25_print_middle_nodes(evenList);
        problem_25_print_middle_nodes(oddList);

        // Problem 26: Using HashMap
        // First scan -> populate the HashMap and get the length
        // Second scan -> Retrieve middle node from HashMap
        System.out.println();
        problem_26_print_middle_nodes(evenList);
        problem_26_print_middle_nodes(oddList);

        // Problem 27: Most efficient - using two pointer approach
        // slow pointer -> updates by one node at a time
        // fast pointer -> updates double the speed of slow
        // By the time fast pointer reaches the end, slow points to the middle
        System.out.println();
        problem_27_print_middle_nodes(evenList);
        problem_27_print_middle_nodes(oddList);
    }
}
