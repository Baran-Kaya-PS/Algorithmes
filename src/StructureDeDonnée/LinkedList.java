package StructureDeDonnée;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<T>> implements Iterable<T>{
    Node<T> head;
    Node<T> tail;

    public LinkedList(){
        this.head = null;
    }
    public LinkedList(T[] values){
        for (T val : values){
            add(val);
        }
    }
    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if (head == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }
    public void add(T data, int index){
        checkIndex(index);
        if (index == 0){
            Node<T> node = new Node<>(data);
            node.setNext(head);
            node = head;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++){
            current = current.getNext();
        }
        Node<T>old_next = current.getNext();
        current.setNext(new Node<>(data));
        current.getNext().setNext(old_next);
    }
    public void clear(){
        head = null;
        tail = null;
    }

    public boolean contains(T value){
        Node<T> curr = head;
        while (curr != null){
            if (curr.get().equals(value)){
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    public void removeFirstValue(T value){
        if (head == null) return;
        if (head.get().equals(value)){
            head = head.getNext();
            if (head == null) tail = null;
            return;
        }
        Node<T> curr = head;
        while (curr.getNext() != null){
            if (curr.getNext().get().equals(value)){
                curr.setNext(curr.getNext().getNext());
                if (curr.getNext() == null) tail = curr; // Si on supprime le dernier élément
                return;
            }
            curr = curr.getNext();
        }
    }

    public void removeAll(T value){
        while (head != null && head.get().equals(value)) {
            head = head.getNext();
        }

        if(head == null) {
            tail = null;
            return;
        }

        Node<T> curr = head;
        while (curr.getNext() != null) {
            if (curr.getNext().get().equals(value)) {
                curr.setNext(curr.getNext().getNext());
                if(curr.getNext() == null) tail = curr; // Si on supprime le dernier élément
            } else {
                curr = curr.getNext();
            }
        }
    }
    public boolean hasCycle(){
        Node<T> slow = head;
        Node<T> fast = head.getNext(); // pour eviter de return true dès le début
        while (fast != null && fast.getNext() != null) {
            if (slow.equals(fast)) return true;
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return false;
    }
    public void reverseList(){
        Node<T> curr = head;
        Node<T> prev = null;
        Node<T> next = null;
        while (curr != null){
            curr.setNext(prev);
            prev = curr;
            curr = next;
            next = next.getNext();
        }
    }
    public LinkedList<T> merge(LinkedList<T> list, boolean sorted){
        LinkedList<T> mergedList = new LinkedList<>();
        if (!sorted) {
            // La logique pour fusionner deux listes non triées (comme vous l'avez déjà écrit)
            Node<T> current = this.head;
            while (current != null) {
                mergedList.add(current.get());
                current = current.getNext();
            }
            current = list.head;
            while (current != null) {
                mergedList.add(current.get());
                current = current.getNext();
            }
        } else {
            // La logique pour fusionner deux listes triées
            Node<T> current1 = this.head;
            Node<T> current2 = list.head;
            while (current1 != null && current2 != null) {
                if (current1.get().compareTo(current2.get()) <= 0) {
                    mergedList.add(current1.get());
                    current1 = current1.getNext();
                } else {
                    mergedList.add(current2.get());
                    current2 = current2.getNext();
                }
            }
            // Ajouter les éléments restants de la première liste si nécessaire
            while (current1 != null) {
                mergedList.add(current1.get());
                current1 = current1.getNext();
            }
            // Ajouter les éléments restants de la deuxième liste si nécessaire
            while (current2 != null) {
                mergedList.add(current2.get());
                current2 = current2.getNext();
            }
        }
        return mergedList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LinkedList)) return false;
        LinkedList<?> other = (LinkedList<?>) obj;
        Node<T> currentA = this.head;
        Node<?> currentB = other.head;

        while (currentA != null && currentB != null) {
            if (!currentA.get().equals(currentB.get())) {
                return false;
            }
            currentA = currentA.getNext();
            currentB = currentB.getNext();
        }
        return currentA == null && currentB == null;
    }

    public T get(int index){
        checkIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.get();
    }

    public int indexOf(T data){
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.get().equals(data)) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    public void set(int index, T data){
        checkIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.set(data);
    }

    public void remove(int index){
        checkIndex(index);
        if (index == 0){
            head = head.getNext();
            if (head == null) tail = null;
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        current.setNext(current.getNext().getNext());
        if (current.getNext() == null) tail = current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int size() {
        int size = 0;
        Node<T> current = head.getNext();
        while (current != null){
            size++;
            current = current.getNext();
        }
        return size;
    }
    public static void main(String[] args) {

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
