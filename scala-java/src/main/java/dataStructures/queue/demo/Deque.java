package dataStructures.queue.demo;

import dataStructures.queue.ListNode;

public class Deque {
    public ListNode front, rear;
    public Integer length;
    public Boolean isEmpty;

    public Deque(){
        this.front = null;
        this.rear = null;
        this.length = 0;
        this.isEmpty = true;
    }

    // Check if deque is empty
    public Boolean isEmpty(){
        return this.isEmpty;
    }

    // Increment the length of the deque
    public void lengthIncrement(){
        this.length += 1;

        if(this.isEmpty())
            this.isEmpty = false;
    }

    // Decrement the length of the deque
    public void lengthDecrement(){
        if(this.length > 0)
            this.length -= 1;

        // Check for isEmpty flag as well
        if(this.length == 0)
            this.isEmpty = true;
    }

    // Add the first element of deque
    private void addFirstNode(ListNode node){
        // Initialize the pointers
        if(this.isEmpty){
            this.front = node;
            this.rear  = node;
        }
    }

    // Add new element (node) at the beginning of deque
    public void addFirst(ListNode node){
        if(this.isEmpty)
            this.addFirstNode(node);
        else{
            node.setNext(this.front);
            this.front.setPrev(node);
            this.front = node;
        }

        // Update length and isEmpty flag
        this.lengthIncrement();
        this.isEmpty = false;
    }

    // Add new element (data) at the beginning of deque
    public void addFirst(int data){
        ListNode node = new ListNode(data);
        addFirst(node);
    }

    // Remove first element from the deque
    public Integer removeFirst(){
        if(this.isEmpty())
            return null;

        // Data from the first element
        Integer data = this.front.getData();
        if(this.front == this.rear){
            this.front = null;
            this.rear = null;
        }
        else
            this.front = this.front.getNext();

        // Decrement the length and check for isEmpty flag
        this.lengthDecrement();

        return data;
    }

    // Add new element (node) at the end of deque
    public void addLast(ListNode node){
        // Is deque initialized?
        if(this.isEmpty())
            addFirstNode(node);
        else{
            this.rear.setNext(node);
            node.setPrev(this.rear);
            this.rear = node;
        }

        // Update length increment and isEmpty flag
        this.lengthIncrement();
        this.isEmpty = false;
    }

    // Add new element (data) at the end of deque
    public void addLast(Integer data){
        ListNode node = new ListNode(data);
        this.addLast(node);
    }

    // Remove element from the end of deque
    public Integer removeLast(){
        if(this.isEmpty())
            return null;

        // Data from the last element of deque]
        Integer data = this.rear.getData();

        // If only one element
        if(this.front == this.rear){
            this.front = null;
            this.rear = null;
        }
        else{
            this.rear = this.rear.getPrev();
        }

        // Update length and isEmpty flag
        this.lengthDecrement();

        return data;
    }

    // Peek the first element of the deque
    public Integer peekFirst(){
        return this.front.getData();
    }

    // Peek the last element of the deque
    public Integer peekLast(){
        return this.rear.getData();
    }

    public void dequeInfo(){
        String frontData, rearData;
        frontData = (this.front != null) ? this.front.getData().toString() : "FRONT NULL";
        rearData  = (this.rear  != null) ? this.rear.getData().toString()  : "REAR  NULL";
        System.out.println(
                "\nLength: " + this.length
                + "\nIsEmpty: " + this.isEmpty
                + "\nFront [" + frontData + "]"
                + "\nRear  [" + rearData + "]"
        );
        if(!this.isEmpty()){
            StringBuilder msg = new StringBuilder("FRONT");
            ListNode curr = this.front;
            msg.append(" <--> ").append(curr.getData());
            while(curr != this.rear){
                curr = curr.getNext();
                msg.append(" <--> ").append(curr.getData());
            }
            msg.append(" <--> ").append("REAR");
            System.out.println(msg.toString());
        }
    }
}
