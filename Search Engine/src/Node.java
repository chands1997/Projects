/**
 * Created by Shivangi Chand on 3/23/2017.
 */
public class Node {

    private Object data;
    private Node next;
    private Node prev;

    public Node(Object obj){
        data = obj;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public void setPrev(Node prev){
        this.prev = prev;
    }

    public Node getNext(){
        return next;
    }

    public Node getPrev(){
        return prev;
    }

    public Object getData(){
        return data;
    }
}
