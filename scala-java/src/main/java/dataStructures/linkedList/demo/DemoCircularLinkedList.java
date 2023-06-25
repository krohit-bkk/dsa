package dataStructures.linkedList.demo;

import dataStructures.linkedList.CircularLinkedList;

public class DemoCircularLinkedList {
    public static void main(String[] args) {
        CircularLinkedList cll1 = new CircularLinkedList();
        CircularLinkedList cll2 = new CircularLinkedList();

        // Insert at the beginning of CLL
        // Insert at the end of CLL
        for(int i = 1; i <= 4; i++){
            cll1.push(i);
            cll2.append(i);
        }

        // Print CircularLinkedList
        cll1.printList();
        cll2.printList();

        // Testing remove first
        System.out.println("\n\n>>>> Testing remove first\n");
        while (!cll1.isEmpty()){
            cll1.removeFirst();
            cll1.printList();
        }
        // Testing remove last
        System.out.println("\n\n>>>> Testing remove last\n");
        while (!cll2.isEmpty()){
            cll2.removeLast();
            cll2.printList();
        }
    }
}
