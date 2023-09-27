package dataStructures.stack.demo;

import dataStructures.stack.Stack;

import java.util.Arrays;
import java.util.function.Function;

public class ReverseStack {

    public static void main(String[] args) {
        Stack s1 = new Stack();
        int[] arr = new int[]{6, 3, 4, 5, 2, 1, 5, 0};

        for(int i : arr)
            s1.push(i);
        // s1.printStack();
        // printArray(arr);
        /*
        // Nearest Greater Left
        System.out.println("greater left...");
        int[] greaterLeft = nearestGreaterLeft(arr);
        printArray(greaterLeft);

        // Nearest Greater Right
        System.out.println("greater right...");
        int[] greaterRight = nearestGreaterRight(arr);
        printArray(greaterRight);

        // Nearest Smaller Left
        System.out.println("smaller left...");
        int[] smallerLeft = nearestSmallerLeft(arr);
        printArray(smallerLeft);
        // Nearest Smaller Right
        System.out.println("smaller right...");
        int[] smallerRight = nearestSmallerRight(arr);
        printArray(smallerRight);

        // Span unoptimized
        System.out.println("Printing spans...");
        printArray(spanUnoptimized(arr));

        // Spans optimized
        System.out.print("\nOriginal ");
        printArray(arr);
        System.out.println("Printing spans optimized...");
        printArray(spanOptimized(arr));

        */

        // Min/Max Stack
        arr = new int[]{2,1,4,1,5,3,2,7,4};
        // Create stack using array
        Stack stack = new Stack();
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
        }
        System.out.println("MinStack...");
        stack.printStack();
        minStackAlgo(stack).printStack();
        System.out.println("MaxStack...");
        stack.printStack();
        maxStackAlgo(stack).printStack();
        stack.printStack();
    }

    private static Stack process2(Stack stack, Stack maxStack) {
        if(!(stack == null || stack.isEmpty())){
            int curr = stack.pop();
            maxStack = process2(stack, maxStack);

            if(maxStack == null || maxStack.isEmpty()) {
                maxStack = new Stack();
                maxStack.push(curr);
            }
            else
                maxStack.push(Math.max(curr, maxStack.peek()));
            stack.push(curr);
        }
        return maxStack;
    }

    private static Stack maxStackAlgo(Stack stack) {
        return process2(stack, null);
    }

    private static Stack process1(Stack stack, Stack minStack) {
        if(!stack.isEmpty()){
            int curr = stack.pop();
            minStack = process1(stack, minStack);
            if(minStack == null || minStack.isEmpty()) {
                minStack = new Stack();
                minStack.push(curr);
            }
            else
                minStack.push(Math.min(curr, minStack.peek()));
            stack.push(curr);
        }
        return minStack;
    }

    private static Stack minStackAlgo(Stack stack) {
        return process1(stack, null);
    }

    public static Stack logic(Stack stack, Stack newStack){
        while(!stack.isEmpty()){
            int temp = stack.pop();
            if(newStack == null)
                newStack = new Stack();
            newStack.push(temp);
            logic(stack, newStack);
        }
        return newStack;
    }

    public static int[] spanUnoptimized(int[] arr){
        int[] spans = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            int counter = 1;
            for(int j = i-1; j >= 0; j--){
                if(arr[j] <= arr[i])
                    counter += 1;
                else
                    break;
            }
            spans[i] = counter;
        }
        return spans;
    }

    public static int[] spanOptimized(int[] arr){
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
                    // System.out.println("Stack at arr[" + i + "] : [" + arr[i] + "]: " + s1.getStackAsString());
                    // System.out.println("Stack at arr[" + i + "] : [" + arr[i] + "]: " + s2.getStackAsString() + "\n");
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

    public static Stack func(Stack stack){
        return logic(stack, new Stack());
    }

    public static void printArray(int[] arr){
        if(arr != null){
            StringBuilder msg = new StringBuilder("Array: [");
            for(int i = 0; i < arr.length; i++) {
                int temp = arr[i];
                if(i < arr.length - 1)
                    msg.append(temp + ", ");
                else
                    msg.append(temp + "]");
            }
            System.out.println(msg.toString());
        }
        else
            System.out.println("[NULL ARRAY]");
    }

    private static int[] nearestSmallerLeft(int[] arr1) {
        // Brute force
        Function<int[], int[]> unoptimized = arr -> {
            int[] result = new int[arr.length];
            Arrays.fill(result, -1);
            for(int i = 0; i < arr.length; i++){
                for(int j = i-1; j >= 0; j--){
                    if(j < 0)
                        result[i] = -1;
                    else if(arr[j] < arr[i]){
                        result[i] = arr[j];
                        break;
                    }
                }
            }
            return result;
        };

        // Using stack
        Function<int[], int[]> optimized = arr -> {
            Stack s = new Stack();
            int[] result = new int[arr.length];
            Arrays.fill(result, -1);

            for(int i = 0; i < arr.length; i++){
                if(s.isEmpty()){
                    result[i] = -1;
                    s.push(arr[i]);
                }
                else{
                    int temp = -1;
                    while((!s.isEmpty()) && s.peek() < arr[i])
                        temp = s.pop();

                    if(s.isEmpty())
                        result[i] = -1;
                    else
                        result[i] = temp;
                    s.push(arr[i]);
                }
            }

            return result;
        };

        if(arr1 != null){
            // return unoptimized.apply(arr1);
            return optimized.apply(arr1);
        }
        return null;
    }

    private static int[] nearestSmallerRight(int[] arr1) {
        // Brute force - looping twice
        // Time  complexity: O[N^2]
        // Space complexity: O[1]
        Function<int[], int[]> unoptimized = arr -> {
            int[] result = new int[arr.length];
            Arrays.fill(result, -1);
            for(int i = arr.length - 1; i >= 0; i--){
                for(int j = i+1; j < arr.length; j++){
                    if(j > arr.length - 1)
                        result[i] = -1;
                    else if(arr[j] < arr[i]){
                        result[i] = arr[j];
                        break;
                    }
                }
            }
            return result;
        };

        // Optimized approach - using Stack
        // Time  complexity: O[N]
        // Space complexity: O[N]
        Function<int[], int[]> optimized = arr -> {
            Stack s = new Stack();
            int[] res = new int[arr.length];
            Arrays.fill(res, -1);

            for(int i = arr.length - 1; i >= 0; i--){
                if(s.isEmpty()){
                    s.push(arr[i]);
                    res[i] = -1;
                }
                else{
                    while(!(s.isEmpty()) && s.peek() > arr[i])
                        s.pop();

                    if(s.isEmpty())
                        res[i] = -1;
                    else
                        res[i] = s.peek();
                    s.push(arr[i]);
                }
            }
            return res;
        };

        if(arr1 != null){
            // return unoptimized.apply(arr1);
            return optimized.apply(arr1);
        }
        return null;
    }

    private static int[] nearestGreaterLeft(int[] arr1) {
        // Brute force - looping twice
        // Time  complexity: O[N^2]
        // Space complexity: O[1]
        Function<int[], int[]> unoptimized = arr -> {
            int[] res = new int[arr.length];
            Arrays.fill(res, -1);

            for(int i = 0; i < arr.length; i++){
                for(int j = i-1; j >= 0; j--){
                    if(arr[j] > arr[i]){
                        res[i] = arr[j];
                        break;
                    }
                }
            }
            return res;
        };

        // Optimized approach - using Stack
        // Time  complexity: O[N]
        // Space complexity: O[N]
        Function<int[], int[]> optimized = arr -> {
            int[] res = new int[arr.length];
            Arrays.fill(res, -1);
            Stack s = new Stack();
            for(int i = 0; i < arr.length; i++){
                if(s.isEmpty()){
                    res[i] = -1;
                    s.push(arr[i]);
                }
                else{
                    while(!(s.isEmpty()) && s.peek() <= arr[i])
                        s.pop();

                    if(s.isEmpty())
                        res[i] = -1;
                    else
                        res[i] = s.peek();
                    s.push(arr[i]);
                }
            }
            return res;
        };

        if(arr1 != null){
            // return unoptimized.apply(arr1);
            return optimized.apply(arr1);
        }
        return null;
    }

    private static int[] nearestGreaterRight(int[] arr1) {
        // Brute force - looping twice
        // Time  complexity: O[N^2]
        // Space complexity: O[1]
        Function<int[], int[]> unoptimized = arr -> {
            int[] res = new int[arr.length];
            Arrays.fill(res, -1);

            for(int i = 0; i < arr.length; i++){
                for(int j = i+1; j < arr.length; j++){
                    if(arr[j] > arr[i]){
                        res[i] = arr[j];
                        break;
                    }
                }
            }
            return res;
        };

        // Optimized approach - using Stack
        // Time  complexity: O[N]
        // Space complexity: O[N]
        Function<int[], int[]> optimized = arr -> {
            int[] res = new int[arr.length];
            Arrays.fill(res, -1);
            Stack s = new Stack();

            for(int i = arr.length-1; i >= 0; i--){
                if(s.isEmpty()){
                    res[i] = -1;
                    s.push(arr[i]);
                }
                else{
                    while(!(s.isEmpty()) && s.peek() <= arr[i])
                        s.pop();

                    if(s.isEmpty())
                        res[i] = -1;
                    else
                        res[i] = s.peek();
                    s.push(arr[i]);
                }
            }
            return res;
        };

        if(arr1 != null){
            // return unoptimized.apply(arr1);
            printArray(unoptimized.apply(arr1));
            return optimized.apply(arr1);
        }
        return null;
    }


}
