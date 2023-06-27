package dataStructures.stack.chapterProblems;

import java.util.Stack;

public class Problem_5_to6 {

    public static void main(String[] args) {
        // Get MinStack && MaxStack for a given array
        // Problem 5: Brute force implementation
        // No optimization
        // - size(original stack) = size(min/max stack)
        int size = 3;
        demonstrateMinMaxStackOfSize(size);

        // Problem 5: Brute force implementation
        // Some degree of space optimization

    }

    // Print stack
    public static void printStack(Stack s){
        Stack stack1 = (Stack) s.clone();
        StringBuilder s1 = new StringBuilder("HEAD");
        while(!stack1.isEmpty()){
            s1.append(" <-- ").append(stack1.pop());
        }
        s1.append(" <-- END");
        System.out.println(s1);
    }

    private static void demonstrateMinMaxStackOfSize(int n) {
        // Create stack of random numbers of size n
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> minStack = new Stack<Integer>();
        Stack<Integer> maxStack = new Stack<Integer>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        StringBuilder msg = new StringBuilder("ORIG_SEQUENCE: ");

        for(int i =0; i < n; i++){
            // Get random number between 1 and 100
            int data = (int)Math.floor(Math.random() * (100 - 1 + 1) + 1);
            stack.push(data);
            // Building min stack
            if(minStack.isEmpty()) {
                minStack.push(data);
                min = data;
            }
            else{
                if(data < min)
                    min = data;
                minStack.push(min);
            }

            // building max stack
            if(maxStack.isEmpty()) {
                maxStack.push(data);
                max = data;
            }
            else{
                if(data > max)
                    max = data;
                maxStack.push(max);
            }
        }
        System.out.println(msg);
        printStack(stack);
        printStack(minStack);
        printStack(maxStack);
    }
}
