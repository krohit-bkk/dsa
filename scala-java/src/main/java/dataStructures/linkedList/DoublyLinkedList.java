package dataStructures.linkedList;

public class DoublyLinkedList {
    private int length;
    private DLLNode head;   // Points to the start of DLL
    private DLLNode tail;   // Points to the end of the DLL

    // Default constructor
    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    // Check id DoublyLinkedList is empty
    public boolean isEmpty(){
        return this.head == null;
    }

    // Insert an element at the beginning of DoublyLinkedList
    public void push(int data){
        DLLNode node = new DLLNode(data);
        // If DoublyLinkedList is empty
        if(this.head == null){
            this.head = node;
            this.tail = node;
        }
        else{
            node.setNext(this.head);
            this.head.setPrev(node);
            this.head = node;
        }
        this.length += 1;
    }

    // Insert an element at the end of the DoublyLinkedList
    public void append(int data){
        DLLNode node = new DLLNode(data);
        // If DoublyLinkedList is empty
        if(this.head == null){
            this.head = node;
            this.tail = node;
        }
        else{
            DLLNode curr = this.head;
            // Iterate to the end of the DLL
            while(curr.getNext() != null)
                curr = curr.getNext();
            // Make last node point to new node
            curr.setNext(node);
            // Make new node point to previous last node
            node.setPrev(curr);
            this.tail = node;
        }
        this.length += 1;
    }

    // Deleting the first element of DoublyLinkedList
    public void removeFirst(){
        // No elements in DLL
        if(this.head == null) {
            System.out.println("ERROR: Empty list! Nothing to delete.");
        }
        // 1 element in DLL
        else if(this.head == this.tail){
            this.head = null;
            this.tail = null;
            this.length -= 1;
        }
        // More than one element in DLL
        else{
            this.head.getNext().setPrev(null);
            this.head = this.head.getNext();
            this.length -= 1;
        }
    }

    // Deleting the last element of DoublyLinkedList
    public void removeLast(){
        // If no elements in DLL
        if(this.isEmpty()){
            System.out.println("ERROR: Empty list! Nothing to delete...");
        }
        // Only 1 element in DLL
        else if(this.head == this.tail){
            this.head = null;
            this.tail = null;
            this.length -= 1;
        }
        else{
            DLLNode curr = this.head;
            // Reach the second last node
            while(curr.getNext().getNext() != null)
                curr = curr.getNext();
            curr.setNext(null);
            this.tail = curr;
            this.length -= 1;
        }
    }

    public void removeAt(int index){
        int currIndex = 0;
        DLLNode curr = this.head;

        // Case 1: index == 0 -> Removal of first element
        if(index == 0){
            this.removeFirst();
            return;
        }

        // Iterate to i-th node
        while(curr != null && currIndex < index){
            curr = curr.getNext();
            currIndex += 1;
        }
        // Two possibilities now
        // Case 1: currIndex == index -> Index lies in between DLL
        // Case 2: curr == null -> currIndex < index -> DLL is shorter than index requested
        if(currIndex == index){
            DLLNode left = curr.getPrev();
            DLLNode right = curr.getNext();
            // Case 1.1: Index lies in the middle of the DLL
            if(right != null){
                left.setNext(right);
                right.setPrev(left);
                this.length -= 1;
            }
            else{
                // Case 1.2: Index points to the last element of DLL
                left.setNext(null);
                this.tail = left;
                this.length -= 1;
            }
        }
        else if(curr == null){
            // Case 2: curr == null -> currIndex < index
            System.out.println("ERROR: Invalid index! DoublyLinkedList is shorter (size[" +
                    this.length + "]) than index [" +
                    index + "] requested!");
        }
    }

    // Returns String representation of the DoublyLinkedList
    public String getListAsString(){
        StringBuilder msg = new StringBuilder("HEAD");
        DLLNode curr = this.head;
        while(curr != null){
            msg.append(" <--> ").append(curr.getData());
            curr = curr.getNext();
        }
        msg.append(" <--> TAIL");
        return msg.toString();
    }

    // Print the DoublyLinkedList as String
    public void printList() {
        System.out.println(this.getListAsString());
        System.out.println("Current length of DoublyLinkedList is: " + this.length);
    }

    // To be deleted...
    // For testing purpose only
    public void printHeadTail(){
        if(this.head != null) {
            System.out.println("Current head: [" + this.head.getData()
                    + "], Current tail: [" + this.tail.getData() + "]."
            );
        }
        else{
            System.out.println("ERROR: Empty list. No HEAD/TAIL");
        }
    }

    // Push all elements of array in DoublyLinkedList
    public void pushArray(int[] ints) {
        for(int item : ints)
            this.push(item);
    }

    // Append all elements of array in DoublyLinkedList
    public void appendArray(int[] ints) {
        for(int item : ints)
            this.append(item);
    }
}
