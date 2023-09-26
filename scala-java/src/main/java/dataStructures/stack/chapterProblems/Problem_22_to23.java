package dataStructures.stack.chapterProblems;

import dataStructures.stack.Stack;

import java.util.Arrays;

import static utils.ArrayUtils.printArray;

public class Problem_22_to23 {

    public static void main(String[] args) {
        // Problem 22/23: Finding spans (of an array)
        // The span S[i] of A[i] is the maximum number of consecutive
        // elements A[j] immediately preceding A[i] such that A[j]<= A[i]
        int[] arr = new int[]{6,3,4,5,2};
        printArray(findingSpans(arr));
        printArray(findingSpansOptimized(arr));
    }

    // Problem 22: Finding span for the array
    // Brute force: For each element, go down the array to find which A[j] <= A[i]
    // Time  complexity: O[N^2]
    // Space complexity: O[1]
    private static int[] findingSpans(int[] arr) {
        int[] spans = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            int numSpans = 0;
            for(int j = i; j >= 0; j--){
                if(arr[j] <= arr[i])
                    numSpans += 1;
            }
            spans[i] = numSpans;
        }
        return spans;
    }

    // Problem 23: Finding span for the array
    // Optimized: Use stack to hold the already computed spans till index i.
    // For each element, check in the stack if last element (processed) was smaller or larger.
    // If smaller, add span of that number to current span, pop that number off stack (and continue comparison stack.top() <= A[i]), else stop.
    // Time  complexity: O[N]
    // Space complexity: O[N]
    public static int[] findingSpansOptimized(int[] arr){
        int[] spans = new int[arr.length];
        Arrays.fill(spans, 1);

        Stack s1 = new Stack(); // Holds previous bigger values
        Stack s2 = new Stack(); // Holds span of previous bigger values

        for (int i = 0; i < arr.length; i++) {
            if(s1.isEmpty()){
                s1.push(arr[i]);
                s2.push(1);
            }
            else{
                int span = 1;
                while(!s1.isEmpty() && s1.peek() <= arr[i]){
                    span += s2.peek();
                    s1.pop();
                    s2.pop();
                }
                s1.push(arr[i]);
                s2.push(span);
                spans[i] = span;
            }
        }
        return spans;
    }
}
