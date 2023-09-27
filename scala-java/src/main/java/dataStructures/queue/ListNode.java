package dataStructures.queue;

public class ListNode {
    private ListNode next, prev;
    private Integer data;

    public ListNode(int data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode getPrev() {
        return prev;
    }

    public void setPrev(ListNode prev) {
        this.prev = prev;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
