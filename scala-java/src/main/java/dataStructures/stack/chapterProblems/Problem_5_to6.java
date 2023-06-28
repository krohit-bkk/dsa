package dataStructures.stack.chapterProblems;

import java.util.Stack;

import static dataStructures.stack.StackHelperUtils.printStack;

public class Problem_5_to6 {

    public static void main(String[] args) {
        // Problem 5: Brute force implementation
        // Get MinStack && MaxStack for a given array
        // No optimization
        // - size(original stack) = size(min/max stack)
        int size = 10;
        demonstrateMinMaxStackOfSize(size);


        // Problem 5: Brute force implementation
        // Some degree of space optimization
        // Do not repeat if min/max is not changing
        System.out.println();
        demonstrateOptimizedMinMaxStackOfSize(size);
    }

    // Problem 6: Brute force implementation
    // Some degree of space optimization
    // Do not repeat if min/max is not changing
    private static void demonstrateOptimizedMinMaxStackOfSize(int n) {
        // Create stack of random numbers of size n
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> minStack = new Stack<Integer>();
        Stack<Integer> maxStack = new Stack<Integer>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

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
                if(data < min) {
                    min = data;
                    minStack.push(min);
                }
            }

            // building max stack
            if(maxStack.isEmpty()) {
                maxStack.push(data);
                max = data;
            }
            else{
                if(data > max) {
                    max = data;
                    maxStack.push(max);
                }
            }
        }
        printStack(stack);
        printStack(minStack);
        printStack(maxStack);
    }

    // Print stack


    // Problem 5: Brute force implementation
    // Get MinStack && MaxStack for a given array
    private static void demonstrateMinMaxStackOfSize(int n) {
        // Create stack of random numbers of size n
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> minStack = new Stack<Integer>();
        Stack<Integer> maxStack = new Stack<Integer>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

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
        printStack(stack);
        printStack(minStack);
        printStack(maxStack);
    }
}
