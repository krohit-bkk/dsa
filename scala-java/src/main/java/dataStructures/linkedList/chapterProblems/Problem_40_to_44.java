package dataStructures.linkedList.chapterProblems;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class RandomNode {
    private Integer data;
    private RandomNode next;
    private RandomNode random;

    public RandomNode(){
        this.data = null;
        this.next = null;
        this.random = null;
    }

    public RandomNode(int data){
        this.data = data;
        this.next = null;
        this.random = null;
    }

    // Get the LinkedList as String
    public static String getListAsString(RandomNode head){
        StringBuilder msg = new StringBuilder("HEAD");
        RandomNode curr = head;
        while (curr != null){
            RandomNode randNode = curr.getRandom();
            int randData = -1;  // If random pointer is not assigned
            if(randNode != null)
                randData = randNode.getData();
            msg.append(" --> ").append(curr.getData() + " [" + randData + "]");
            curr = curr.getNext();
        }
        msg.append(" --> ").append("END");
        return msg.toString();
    }

    // Print the LinkedList as String
    public static void printList(RandomNode head){
        System.out.println(getListAsString(head));
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public RandomNode getNext() {
        return next;
    }

    public void setNext(RandomNode next) {
        this.next = next;
    }

    public RandomNode getRandom() {
        return random;
    }

    public void setRandom(RandomNode random) {
        this.random = random;
    }
}

public class Problem_40_to_44 {
    public static void main(String[] args) throws Exception {
        // Problem 40: Josephus Circle
        // TBD

        // Problem 41: Given a LinkedList with data, next pointer and random pointer
        // Write an algorithm to clone it
        // Creating a LinkedList with Random pointers
        RandomNode head = null;
        RandomNode curr = null;
        Map<Integer, RandomNode> map = new HashMap<Integer, RandomNode>();
        int numNodes = 4;   // Control the number of elements in the LinkedList
        for (int i = 0; i < numNodes; i++) {
            RandomNode node = new RandomNode(i);
            if(head == null)
                head = node;
            else
                curr.setNext(node);
            map.put(i, node);
            curr = node;
        }
        // TEST
        // System.out.println("Before random pointer assignment...");
        // RandomNode.printList(head);

        // Assign random pointers in the LinkedList
        curr = head;
        int index = 0;
        Random r = new Random();
        while(curr != null){
            int randomIndex = r.nextInt(numNodes);
            RandomNode currNode = map.get(index);
            RandomNode randomNode = map.get(randomIndex);
            currNode.setRandom(randomNode);
            index += 1;
            curr = curr.getNext();
        }

        System.out.println("Problem 41: Approach 1: Using extra space.");
        System.out.println("Original LinkedList input...");
        RandomNode.printList(head);

        // Now cloning...
        RandomNode clonedHead = cloneRandomLinkedList(head);
        System.out.println("After cloning the original LinkedList...");
        RandomNode.printList(clonedHead);

        System.out.println("\nProblem 42: Approach 2: Optimized way.");
        System.out.println("Original LinkedList input...");
        RandomNode.printList(head);
        RandomNode clonedHead1 = cloneRandomLinkedListOptimized(head);
        System.out.println("After cloning the original LinkedList (optimized)...");
        RandomNode.printList(clonedHead1);

        // Problem 43: Given a LinkedList with even and odd numbers, create an algorithm to
        // make changes in such a way that even numbers appear at the beginning
        ListNode list1 = ListNodeUtils.getListOfSize(8);
        System.out.println("\nProblem 43: Even nodes before odd");
        System.out.println("Before segregation...");
        ListNodeUtils.printList(list1);
        list1 = evenNodesAtBeginning(list1);
        System.out.println("After segregation...");
        ListNodeUtils.printList(list1);

        // Problem 44: In a LinkedList with n nodes, the time taken to insert an element after
        // an element pointed by some pointer is => O[1]
    }

    // Problem 41: Given a LinkedList with data, next pointer and random pointer
    // Write an algorithm to clone it
    // Approach 1: Using extra space
    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static RandomNode cloneRandomLinkedList(RandomNode head) {
        RandomNode newHead = null;
        RandomNode newCurr = null;
        RandomNode curr = head;

        Map<RandomNode, RandomNode> nodeMapping = new HashMap<RandomNode, RandomNode>();

        // Iterate for data in original LinkedList
        // Keep building new LinkedList using next
        int index = 0;
        while(curr != null){
            RandomNode node = new RandomNode(curr.getData());
            nodeMapping.put(curr, node);
            if(newHead == null)
                newHead = node;
            else
                newCurr.setNext(node);
            newCurr = node;

            index += 1;
            curr = curr.getNext();
        }

        // Iterate for random node in original LinkedList
        // Leverage node vs node mapping
        curr = head;
        index = 0;
        while(curr != null){
            RandomNode node = nodeMapping.get(curr);
            node.setRandom(nodeMapping.get(curr.getRandom()));
            index += 1;
            curr = curr.getNext();
        }
        return newHead;
    }

    // Problem 42: Given a LinkedList with data, next pointer and random pointer
    // Write an algorithm to clone it
    // Approach 2: Without using extra space
    // Time  complexity: O[N]
    // Space complexity: O[1]
    private static RandomNode cloneRandomLinkedListOptimized(RandomNode head) {
        RandomNode newHead = null;
        RandomNode newCurr = null;
        RandomNode curr = head;

        // Step 1: Clone the original LinkedList
        while(curr != null){
            RandomNode node = new RandomNode(curr.getData());
            if(newHead == null)
                newHead = node;
            else
                newCurr.setNext(node);
            newCurr = node;
            curr = curr.getNext();
        }

        // Step 2: Intertwine the two LinkedLists
        curr = head;
        newCurr = newHead;
        while(curr != null){
            RandomNode next = curr.getNext();
            RandomNode newNext = newCurr.getNext();
            curr.setNext(newCurr);
            newCurr.setNext(next);
            newCurr = newNext;
            curr = next;
        }
        // TEST - Verify the intertwining
        // System.out.println("Check if intertwining was OK");
        // RandomNode.printList(head);

        // Step 3: Assign random pointers to the new nodes
        curr = head;
        while(curr != null){
            newCurr = curr.getNext();
            newCurr.setRandom(curr.getRandom().getNext());
            curr = curr.getNext().getNext();
        }
        // TEST - Verify random assignments
        // System.out.println("Check if random pointer assignment was OK");
        // RandomNode.printList(head);

        // Step 4: Break into two LinkedLists
        curr = head;
        newCurr = newHead;
        while(curr != null && newCurr.getNext() != null){
            curr.setNext(curr.getNext().getNext());
            newCurr.setNext(newCurr.getNext().getNext());

            curr = curr.getNext();
            newCurr = newCurr.getNext();
        }
        curr.setNext(null);

        return newHead;
    }

    // Problem 43: Given a linked list wiLh even and odd numbers,
    // create an algorithm for making changes to the list in such a way that
    // all even numbers appear at the beginning.
    private static ListNode evenNodesAtBeginning(ListNode head) {
        // Step 1: Break the LinkedList into two LinkedLists (Even/Odd LLs)
        ListNode evenHead = null;
        ListNode  oddHead = null;
        ListNode evenLast = null;
        ListNode  oddLast = null;

        ListNode curr = head;
        while(curr != null){
            if((curr.getData() & 1) == 0){
                if(evenHead == null){
                    evenHead = curr;
                    evenLast = curr;
                }
                else{
                    evenLast.setNext(curr);
                    evenLast = evenLast.getNext();
                }
            }
            else{
                if(oddHead == null){
                    oddHead = curr;
                    oddLast = curr;
                }
                else{
                    oddLast.setNext(curr);
                    oddLast = oddLast.getNext();
                }
            }
            curr = curr.getNext();
        }

        // No change - all are either even or odd
        if(evenHead == null || oddHead == null)
            return head;

        // Step 2: Append the Odd LL at the end of the even LL
        evenLast.setNext(oddHead);
        oddLast.setNext(null);
        return evenHead;
    }

}