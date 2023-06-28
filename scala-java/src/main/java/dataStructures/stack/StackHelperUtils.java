package dataStructures.stack;

import java.util.Stack;

public class StackHelperUtils {
    public static void printStack(java.util.Stack s){
        java.util.Stack stack1 = (Stack) s.clone();
        StringBuilder s1 = new StringBuilder("HEAD");
        while(!stack1.isEmpty()){
            s1.append(" <-- ").append(stack1.pop());
        }
        s1.append(" <-- END");
        System.out.println(s1);
    }
}
