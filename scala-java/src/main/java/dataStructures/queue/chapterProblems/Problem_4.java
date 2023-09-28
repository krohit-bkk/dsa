package dataStructures.queue.chapterProblems;

import java.util.ArrayDeque;
import java.util.Deque;

import static utils.ArrayUtils.printArray;

public class Problem_4 {
    public static void main(String[] args) {
        // Problem 4: Maximum in sliding window: Given array A[] with sliding window of size w
        // which is moving from the very left of the array lo the very right.
        // Assume that we can only see the w numbers in the window.
        // Each time the sliding window moves rightwards by one position.
        // For example: The array is [1, 3, -1, -3, 5, 3, 6, 7], and w is 3.
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 4;
        int[] res1 = problem4_maxSumSlidingWindow(arr, k);  // using built-in deque
        int[] res2 = problem4_maxSumSlidingWindow1(arr, k); // using apna deque
        printArray(arr);
        printArray(res1);
        printArray(res2);
    }

    // Problem 4: Max in sliding window of size k
    // Deque approach - Double ended queue
    // For a given window, the aim is to have the largest number at the front
    // and the subsequent insertions happen at the rear.
    // Time  complexity: O[N]
    // Space complexity: O[N]
    private static int[] problem4_maxSumSlidingWindow(int[] arr, int w) {
        if(arr == null)
            return null;

        int[] res = new int[arr.length - w + 1];
        Deque<Integer> deque = new ArrayDeque();

        // First window
        int i = 0;
        for(i = 0; i < w; i++) {
            if(deque.isEmpty())
                deque.addFirst(arr[i]);
            else{
                while(!deque.isEmpty() && deque.getLast() < arr[i])
                    deque.removeLast();
                deque.addLast(arr[i]);
            }
        }
        res[0] = deque.getFirst();

        // Subsequent windows
        for(; i < arr.length; i++){
            if(deque.isEmpty())
                deque.addFirst(arr[i]);
            else{
                while(!deque.isEmpty() && deque.peekLast() < arr[i])
                    deque.removeLast();

                deque.addLast(arr[i]);
                res[i-w+1] = deque.peekFirst();
            }
        }
        return res;
    }

    private static int[] problem4_maxSumSlidingWindow1(int[] arr, int w) {
        if(arr == null)
            return null;

        int res[] = new int[arr.length - w + 1];

        // Our implementation of deque
        dataStructures.queue.demo.Deque deque = new dataStructures.queue.demo.Deque();

        // First window
        int i = 0;
        for(i = 0; i < w; i++) {
            if(deque.isEmpty())
                deque.addFirst(arr[i]);
            else{
                while(!deque.isEmpty() && deque.peekLast() < arr[i]) {
                    deque.removeLast();
                }
                deque.addLast(arr[i]);
            }
        }
        res[0] = deque.peekFirst();

        // Subsequent windows
        for(; i < arr.length; i++){
            if(deque.isEmpty())
                deque.addFirst(arr[i]);
            else{
                while(!deque.isEmpty() && deque.peekLast() < arr[i]) {
                    deque.removeLast();
                }
                deque.addLast(arr[i]);
                res[i-w+1] = deque.peekFirst();
            }
        }
        return res;
    }
}
