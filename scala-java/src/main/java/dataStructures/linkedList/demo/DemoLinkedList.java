package dataStructures.linkedList.demo;

import dataStructures.linkedList.LinkedList;
import dataStructures.linkedList.ListNodeUtils;

public class DemoLinkedList {
    public static void main(String[] args) throws Exception {
        LinkedList ll1 = new LinkedList();
        LinkedList ll2 = new LinkedList();

        // Insert the element at the beginning
        // Insert the element at the end
        for(int i = 0; i < 5; i++){
            ll1.push(i);
            ll2.append(i);
        }

        ll1.printList();
        ll2.printList();

        int[] arr1 = new int[]{10, 20, 30, 40, 50};
        int[] arr2 = {20, 30, 40, 50, 60};

        // Insert the array of elements at the beginning
        // Insert the array of elements at the end
        ll1.pushArray(arr1);
        ll2.appendArray(arr2);

        ll1.printList();
        ll2.printList();

        System.out.println("\n\nTesting insertAt");
        // Insert at a given index
        ll1.insertAt(0, 100);   // Inserting at the beginning
        ll1.printList();
        ll1.insertAt(20, 200);  // Inserting at out of bound index
        ll1.printList();
        ll1.insertAt(5, 300);   // Inserting in the middle of the LinkedList
        ll1.printList();
        ll1.insertAt(12, 200);  // Inserting at the end of the LinkedList
        ll1.printList();

        System.out.println("\n\nTesting removal of elements");
        // Remove from a given index
        ll1.removeFirst();         // Removing first element of the LinkedList
        ll1.printList();
        ll1.removeLast();          // Removing last element of the LinkedList
        ll1.printList();
        ll1.removeAt(5);     // Removing from the middle of the LinkedList
        ll1.printList();
        ll1.removeAt(15);    // Removing from the middle of the LinkedList
        ll1.printList();
        ll1.removeAt(9);     // Removing the last element of the LinkedList
        ll1.printList();
        ll1.removeAt(0);     // Removing the first element of the LinkedList
        ll1.printList();

        // Find element in a LinkedList
        System.out.println("\n\nCheck if exists...");
        System.out.println(ll1.exists(300));
        System.out.println(ll1.exists(200));
        System.out.println(ll1.findIndex(300));
        System.out.println(ll1.findIndex(200));
    }

}
