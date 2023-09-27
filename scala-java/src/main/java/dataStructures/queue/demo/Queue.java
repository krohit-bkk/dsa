package dataStructures.queue.demo;

import dataStructures.queue.ListNode;

public class Queue {
    public ListNode front, rear;
    public Integer length;
    public Boolean isEmpty;

    public Queue(){
        this.front = null;
        this.rear = null;
        this.length = 0;
        this.isEmpty = true;
    }

    // Return if queue is empty
    public Boolean isEmpty(){
        return this.isEmpty;
    }

    // Increment the length of the Queue
    private void lengthIncrement() {
        this.length += 1;
    }

    // Decrement the length of the Queue
    private void lengthDecrement() {
        if(this.length > 0)
            this.length -= 1;
    }

    // Get queue as String object
    public String getQueueAsString(){
        if(!this.isEmpty) {
            ListNode curr = this.front;
            StringBuilder msg = new StringBuilder("Length [" + this.length + "] >> " + "FRONT");
            msg.append(" --> ").append(curr.getData());
            while(curr != this.rear){
                curr = curr.getNext();
                msg.append(" --> ").append(curr.getData());
            }
            msg.append(" --> ").append("REAR");
            return msg.toString();
        }
        return "EMPTY QUEUE";
    }

    // Print Queue as String
    public void printQueue(){
        System.out.println(this.getQueueAsString());
    }

    // Enqueue - an element in the queue
    public void enqueueNode(Integer data){
        // Create a new node to hold data
        ListNode node = new ListNode(data);

        if(this.isEmpty)
            this.front = node;
        else
            this.rear.setNext(node);

        this.rear = node;
        this.lengthIncrement();
        this.isEmpty = false;
    }

    // Enqueue - a node in the queue
    public void enqueueNode(ListNode node){
        // Create a new node to hold data
        if(this.isEmpty)
            this.front = node;
        else
            this.rear.setNext(node);

        this.rear = node;
        this.lengthIncrement();
        this.isEmpty = false;
    }

    // EnqueueAll - the elements of the Array into queue
    public void enqueueAll(int[] arr){
        for(Integer data : arr)
            enqueueNode(data);
    }

    // Dequeue - remove the front element from the queue
    public Integer dequeue(){
        if(!this.isEmpty()){
            Integer data = this.front.getData();
            this.front = this.front.getNext();

            // Length decrement
            this.lengthDecrement();

            // Check if queue is empty
            if(this.length == 0)
                this.isEmpty = true;
            return data;
        }
        System.out.println("ERROR: Cannot de-queue an empty queue");
        return null;
    }

}
