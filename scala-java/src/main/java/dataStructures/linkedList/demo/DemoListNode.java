package dataStructures.linkedList.demo;

import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

public class DemoListNode {
    public static void main(String[] args) throws Exception {
        ListNode ll = ListNodeUtils.getListOfSize(5);
        System.out.println("List size: " + ListNodeUtils.getSize(ll));
        ListNodeUtils.printList(ll);
        System.out.println("List size: " + ListNodeUtils.getSize(null));
        ListNodeUtils.printList(null);
    }
}