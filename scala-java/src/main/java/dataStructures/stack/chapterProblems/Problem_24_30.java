package dataStructures.stack.chapterProblems;

import java.util.Arrays;
import java.util.function.Function;

import dataStructures.stack.Stack;

import static utils.ArrayUtils.getArrayAsString;

public class Problem_24_30 {
    public static void main(String[] args) {
        // Problem 24: Find the largest rectangle area under histogram
        // Given the array of heights, find the area of the rectangle formed by
        // using array values as height and distance between value indices as base
        int[] arr = new int[]{3,2,5,6,1,4,4};
        System.out.println("Problem 24: Greatest area under rectangle: " + problem_24_findArea(arr));

        // Problem 25: For Problem-24, can we improve the time complexity?
        System.out.println("Problem 25: Greatest area under rectangle: " + problem_25_findAreaOptimized(arr));

        // Problem 27: Recursively remove all adjacent duplicates.
        // Given a string of characters, recursively remove adjacent duplicate characters from string.
        // The output string should not have any adjacent duplicates
        String[] strs = new String[]{"careermonk", "mississippi"};
        for(String str : strs)
            System.out.println("\nProblem 27: Input string ["
                    + str
                    + "],\nProblem 27: Output string: ["
                    + problem_27_removeAdjacentDuplicates(str)
                    + "]"
            );

        // Problem 28: Given an array of elements, replace every element with nearest greater element
        // on the right of that element.
        // Brute force approach
        // Time  complexity: O[N^2]
        // Space  complexity: O[1]
        int[] problem28 = problem_28_nearestGreaterTowardsRight(arr);
        System.out.println("\nProblem 28: Nearest greater towards right: "
                + "\n" + getArrayAsString(arr)
                + "\n" + getArrayAsString(problem28)
        );

        // Problem 29: For Problem-28, cun we improve the complexity?
        // Using stacks
        // Time  complexity: O[N]
        // Space complexity: O[N]
        int[] problem29 = problem_29_nearestGreaterTowardsRight(arr);
        System.out.println("\nProblem 29: Nearest greater towards right (using stacks): "
                + "\n" + getArrayAsString(arr)
                + "\n" + getArrayAsString(problem29)
        );
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

    // Problem 27: Recursively remove all adjacent duplicates.
    // Given a string of characters, recursively remove adjacent duplicate characters from string.
    // The output string should not have any adjacent duplicates
    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static String problem_27_removeAdjacentDuplicates(String str) {
        if(str == null)
            return null;
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for(Character c : str.toCharArray()){
            if(stack.isEmpty())
                stack.push(c);
            else{
                if(!stack.isEmpty() && stack.peek() != c)
                    stack.push(c);
                else
                    stack.pop();
            }
        }
        // Reverse the stck and get the output string
        if(!stack.isEmpty()){
            String output = "";
            while(!stack.isEmpty()){
                output = stack.pop() + output;
            }
            return output;
        }
        return null;
    }

    // Problem 28: Given an array of elements, replace every element with nearest greater element
    // on the right of that element.
    // Brute force approach
    // Time  complexity: O[N^2]
    // Space  complexity: O[1]
    private static int[] problem_28_nearestGreaterTowardsRight(int[] arr) {
        if(arr == null)
            return null;

        int[] res = new int[arr.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] > arr[i]) {
                    res[i] = arr[j];
                    break;
                }
            }
        }
        return res;
    }

    // Problem 29: For Problem-28, cun we improve the complexity?
    // Given an array of elements, replace every element with nearest greater element
    // on the right of that element.
    // Optimal solution using stacks
    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static int[] problem_29_nearestGreaterTowardsRight(int[] arr) {
        if(arr == null)
            return null;

        Stack stack = new Stack();
        int[] res = new int[arr.length];
        Arrays.fill(res, -1);

        for (int i = arr.length-1; i >= 0; i--) {
            if(stack.isEmpty()){
                stack.push(arr[i]);
                res[i] = -1;
            }
            else{
                while(!stack.isEmpty() && stack.peek() <= arr[i])
                    stack.pop();
                if(!stack.isEmpty())
                    res[i] = stack.peek();
                else
                    res[i] = -1;
                stack.push(arr[i]);
            }
        }
        return res;
    }

}
