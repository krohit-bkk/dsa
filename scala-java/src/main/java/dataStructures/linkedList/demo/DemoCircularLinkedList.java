package dataStructures.linkedList.demo;

import dataStructures.linkedList.CircularLinkedList;

public class DemoCircularLinkedList {
    public static void main(String[] args) {
        CircularLinkedList cll1 = new CircularLinkedList();
        CircularLinkedList cll2 = new CircularLinkedList();

        // Insert at the beginning of CLL
        // Insert at the end of CLL
        for(int i = 1; i <= 4; i++){
            System.out.println("Iteration: " + i);
            cll1.push(i);
            cll1.printList();
            cll2.append(i);
            cll2.printList();
            System.out.println();
        }
    }
}
