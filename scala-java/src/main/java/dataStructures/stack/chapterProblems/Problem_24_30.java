package dataStructures.stack.chapterProblems;

import java.util.function.Function;

import dataStructures.stack.Stack;
import static utils.ArrayUtils.printArray;

public class Problem_24_30 {
    public static void main(String[] args) {
        // Problem 24: Find the largest rectangle area under histogram
        // Given the array of heights, find the area of the rectangle formed by
        // using array values as height and distance between value indices as base
        // int[] arr = new int[]{4,2,1,5,6,3,2,4,2};
        int[] arr = new int[]{3,2,5,6,1,4,4};
        System.out.println("Problem 24: Greatest area under rectangle: " + problem_24_findArea(arr));
        System.out.println("Problem 25: Greatest area under rectangle: " + problem_25_findAreaOptimized(arr));
    }

    // Problem 24: Find the largest rectangle area under histogram
    // Brute force approach.
    // Use each index value as height and create rectangle by comparing it against
    // the other index values while keeping a track on the max area registered so far
    // Time  complexity: O[N^2]
    // Space complexity: O[1]
    private static int problem_24_findArea(int[] arr) {
        if(arr == null)
            return 0;

        int area = 0;

        for (int i = 0; i < arr.length; i++) {
            int left, right;
            int j = i;
            while(j >= 0 && arr[j] >= arr[i])
                j--;
            left = j;

            j = i;
            while(j < arr.length && arr[j] >= arr[i])
                j++;
            right = j;

            int curr = (right - left -1) * arr[i];
            if(curr > area)
                area = curr;
        }
        return area;
    }

    // Problem 25: Find the largest rectangle area under histogram
    // Stack based - previous/next smaller value for a given index
    // Use each index value as height and create rectangle by comparing it against
    // the other index values while keeping a track on the max area registered so far
    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static int problem_25_findAreaOptimized(int[] arr1) {
        if(arr1 == null)
            return 0;

        Function<int[], int[]> smallerLeft = arr -> {
            Stack stack = new Stack();
            int[] res = new int[arr.length];
            for(int i = 0; i < arr.length; i++){
                if(stack.isEmpty()){
                    stack.push(i);
                    res[i] = -1;
                }
                else{
                    while(! stack.isEmpty() && arr[stack.peek()] >= arr[i])
                        stack.pop();
                    if(stack.isEmpty())
                        res[i] = -1;
                    else
                        res[i] = stack.peek();
                    stack.push(i);
                }
            }
            return res;
        };

        Function<int[], int[]> smallerRight = arr -> {
            Stack stack = new Stack();
            int[] res = new int[arr.length];

            for (int i = arr.length-1; i >= 0 ; i--) {
                if(stack.isEmpty()){
                    res[i] = arr.length;
                    stack.push(i);
                }
                else{
                    while(!stack.isEmpty() && arr[stack.peek()] >= arr[i])
                        stack.pop();
                    if(stack.isEmpty())
                        res[i] = arr.length;
                    else
                        res[i] = stack.peek();
                    stack.push(i);
                }
            }
            return res;
        };

        int area = 0;
        int[] smallerLeftArr = smallerLeft.apply(arr1);
        int[] smallerRightArr = smallerRight.apply(arr1);

        // Verify the intermediate steps
        // printArray(arr1);
        // printArray(smallerLeftArr);
        // printArray(smallerRightArr);

        // Compute the area at a given index and see if it's max area
        for (int i = 0; i < arr1.length; i++) {
            int curr = (smallerRightArr[i] - smallerLeftArr[i] - 1) * arr1[i];
            if(area < curr)
                area = curr;
        }
        return area;
    }
}
