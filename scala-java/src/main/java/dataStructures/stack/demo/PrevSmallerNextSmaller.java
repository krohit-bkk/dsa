package dataStructures.stack.demo;


import java.util.Arrays;
import java.util.Stack;

public class PrevSmallerNextSmaller {

    public static void main(String[] args) {
        // For a given array arr, return an array such that the value at a given index (i)
        // in the new array stores the first value (from left) in original array which is smaller than arr[i]
        int[] arr = new int[]{4, 10, 5, 8, 20, 15, 3, 12};
        int[] prevSmaller = prevSmaller(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(prevSmaller));
    }

    private static int[] prevSmaller(int[] arr) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] res = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int elem = arr[i];
            if(stack.isEmpty()){
                stack.push(elem);
                res[i] = -1;
            }
            else {
                while (!stack.isEmpty() && stack.peek() > elem)
                    stack.pop();

                if (stack.isEmpty()) {
                    res[i] = -1;
                    stack.push(elem);
                }
                else
                    res[i] = stack.peek();
            }
        }
        return res;
    }
}
