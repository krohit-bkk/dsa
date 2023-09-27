package dataStructures.queue.demo;

public class SimpleCircularQueue {
    private Integer front, rear, length;
    private final Integer limit = 5;
    private Integer[] data;
    private boolean isEmpty ;

    // Primary constructor
     public SimpleCircularQueue(){
         this.front = null;
         this.rear = null;
         this.length = 0;
         this.isEmpty = true;
         this.data = new Integer[10];
     }

     // Check if queue is empty
     public boolean isEmpty(){
         return this.isEmpty;
     }

    // Check if queue is not empty
    public boolean isNotEmpty(){
        return !this.isEmpty;
    }

    // Increment the length by 1
    public void lengthIncrement(){
         this.length += 1;
    }

    // Length decrement
    public void lengthDecrement(){
         if(this.length > 0)
             this.length -= 1;
    }

    // Return if SimpleCircularQueue is full
    public boolean isFull(){
         if(this.length == this.limit)
             return true;
         return false;
    }

    // Return the front element in the queue
    public Integer first(){
         if(this.isNotEmpty())
             return this.data[front];
         else
             return null;
    }

    // Return the front element in the queue
    public Integer last(){
        if(this.isNotEmpty())
            return this.data[rear];
        else
            return null;
    }

    // Enqueue operation
    public void enqueue(int data){
         // First element - set pointers
         if(this.isEmpty) {
             // Special case - queue is empty - front == rear
             // In this case the rear need not be incremented before insertion
             if(this.front == null) {
                 // Is queue uninitialized?
                 this.data[0] = data;
                 this.front = 0;
                 this.rear = 0;
             }
             else{
                 // Front in the middle?
                 if(this.front == this.rear)
                     this.data[this.front] = data;
             }
         }
         else{
             // Regular case
             if(!this.isFull()){
                 int tempRear = (this.rear + 1) % this.limit;
                 if (tempRear != this.front) {
                     this.rear = tempRear;
                     this.data[this.rear] = data;
                 }
             }
             else {
                 System.out.println("ERROR: Queue full!");
                 return;
             }
        }
        this.isEmpty = false;
        this.lengthIncrement();
    }

    // Enqueue operation for array
    public void enqueueAll(int[] arr) {
        if (!this.isFull() && this.length + arr.length <= this.limit)
            for (int data : arr)
                this.enqueue(data);

        else
            System.out.println("ERROR: Insertion of array failed! Not enough empty space in queue!");
    }

    // Print the contents of the queue
    public void printQueue(){
        System.out.println(this.getQueueAsString());
    }

    // Get the queue as String object
    private String getQueueAsString() {
         if(this.isNotEmpty()){
             StringBuilder msg = new StringBuilder("Length: [" + this.length + "] >> FRONT");

             if(this.front == this.rear)
                 return msg.append(" --> ")
                         .append(this.data[front])
                         .append(" --> REAR")
                         .toString();
             else{
                 int ptr = this.front;
                 msg.append(" --> ").append(this.data[ptr]);
                 while(ptr != this.rear && this.length > 0){
                     ptr = (ptr + 1)% this.limit;
                     msg.append(" --> ").append(this.data[ptr]);
                 }
                 msg.append(" --> REAR");
                 return msg.toString();
            }
         }
        return "Length: [" + this.length + "] >> FRONT" + " --> REAR";
    }

    // Print queue details
    public void getInfo(){
         this.printQueue();
         if(this.isNotEmpty())
            System.out.println(
                    "First[" + this.front + "]: " + this.data[this.front]
                    + "\nLast [" + this.rear + "]: " + this.data[this.rear]
                    + "\nLength: " + this.length
                    + "\nIsEmpty flag: " + this.isEmpty + "\n"
            );
         else{
             System.out.println(
                     "First[" + this.front + "]: " + this.data[this.front] + " (GARBAGE)"
                             + "\nLast [" + this.rear + "]: " + this.data[this.rear] + " (GARBAGE)"
                             + "\nLength: " + this.length
                             + "\nIsEmpty flag: " + this.isEmpty + "\n"
             );
         }
    }

    // Remove the front element of the queue
    public Integer dequeue() {
         Integer data = null;
         if(this.isNotEmpty()){
             data = this.data[this.front];
             int prevFront = this.front;
             this.front = (this.front + 1) % this.limit;

             // Length decrement
             this.lengthDecrement();

             // IsEmpty flag
             if(this.length == 0)
                 this.isEmpty = true;

             // Edge case - if queue had only one element, reset rear as well
             // prevFront == Rear
             if(prevFront == this.rear)
                 this.rear = this.front;
         }
         else
             System.out.println("ERROR: Empty queue! Nothing to remove");
        return data;
    }
}

class Demo{
    public static void main(String[] args) {
        // Create a queue object
        SimpleCircularQueue queue = new SimpleCircularQueue();
        for(int i = 0; i < 5; i++)
            queue.enqueue(i);
        queue.getInfo();

        // BACK TO BACK ENQUEUE/DEQUEUE OPERATIONS TO OBSERVE THE POINTER ROTATION

        // Insert array to queue - should fail as limit is 5 element
        int[] arr = new int[]{10,20,30};
        queue.enqueueAll(arr);
        queue.getInfo();

        // Creating and printing new empty queue
        // Print should give: Length: [0] >> FRONT --> REAR
        // queue = new SimpleCircularQueue();
        // queue.printQueue();

        // Delete from queue (LIMIT -1) times
        for (int i = 0; i < 4; i++) {
            queue.dequeue();
        }
        queue.getInfo();

        // Check if front and rear to a circular round up
        int[] arr1 = new int[]{10,20,30};
        queue.enqueueAll(arr1);
        queue.getInfo();

        queue.dequeue();
        queue.getInfo();

        queue.dequeue();
        queue.getInfo();

        queue.dequeue();
        queue.getInfo();

        queue.enqueue(99);
        queue.getInfo();

        queue.dequeue();
        queue.getInfo();

        queue.dequeue();
        queue.getInfo();

        queue.enqueue(999);
        queue.getInfo();

        arr1 = new int[]{100,200,300,400,500};
        for (int num : arr1) {
            queue.enqueue(num);
            queue.getInfo();
        }

        while(queue.isNotEmpty()){
            queue.dequeue();
            queue.getInfo();
        }
        queue.getInfo();
    }
}
