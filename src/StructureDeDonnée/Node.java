package StructureDeDonnée;

public class Node<T> {
    private T val;
    private Node<T> next;

    Node(T value) {
        this.val = value;
        this.next = null;
    }
    public T get(){
        return this.val;
    }
    public void set(T data){
        this.val = data;
    }
    public void setNext(Node<T> next){
        this.next = next;
    }
    public Node<T> getNext(){
        return this.next;
    }
}
