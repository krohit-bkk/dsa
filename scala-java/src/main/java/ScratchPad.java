import dataStructures.linkedList.ListNode;
import dataStructures.linkedList.ListNodeUtils;

public class ScratchPad {
    public static void main(String[] args) throws Exception {
        ListNode head = ListNodeUtils.getListOfSize(10);
        int k = 4;
        ListNodeUtils.printList(head);
        head = kBlockReverseRecurse(head, k);
        ListNodeUtils.printList(head);
    }

    private static ListNode kBlockReverseRecurse(ListNode head, int k) {
        System.out.println("Received: " + ListNodeUtils.getListAsString(head));
        // Edge case
        if(head == null || head.getNext() == null)
            return head;

        // Step-1: Reverse first k nodes then repeat for the rest
        ListNode curr = head;
        ListNode prev = null;
        ListNode temp = null;
        int counter = 0;
        while(curr != null && counter < k){
            temp = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = temp;
            counter += 1;
        }

        // Step-2: New end of first k nodes would be head
        // Now the head must point to the rest of the tail
        head.setNext(kBlockReverseRecurse(curr, k));
        return prev;
    }
}
