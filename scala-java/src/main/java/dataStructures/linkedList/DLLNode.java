package dataStructures.linkedList;

public class DLLNode {
    private int data;
    private DLLNode prev;
    private DLLNode next;

    // Default constructor
    public DLLNode(int data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    // Auxiliary constructor
    public DLLNode(int data, DLLNode next, DLLNode prev){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    // Getters-Setters below
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public DLLNode getPrev() {
        return prev;
    }

    public void setPrev(DLLNode prev) {
        this.prev = prev;
    }

    public DLLNode getNext() {
        return next;
    }

    public void setNext(DLLNode next) {
        this.next = next;
    }
}
