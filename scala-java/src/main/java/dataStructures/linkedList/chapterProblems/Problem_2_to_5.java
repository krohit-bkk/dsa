package dataStructures.linkedList.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

import java.util.HashMap;
import java.util.Map;

// Find n-th node from the end of a LinkedList
public class Problem_2_to_5 {

    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static int problem3_using_hashmap(ListNode head, int n) {
        // Check if list is empty or index from end is invalid
        if(head == null || n < 1) {
            System.out.println("ERROR: Invalid input! Returning [-1].");
            return -1;
        }
        Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();
        int counter = 0;
        ListNode curr = head;
        while(curr != null){
            map.put(counter, curr);
            curr = curr.getNext();
            counter += 1;
        }

        // Index from the end
        int requiredIndex = counter - n;
        return map.get(requiredIndex).getData();
    }

    // Time  complexity: O[N]
    // Space complexity: O[1]
    public static int problem4_brute_force(ListNode head, int n){
        if(head == null)
            return -1;

        // Step-1: Count the number of nodes
        int length = 0;
        ListNode curr = head;
        while(curr != null) {
            length += 1;
            curr = curr.getNext();
        }

        // Check if length >= n
        if(!(length >= n) || n < 1) {
            System.out.println("ERROR: Invalid input! Returning [-1].");
            return -1;
        }

        // Step-2: Go until (length - n + 1) node
        int counter = 0;
        curr = head;
        while(counter < (length - n)){
            curr = curr.getNext();
            counter += 1;
        }
        // Return the answer
        return curr.getData();
    }

    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static int problem5_most_efficient(ListNode head, int n) {
        // Case 1: Invalid inputs
        if(head == null || n < 1){
            System.out.println("ERROR: Invalid inputs! Returning [-1].");
            return -1;
        }

        // Manage a gap of distance n between two pointers - curr and req
        // When curr points to last node, req points to desired node
        ListNode curr = head;
        ListNode req  = head;
        int counter = 0;
        while(curr != null){
            if(!(counter < n))
                req = req.getNext();
            curr = curr.getNext();
            counter += 1;
        }
        if(n > counter){
            System.out.println("ERROR: Invalid inputs! Returning [-1].");
            return -1;
        }
        // Return answer
        return req.getData();
    }

    public static void main(String[] args) throws Exception {
        // Create a dummy LinkedList, say of size 10
        ListNode head = ListNodeUtils.getListOfSize(10);
        ListNodeUtils.printList(head);

        // n-th index from the end
        int n = 0;

        // Problem-2: Solving through the suggested approach is way too useless

        // Problem-3: Solving using HashMap
        int answer3 = problem3_using_hashmap(head, n);
        System.out.println("Problem-3 answer: " + answer3);

        // Problem-4: Solving using brute force
        int answer4 = problem4_brute_force(head, n);
        System.out.println("Problem-4 answer: " + answer4);

        // Problem-5: Solving using brute force
        int answer5 = problem5_most_efficient(head, n);
        System.out.println("Problem-5 answer: " + answer5);
    }
}
