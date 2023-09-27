package dataStructures.queue.chapterProblems;

import dataStructures.queue.ListNode;
import dataStructures.queue.demo.Queue;

public class Problem_1 {
    public static void main(String[] args) {
        // Problem 1: Give an algorithm for reversing a queue Q. To access the queue, we are only
        // allowed to use the methods of queue ADT

        Queue queue = new Queue();
        int[] arr = new int[]{10,20,30};
        queue.enqueueAll(arr);
        queue.printQueue();
        queue = problem1_reverseQueue(queue);
        queue.printQueue();
    }

    // Recursive algorithm
    private static Queue problem1_reverseQueue(Queue queue) {
        if(!queue.isEmpty()){
            ListNode curr = new ListNode(queue.dequeue()); // dequeue head - front
            queue = problem1_reverseQueue(queue);          // reverse tail - remaining queue
            queue.enqueueNode(curr);                       // enqueue head in tail
            return queue;
        }
        return queue;                                      // edge case
    }
}
