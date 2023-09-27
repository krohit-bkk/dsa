package dataStructures.queue.demo;

public class QueueDemo {
    public static void main(String[] args) {
        Queue queue = new Queue();
        // Enqueue Op
        for (int i = 0; i < 3; i++) {
            queue.enqueueNode(i);
            queue.printQueue();
        }

        // EnqueueAll Op
        int[] arr = new int[]{10,20,30};
        queue.enqueueAll(arr);
        queue.printQueue();

        // Dequeue Op
        while(!queue.isEmpty()){
            queue.dequeue();
            queue.printQueue();
        }
        queue.dequeue(); // Dequeue an empty queue
        System.out.println(queue.isEmpty());

        // enqueue in an old empty queue
        queue.enqueueAll(arr);
        queue.printQueue();
        System.out.println(queue.isEmpty());

    }
}
