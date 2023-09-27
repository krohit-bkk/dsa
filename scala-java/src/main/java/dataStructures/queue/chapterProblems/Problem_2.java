package dataStructures.queue.chapterProblems;

import dataStructures.stack.Stack;

public class Problem_2 {
    public static class StackWalaQueue{
        public Stack s1, s2;

        public StackWalaQueue(){
            this.s1 = new Stack();
            this.s2 = new Stack();
        }

        public void enqueue(int data){
            this.s1.push(data);
        }
        
        public Integer dequeue(){
            if(this.s1.isEmpty() && this.s2.isEmpty()){
                System.out.println("ERROR: Queue is empty");
                return null;
            }
            else{
                if(!this.s2.isEmpty())
                    return this.s2.pop();

                while(!this.s1.isEmpty())
                    this.s2.push(this.s1.pop());
                return this.s2.pop();
            }
        }

        public Boolean isEmpty(){
            return this.s1.isEmpty() && this.s2.isEmpty();
        }

    }
    public static void main(String[] args) {
        // Problem 2: How can you implement a queue using two stacks?

        // Some insertions
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
        StackWalaQueue queue = new StackWalaQueue();
        for(int num: arr)
            queue.enqueue(num);

        // A bit of deletion
        int counter = 0;
        while(!queue.isEmpty() && counter < 5) {
            System.out.println("Item popped: " + queue.dequeue());
            counter++;
        }

        // Insertion again
        queue.enqueue(10);
        queue.enqueue(20);

        // Verify that the orders remains unchanged
        while(!queue.isEmpty())
            System.out.println("Item popped: " + queue.dequeue());

        // Should print error message
        queue.dequeue();
    }
}
