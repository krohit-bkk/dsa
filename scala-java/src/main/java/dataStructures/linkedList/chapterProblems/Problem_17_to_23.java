package dataStructures.linkedList.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// Intersecting LinkedLists: Two LinkedLists having separate heads.
// List intersect at some node, and continue as one.
// Find:
//   - if LinkedLists intersect
//   - node of intersections
public class Problem_17_to_23 {
    public static void main(String[] args) throws Exception {
        // Create first LinkedList
        ListNode ll1 = ListNodeUtils.getListOfSize(10);
        ListNode ll2 = ListNodeUtils.getListOfSize(4);

        // Lets see both LinkedLists
        System.out.println(">>>> Before intersection....");
        ListNodeUtils.printList(ll1);
        ListNodeUtils.printList(ll2);
        // Let's join the end of ll2 with 7-th node of ll1
        int counter = 0;
        ListNode seventNode = ll1;
        while(counter < 6) {
            seventNode = seventNode.getNext();
            counter += 1;
        }

        ListNode curr = ll2;
        while(curr.getNext()!= null)
            curr = curr.getNext();

        curr.setNext(seventNode);

        // Let's see both LinkedLists after intersection
        System.out.println(">>>> After intersection....");
        ListNodeUtils.printList(ll1);
        ListNodeUtils.printList(ll2);


        // Problem 17: Brute force approach
        // Compare each node of one LinkedList with each of another one
        boolean answer1 = checkIntersectionBruteForce(ll1, ll2);
        boolean answer2 = checkIntersectionBruteForce(ll1, ListNodeUtils.getListOfSize(5));
        System.out.println("Problem 17: Checking intersection (True): " + answer1);
        System.out.println("Problem 17: Checking intersection (False): " + answer2);

        // Problem 18/21/22: Using searching/sorting technique methods
        // We will revisit this in the respective chapters

        // Problem 19: Using HashMap/HashSet approach
        // Compare each node of one LinkedList with each of another one
        boolean answer3 = checkIntersectionHashSet(ll1, ll2);
        boolean answer4 = checkIntersectionHashSet(ll1, ListNodeUtils.getListOfSize(5));
        System.out.println("Problem 19: Checking intersection (True): " + answer3);
        System.out.println("Problem 19: Checking intersection (False): " + answer4);

        // Problem 20: Using Stacks approach
        boolean answer5 = checkIntersectionStacks(ll1, ll2);
        boolean answer6 = checkIntersectionStacks(ll1, ListNodeUtils.getListOfSize(5));
        System.out.println("Problem 20: Checking intersection (True): " + answer5);
        System.out.println("Problem 20: Checking intersection (False): " + answer6);

        // Problem 23: Using no extra space
        // Use two pointers and maintain distance = len(ll1) - len(ll2)
        boolean answer7 = checkIntersectionDistance(ll1, ll2);
        boolean answer8 = checkIntersectionDistance(ll1, ListNodeUtils.getListOfSize(5));
        System.out.println("Problem 23: Checking intersection (True): " + answer7);
        System.out.println("Problem 23: Checking intersection (False): " + answer8);

    }

    // Problem 17: Brute force to find intersection
    // Time  complexity: O[N2]
    // Space complexity: O[1]
    private static boolean checkIntersectionBruteForce(ListNode ll1, ListNode ll2) {
        // Compare each node of first list with all nodes of 2nd list
        ListNode curr1 = ll1;
        while(curr1 != null){
            ListNode curr2 = ll2;
            while(curr2 != null){
                if(curr1 == curr2) {
                    System.out.println("\nIntersection at: " + curr1.getData());
                    return true;
                }
                curr2 = curr2.getNext();
            }
            curr1 = curr1.getNext();
        }
        return false;
    }

    // Problem 19: Use HashSet to find intersection
    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static boolean checkIntersectionHashSet(ListNode ll1, ListNode ll2) {
        Set<ListNode> set = new HashSet<ListNode>();
        ListNode curr = ll1;
        while(curr != null) {
            set.add(curr);
            curr = curr.getNext();
        }

        curr = ll2;
        while(curr != null){
            if(set.contains(curr)) {
                System.out.println("\nIntersection at: " + curr.getData());
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    // Problem 20: Use Stacks to find intersection
    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static boolean checkIntersectionStacks(ListNode ll1, ListNode ll2) {
        // Not implementing stacks of our own to stay focussed on LinkedList
        Stack<ListNode> stack1 = new Stack<ListNode>();
        Stack<ListNode> stack2 = new Stack<ListNode>();

        // Populate stacks
        ListNode curr1 = ll1;
        ListNode curr2 = ll2;
        while(curr1 != null || curr2 != null){
            if(curr1 != null) {
                stack1.push(curr1);
                curr1 = curr1.getNext();
            }
            if(curr2 != null) {
                stack2.push(curr2);
                curr2 = curr2.getNext();
            }
        }

        // Check stacks
        if(stack1.peek() != stack2.peek()) {
            return false;
        }

        int last = -1;
        while(!stack1.isEmpty() && !stack2.isEmpty()){
            if(stack1.peek() == stack2.peek()) {
                last = stack1.peek().getData();
                stack1.pop();
                stack2.pop();
            }
            else{
                System.out.println("\nIntersection at: " + last);
                break;
            }
        }
        return true;
    }

    // Problem 23: Using no extra space
    // Use two pointers and maintain distance = len(ll1) - len(ll2)
    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static boolean checkIntersectionDistance(ListNode ll1, ListNode ll2) {
        ListNode curr1 = ll1;
        ListNode curr2 = ll2;

        int l1 = 0;
        int l2 = 0;
        // Finding lengths of both LinkedLists
        while(curr1 != null || curr2 != null){
            if(curr1 != null){
                l1 += 1;
                curr1 = curr1.getNext();
            }
            if(curr2 != null){
                l2 += 1;
                curr2 = curr2.getNext();
            }
        }
        // Calculate the length gap
        int dist = Math.abs(l1 - l2);

        ListNode longer, shorter;
        if(l1 > l2){
            longer  = ll1;
            shorter = ll2;
        }
        else {
            longer  = ll2;
            shorter = ll1;
        }
        // Make longer pointer take (number of steps) = dist
        int counter = 0;
        while(counter < dist) {
            longer = longer.getNext();
            counter += 1;
        }

        // Now both the pointers should be equidistant from their respective list's end node
        while(longer != null){
            if(longer == shorter){
                System.out.println("\nIntersection at: " + longer.getData());
                return true;
            }
            longer = longer.getNext();
            shorter = shorter.getNext();
        }
        // Default case - when no intersection
        return false;
    }

}