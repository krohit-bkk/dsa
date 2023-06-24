package dataStructures.linkedList;

// Helper class
public class ListNodeUtils {

    // Generate a random number between a predefined range
    public static int getRandomNumber(){
        int min = 0;
        int max = 500;
        return (int)Math.floor(Math.random() * (max-min + 1) + min);
    }

    // Returns a LinkedList of the given size
    // with randomly generated values as data
    public static ListNode getListOfSize(int size) throws Exception {
        ListNode head = null;
        ListNode prev = null;
        if(size > 0){
            int i = 0;
            while(i < size){
                int nodeData = getRandomNumber();
                ListNode node = new ListNode(nodeData);
                if(head == null)
                    head = node;
                else
                    prev.setNext(node);
                prev = node;
                i += 1;
            }
        }
        else
            throw new Exception("ERROR: Invalid parameter. Size must be greater than 0!");
        return head;
    }

    // Returns a string representation of LinkedList
    public static String getListAsString(ListNode head){
        StringBuilder msg = new StringBuilder("HEAD");
        if(head != null) {
            ListNode curr = head;
            while(curr != null){
                int data = curr.getData();
                msg.append(" --> ").append(data);
                curr = curr.getNext();
            }
        }
        msg.append(" --> NULL");
        return msg.toString();
    }

    // Prints the string representation of LinkedList
    public static void printList(ListNode head){
            System.out.println(getListAsString(head));
    }

    // Returns the length of the LinkedList
    public static int getSize(ListNode headNode){
        int length = 0;
        ListNode curr = headNode;
        while (curr != null) {
            length += 1;
            curr = curr.getNext();
        }
        return length;
    }
}
