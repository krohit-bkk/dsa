package dataStructures.linkedList.demo;

import dataStructures.linkedList.DLLNode;
import dataStructures.linkedList.DoublyLinkedList;

public class DemoDoublyLinkedList {
    public static void main(String[] args) {
        DoublyLinkedList dll1 = new DoublyLinkedList();
        DoublyLinkedList dll2 = new DoublyLinkedList();

        // Insert at the beginning of the list
        // Insert at the end of the list
        System.out.println("\n\n>>>> Testing insertion ops...");
        for(int i = 1; i<= 4; i++){
            dll1.push(i);
            dll2.append(i);
        }

        // Verify the output
        dll1.printList();
        dll2.printList();

        dll1.printHeadTail();
        dll2.printHeadTail();

        // Testing remove ops
        System.out.println("\n\n>>>> Testing deletion ops...");
        while(!dll1.isEmpty()){
            System.out.println();
            dll1.removeFirst();
            dll1.printList();
        }
        while(!dll2.isEmpty()){
            System.out.println();
            dll2.removeLast();
            dll2.printList();
        }

        dll1.pushArray(new int[]{10, 20, 30, 40, 50});
        System.out.println("\n\n>>>> Repopulated DoublyLinkedList");
        dll1.printList();
        dll1.removeAt(0);   // Remove first
        dll1.printList();
        dll1.removeAt(3);   // Remove last
        dll1.printList();
        dll1.removeAt(1);   // Remove from middle (index = 1, size = 3)
        dll1.printList();
        dll1.removeAt(1);   // Remove last
        dll1.printList();
        dll1.removeLast();        // Remove first
        dll1.printList();
    }
}
