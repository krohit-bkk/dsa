package dataStructures.stack.chapterProblems;

import dataStructures.stack.Stack;

public class Problem_11 {
    // Helper method
    public static Stack reverseStackLogic(Stack stack, Stack newStack) {
        if(!stack.isEmpty()){
            newStack.push(stack.pop());
            reverseStackLogic(stack, newStack);
        }
        return newStack;
    }

    // If stack is not empty, reverse it
    public static Stack reverseStack(Stack stack) {
        Stack stack1 = null;
        if(!stack.isEmpty()){
            stack1 = reverseStackLogic(stack, new Stack());
        }
        return stack1;
    }

    // Problem 11: Reversing a stack using only stack operation
    public static void main(String[] args) {
        Stack stack = new Stack();
        for(int i = 0; i < 5; i++){
            stack.push(i);
        }
        // Original stack
        System.out.println(">>>> Original stack....");
        stack.printStack();
        System.out.println(">>>> Reversed stack....");
        stack = reverseStack(stack);
        stack.printStack();
    }
}
