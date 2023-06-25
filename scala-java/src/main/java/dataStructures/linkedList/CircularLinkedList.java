package dataStructures.linkedList;

import scala.collection.concurrent.LNode;

import java.util.List;

public class CircularLinkedList {
    private ListNode head;
    private int length;

    // Default constructor
    public CircularLinkedList(){
        this.head = null;
        this.length = 0;
    }

    // Check if CircularLinkedList is empty
    public boolean isEmpty(){
        return this.head == null;
    }
    // Insert element in the beginning of CircularLinkedList
    public void push(int data){
        ListNode node = new ListNode(data);
        if(this.isEmpty()){
            this.head = node;
            node.setNext(this.head);
        }
        else{
            // Make new node point to prev first element
            node.setNext(this.head);

            // Find current last element and make it point to new node
            ListNode curr = this.head;
            while(curr.getNext() != this.head)
                curr = curr.getNext();
            curr.setNext(node);
            // Update head to point to new node (new 1st node)
            this.head = node;
        }
        this.length += 1;
    }

    // Insert element in the beginning of CircularLinkedList
    public void append(int data){
        ListNode node = new ListNode(data);
        if(this.isEmpty()){
            this.head = node;
            node.setNext(node);
        }
        else{
            ListNode curr = this.head;
            // Iterate to the last element of CLL
            while(curr.getNext() != this.head)
                curr = curr.getNext();
            // Add new node to the CLL
            curr.setNext(node);
            node.setNext(this.head);
        }
        this.length += 1;
    }

    // Push all elements of array at the beginning of CLL
    public void pushArray(int[] arr){
        for(int data: arr)
            this.push(data);
    }

    // Append all elements of array at the end of CLL
    public void appendArray(int[] arr){
        for(int data: arr)
            this.append(data);
    }

    // Removes the first element of the CircularLinkedList
    public void removeFirst(){
        if(!this.isEmpty()){
            // Case 1: Check if only 1 element exists in CLL
            if(this.head.getNext() == this.head)
                this.head = null;
            else{
                // Make last node point to the 2nd element of CLL
                ListNode curr = this.head;
                while(curr.getNext() != this.head)
                    curr = curr.getNext();
                curr.setNext(this.head.getNext());
                // Adjust head pointer
                this.head = this.head.getNext();
            }
            this.length -= 1;
        }
    }

    // Remove the last element of the CircularLinkedList
    public void removeLast(){
        if(!this.isEmpty()){
            // Case 1: Only 1 element in CLL
            if(this.head.getNext() == this.head)
                this.head = null;
            // Regular Case: Go to last but one node
            else{
                ListNode curr = this.head;
                // reach 2nd last element
                while(curr.getNext().getNext() != this.head)
                    curr = curr.getNext();
                // Make 2nd last node point to 1st node of CLL
                curr.setNext(this.head);
            }
            this.length -= 1;
        }
    }

    // Return CircularLinkedList as String
    public String getListAsString(){
        StringBuilder msg = new StringBuilder("HEAD");
        if(this.head != null){
            ListNode curr = this.head;
            // All elements but last of the CLL
            while (curr.getNext() != this.head) {
                msg.append(" <--> ").append(curr.getData());
                curr = curr.getNext();
            }
            // Last element of the CLL
            msg.append(" <--> ").append(curr.getData());
        }
        msg.append(" <--> ").append("TAIL");
        return msg.toString();
    }

    // Print CircularLinkedList as String
    public void printList(){
        System.out.println(this.getListAsString());
        System.out.println("Current length of CircularLinkedList is: " + this.length);
    }
}
