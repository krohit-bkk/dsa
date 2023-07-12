package dataStructures.stack.chapterProblems;

import java.util.Stack;

public class Problem_1 {

    public static void main(String[] args) {
        // Discuss how stacks can be used for
        // checking balancing of symbols
        String[] strings = new String[4];
        strings[0] = "(A+B)+(C-D)";
        strings[1] = "((A+B)+(C-D)";
        strings[2] = "((A+B)+[C-D])";
        strings[3] = "((A+B)+[C-D]}";

        boolean[] expectedOutputs = {true, false, true, false};
        boolean[] outputs = new boolean[4];

        for(int i = 0; i < 4; i++){
            outputs[i] = checkSymbolBalancing(strings[i]);
            System.out.println(expectedOutputs[i] + " <-- expected | actual --> " + outputs[i]);
        }
    }

    // Problem 1: Discuss how stacks can be used for
    // checking balancing of symbols
    private static boolean checkSymbolBalancing(String string) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else if (c == ')' || c == ']' || c == '}') {
                char lastPop = stack.pop();
                if(!(
                        (lastPop == '(' && c == ')') ||
                        (lastPop == '{' && c == '}') ||
                        (lastPop == '[' && c == ']')
                ))
                    return false;
            }
        }

        return stack.isEmpty();
    }
}
