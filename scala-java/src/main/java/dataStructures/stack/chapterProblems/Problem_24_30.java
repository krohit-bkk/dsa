package dataStructures.stack.chapterProblems;

import java.util.Arrays;

public class Problem_24_30 {
    public static void main(String[] args) {
        // Problem 25: Find the largest rectangle area under histogram
        // Given the array of heights, find the area of the rectangle formed by
        // using array values as height and distance between value indices as base
        int[] arr = new int[]{3,2,5,6,1,4,4};
        System.out.println("Greatest area under rectangle: " + problem_26_findArea(arr));
    }

    // Problem 25: Find the largest rectangle area under histogram
    // Brute force approach.
    // Use each index value as height and create rectangle by comparing it against
    // the other index values while keeping a track on the max area registered so far
    // Time  complexity: O[N^2]
    // Space complexity: O[1]
    private static int problem_26_findArea(int[] arr) {
        if(arr == null)
            return 0;

        int area = 0;
        int[] kindOfSpan = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            kindOfSpan[i] = 1;
            int counter = 1;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] >= arr[i])
                    counter += 1;
                break;
            }
            kindOfSpan[i] = counter;
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(kindOfSpan));
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i] * kindOfSpan[i];
            if(temp > area)
                area = temp;
        }
        return area;
    }
}
