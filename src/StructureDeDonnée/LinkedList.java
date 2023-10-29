package StructureDeDonnée;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T>{
    Node<T> head;

    public LinkedList(){
        this.head = null;
    }
    public LinkedList(T[] values){
        for (T val : values){
            add(val);
        }
    }
    public void add(T data){
        if (head == null){
            head = new Node<>(data);
            return;
        }
        Node<T> current = head;
        while (current.getNext() != null){
            current = current.getNext();
        }
        current.setNext(new Node<T>(data));
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // sb more fast
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.get());
            current = current.getNext();
            if (current != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public class LinkedListIterator implements Iterator<T>{

        private Node<T> current;
        public LinkedListIterator(){
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            T data = current.get();
            current = current.getNext();
            return data;
        }
    }
}
