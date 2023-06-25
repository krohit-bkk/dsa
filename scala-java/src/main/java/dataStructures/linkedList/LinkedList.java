package dataStructures.linkedList;

public class LinkedList {
    private int length = 0;
    public ListNode head = null;

    // Default constructors
    public LinkedList(){
        this.length = 0;
    }

    // Get length of LinkedList
    public int getLength(){
        int length = 0;
        ListNode curr = this.head;
        while(curr != null){
            length += 1;
            curr = curr.getNext();
        }
        return length;
    }

    // Set the length of LinkedList - for manual ops
    public void setLength(int length){
        this.length = length;
    }

    // Insert element at the beginning
    public void push(int data){
        // Create a new node
        ListNode node = new ListNode(data);
        // Insert the node at the beginning
        node.setNext(this.head);
        this.head = node;
        this.length += 1;
    }

    // Insert element at the beginning
    public void append(int data){
        // Create a new node
        ListNode node = new ListNode(data);

        // Check if LinkedList is empty
        if(this.head == null)
            this.head = node;
        else {
            ListNode curr = this.head;
            // Iterate to reach the end of the list
            while (curr.getNext() != null)
                curr = curr.getNext();

            // Append at the end
            curr.setNext(node);
        }
        this.length += 1;
    }

    // Push all elements of array in LinkedList
    public void pushArray(int[] arr){
        for(int data : arr)
            this.push(data);
    }

    // Append all elements of array in LinkedList
    public void appendArray(int[] arr){
        for(int data : arr)
            this.append(data);
    }

    // Insert at a given index in the LinkedList
    public void insertAt(int index, int data){
        int currIndex = 0;
        ListNode curr = this.head;
        ListNode node = new ListNode(data);

        // Case 1: Insertion at the beginning of the LinkedList
        if(index == 0){
            node.setNext(this.head);
            this.head = node;
            this.length += 1;
        }
        else{
            while(curr != null && currIndex < index - 1){
                curr = curr.getNext();
                currIndex += 1;
            }
            // Case 2: LinkedList is shorted than the index requested
            if(curr == null)
                System.out.println("ERROR: IndexOutOfBoundsException - Cannot insert at the index ["
                        + index + "]. Size of linkedList: " + currIndex
                );
            else{
                node.setNext(curr.getNext());
                curr.setNext(node);
                this.length += 1;
            }
        }
    }

    // Delete an element from the beginning of the LinkedList
    public void removeFirst(){
        if(this.head != null){
            this.head = this.head.getNext();
            this.length -= 1;
        }
        else
            System.out.println("ERROR: Empty linkedList. Nothing to delete!");
    }

    // Delete an element from the end of the LinkedList
    public void removeLast(){
        // Iterate to the second last node of the LinkedList
        ListNode curr = this.head;

        if(this.head != null){
            // Goto second last node of the LinkedList
            while(curr.getNext().getNext() != null)
                curr = curr.getNext();

            // Remove the last element
            curr.setNext(null);
            this.length -= 1;
        }
        else
            System.out.println("ERROR: Empty linkedList. Nothing to delete!");
    }

    // Delete an element at a given index from a LinkedList
    public void removeAt(int index){
        // Case 0: index < 0 -> Remove the first element
        if(index < 0) {
            System.out.println("ERROR: Invalid index [" + index + "]. Valid index between 0 and size(LinkedList)");
            return;
        }
        // Case 1: index == 0 -> Remove the first element
        if(index == 0) {
            this.removeFirst();
            return;
        }
        int currIndex = 0;
        ListNode curr = this.head;

        // Iterate over to the index
        while(this.head != null && curr != null && currIndex < index-1) {
            curr = curr.getNext();
            currIndex += 1;
        }
        System.out.println("CurrIndex: " + currIndex);// + ", Curr Data: " + curr.getData());
        // Did we reach the intended index?
        if(currIndex == index -1){
            curr.setNext(curr.getNext().getNext());
            this.length -= 1;
        }
        // IndexOutOfBoundException - LinkedList is smaller in size.
        else
            System.out.println("ERROR: IndexOutOfBoundException - Requested index ["
                    + index + "] cannot be reached. " +
                    "LinkedList is smaller in size [" + this.length + "]."
            );
    }

    // Find if item exists in the LinkedList
    public boolean exists(int item){
        // Case 1: Check if LinkedList is empty
        if(this.head == null)
            return false;

        ListNode curr = this.head;
        while(curr != null && curr.getData() != item)
            curr = curr.getNext();

        // Check if curr is the item
        return curr != null;
    }

    // Return the index of first occurrence of item in LinkedList
    public int findIndex(int item){
        int index = 0;     // Index for item not existing

        // Case 1: LinkedList is empty
        if(this.head == null)
            return -1;

        ListNode curr = this.head;
        while(curr != null){
            if(curr.getData() == item)
                return index;

            curr = curr.getNext();
            index += 1;
        }
        return -1;
    }

    // Get LinkedList as String
    public String getListAsString(){
        StringBuilder msg = new StringBuilder();
        msg.append("Head");
        ListNode curr = this.head;
        while(curr != null){
            msg.append(" --> ").append(curr.getData());
            curr = curr.getNext();
        }
        msg.append(" --> ").append("END");
        return msg.toString();
    }

    // Print LinkedList as String
    public void printList() {
        System.out.println(this.getListAsString());
        System.out.println("Length of LinkedList: " + this.length);
    }

}