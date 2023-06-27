package dataStructures.stack;

public class Stack {
    private ListNode top;
    private Integer size;

    // Default constructor
    public Stack(){
        this.top = null;
        this.size = 0;
    }

    // Check if Stack is empty
    public boolean isEmpty(){
        return this.top == null;
    }

    // Push - an element at the top
    public void push(Integer data){
        ListNode node = new ListNode(data);
        if(this.isEmpty()) {
            this.top = node;
        }
        else{
            node.setNext(this.top);
            this.top = node;
        }
        this.size += 1;
    }

    // Peek - the element at the top
    public Integer peek(){
        if(!this.isEmpty())
            return this.top.getData();
        return null;
    }

    // Pop - the element at the top
    public Integer pop(){
        if(!isEmpty()) {
            ListNode node = this.top;
            this.top = this.top.getNext();
            return node.getData();
        }
        return null;
    }

    // Get Stack as String
    public String getStackAsString(){
        StringBuilder msg = new StringBuilder("TOP");
        ListNode curr = this.top;
        if(!this.isEmpty()){
            while(curr != null) {
                msg.append(" --> ").append(curr.getData());
                curr = curr.getNext();
            }
        }
        msg.append(" --> END");
        return msg.toString();
    }

    // Print Stack as String
    public void printStack(){
        System.out.println(getStackAsString());
        // System.out.println("Current size of Stack is: " + this.size);
    }
}
