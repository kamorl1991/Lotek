public class CustomStack {
    Node head;
    int size = 0;

    private class Node{
        int data;
        Node next;
        private Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    public void add(int data){
        if (size == 0) head = new Node(data, null);
        else{
            Node temp = head;
            head = new Node(data, temp);
        }
        size++;
    }

    public int pop(){
        int toPop;
        if (size == 0) return -1;
        else if (size == 1){
            toPop = head.data;
            size--;
            head = null;
            return toPop;
        }
        else {
           toPop = head.data;
           head = head.next;
           size--;
           return toPop;
        }
    }
}
