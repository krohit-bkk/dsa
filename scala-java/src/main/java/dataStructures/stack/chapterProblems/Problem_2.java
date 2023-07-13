package dataStructures.stack.chapterProblems;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Problem_2 {

    public static void main(String[] args) {
        // Problem 2: Infix to postfix expression conversion using stack
        List<String> infixArr = Arrays.asList(
                "A+B",
                "A+(B-C)",
                "(A+B)+(C-D)"
        );
        for (int i = 0; i < infixArr.size(); i++) {
            System.out.println(
                "Infix: " + infixArr.get(i)
                + "\tPostfix: " + infixToPostfix(infixArr.get(i))
            );
        }
    }

    // Function that returns precedence of a given operator
    static int prec(char ch){
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    private static String infixToPostfix(String str) {
        StringBuilder expression = new StringBuilder();
        Stack<Character> stack = new Stack<Character>();
        List<Character> operators = Arrays.asList('/', '*', '+', '-');

        // Assumption: The infix expression is a valid-balanced expression
        // Algorithm: Iterate over the expression
        // If c is opening parenthesis ['('] -> Push it in stack
        // If c is closing parenthesis [')'] -> Pop stack until opening parenthesis ['('] is found
        // If c is an operand -> Print it
        // If c is an operator ->
        //      - If stack is empty -> Push it to stack
        //      - Else -> Compare it with the top element of stack
        //          - If element at top < c -> push the element
        //          - Else -> keep popping and printing until lower precedence operator is found
        // If expression is fully traversed & stack not empty -> keep popping & printing elements from stack

        // Point to note is the operators in the stack would have precedence in decreasing order
        // Means, the operator precedence would be : top > top-1 > top-2 > ..
        for (Character c : str.toCharArray()) {
            // Case 1: c is opening parenthesis ['('] -> push to stack
            if(c == '('){
                // System.out.println("1. Pushing to stack: " + c);
                stack.push(c);
            }
            // Case 2: c is closing parenthesis [')'] -> pop until ['('] is popped
            else if(c == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    Character elem = stack.pop();
                    // System.out.println("2. " + c + " found: Popping from stack: " + elem);
                    expression.append(elem);
                }
                if(!stack.isEmpty() && stack.peek() == '('){
                    Character elem = stack.pop();
                    // System.out.println("2. Popping from stack '(': " + elem);
                }
            }
            // Case 3: If c is an operand -> Print to output expression
            else if(!operators.contains(c)){
                // System.out.println("3. Printing operand: " + c);
                expression.append(c);
            }
            // Case 4: If operator is found
            else if(operators.contains(c)){
                // System.out.println("4. Operator found: " + c);
                // Case 4.1: If stack is empty -> push operator to stack
                if(stack.isEmpty()){
                    // System.out.println("4.1 Pushing operator to stack: " + c);
                    stack.push(c);
                }
                else{
                    // Case 4.2: If operator at top > c, pop the operator, else push
                    while(prec(stack.peek()) > prec(c)){
                        Character elem = stack.pop();
                        // System.out.println("4.2.1 Popping this operator from stack: " + elem);
                        expression.append(elem);
                    }
                    // System.out.println("4.2.2 Pushing this operator in stack: " + c);
                    stack.push(c);
                }
            }
        }
        while(!stack.isEmpty())
            if(stack.peek() != '(')
                expression.append(stack.pop());

        return expression.toString();
    }
}
