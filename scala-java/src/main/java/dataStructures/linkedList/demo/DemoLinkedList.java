package dataStructures.linkedList.demo;

import dataStructures.linkedList.LinkedList;

public class DemoLinkedList {
    public static void main(String[] args) {
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
        ll1.insertAt(12, 200);   // Inserting at the end of the LinkedList
        ll1.printList();
    }

}
