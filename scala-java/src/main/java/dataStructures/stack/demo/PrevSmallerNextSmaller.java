package dataStructures.stack.demo;


import dataStructures.stack.Stack;

import java.util.Arrays;
import java.util.function.Function;

public class PrevSmallerNextSmaller {

    public static void main(String[] args) {
        // Problem - previous smaller
        // For a given array arr, return an array such that the value at a given index (i)
        // in the new array stores the first value (from left) in original array which is smaller than arr[i]
        int[] arr = new int[]{4, 10, 5, 8, 20, 15, 3, 12};
        int[] prevSmaller = prevSmaller(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(prevSmaller));

        // Problem - next smaller
        // For a given array arr, return an array such that the value at a given index (i)
        // in the new array stores the first value (towards right) in original array which is smaller than arr[i]
        int[] nextSmaller = nextSmaller(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(nextSmaller));
    }

    private static int[] prevSmaller(int[] arr1) {
        // Brute force - looping twice
        // Time  complexity: O[N^2]
        // Space complexity: O[1]
        Function<int[], int[]> unoptimizedAlgo = arr -> {
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

        // Optimized approach - using Stack
        // Time  complexity: O[N]
        // Space complexity: O[N]
        Function<int[], int[]> optimizedAlgo = arr -> {
            dataStructures.stack.Stack s = new Stack();
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
            // return unoptimizedAlgo.apply(arr1);
            return optimizedAlgo.apply(arr1);
        }
        return null;
    }

    private static int[] nextSmaller(int[] arr1) {
        // Brute force - looping twice
        // Time  complexity: O[N^2]
        // Space complexity: O[1]
        Function<int[], int[]> unoptimizedAlgo = arr -> {
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
        Function<int[], int[]> optimizedAlgo = arr -> {
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
            // return unoptimizedAlgo.apply(arr1);
            return optimizedAlgo.apply(arr1);
        }
        return null;
    }
}
