package dataStructures.stack.demo;

import dataStructures.stack.Stack;

import java.util.Arrays;
import java.util.function.Function;

public class PrevBiggerNextBigger {
    public static void main(String[] args) {
        // Problem - previous bigger
        // For a given array arr, return an array such that the value at a given index (i)
        // in the new array stores the first value (from left) in original array which is bigger than arr[i]
        int[] arr = new int[]{4, 10, 5, 8, 20, 15, 3, 12};
        int[] prevBigger = prevBigger(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(prevBigger));

        // Problem - next bigger
        // For a given array arr, return an array such that the value at a given index (i)
        // in the new array stores the first value (towards right) in original array which is bigger than arr[i]
        int[] nextBigger = nextBigger(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(nextBigger));
    }

    private static int[] prevBigger(int[] arr1) {
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

    private static int[] nextBigger(int[] arr1) {
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
            return optimized.apply(arr1);
        }
        return null;
    }
}

