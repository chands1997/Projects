/**
 * Created by Shivangi Chand on 3/22/2017.
 */
public class MyQueue {

    private int count;
    private Node head;
    private Object monitor;

    public void add(Object o){
        if(head == null && count == 0){
            head = new Node(o);
            head.setNext(null);
            count = 1;
        }
        else if(head != null && count == 1){
            Node n = new Node(o);
            head.setNext(n);
            n.setNext(null);
            n.setPrev(head);
            count += 1;
        }
        else{
            Node n = new Node(o);
            Node current = head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(n);
            n.setPrev(current);
            n.setNext(null);
            count += 1;
        }
    }

    public Node remove(){
       if(head == null && count == 0){
           return null;
       }
       else if(head != null && count ==1){
           Node temp = head;
           head = null;
           count = 0;
           return temp;
       }
       else{
           Node temp = head;
           Node current = head;
           head = head.getNext();
           for(int i=0; i<count; i++){
               current = current.getNext();
           }
           count -= 1;
           return temp;
       }
    }

    public boolean isEmpty(){
        if(head == null && count == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return count;
    }

    public Node peek(){
        return head;
    }
}
