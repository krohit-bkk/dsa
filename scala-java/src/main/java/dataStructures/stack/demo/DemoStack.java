package dataStructures.stack.demo;

import dataStructures.stack.Stack;

public class DemoStack {
    public static void main(String[] args) {
        Stack s1 = new Stack();
        System.out.println(">>>> Pushing elements in the Stack...");
        for(int i = 0; i < 5; i++){
            s1.push(i);
            s1.printStack();
        }

        System.out.println(">>>> Popping elements from the Stack...");
        while(!s1.isEmpty()){
            s1.pop();
            s1.printStack();
        }
    }
}
