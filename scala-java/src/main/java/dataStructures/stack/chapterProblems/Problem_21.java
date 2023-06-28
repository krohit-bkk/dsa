package dataStructures.stack.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

import java.util.Stack;

public class Problem_21 {

    // Problem 21: Intersecting LinkedLists
    public static void main(String[] args) throws Exception {
        // Check if the two LinkedLists are intersecting at any point
        // If they do, find the node of intersection, using Stacks
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

        // Check if LinkedLists are intersecting using Stack
        System.out.println();
        System.out.println("Do ll1, ll2 intersect?\n--> " + areListsIntersecting(ll1, ll2));
    }

    // Problem 21: Intersecting LinkedLists
    // Approach 1: Put nodes from both LinkedLists in two different Stacks
    // If tops of both stacks are same, means they intersect.
    // Keep popping both stacks until the nodes are different
    private static boolean areListsIntersecting(ListNode ll1, ListNode ll2) {
        Stack<ListNode> s1 = new Stack<ListNode>();
        Stack<ListNode> s2 = new Stack<ListNode>();

        if(ll1 == null || ll2 == null)
            return true;

        ListNode curr1 = ll1;
        ListNode curr2 = ll2;

        while(curr1 != null || curr2 != null){
            if(curr1 != null) {
                s1.push(curr1);
                curr1 = curr1.getNext();
            }
            if(curr2 != null) {
                s2.push(curr2);
                curr2 = curr2.getNext();
            }
        }

        if(s1.peek() != s2.peek()){
            System.out.println("Lists do not intersect!");
            return false;
        }
        ListNode last = null;
        while(s1.peek() == s2.peek()){
            last = s1.peek();
            s1.pop();
            s2.pop();
        }
        System.out.println("Intersection starts at: " + last.getData());
        return true;
    }
}
