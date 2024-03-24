package StructureDeDonnée;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<T>> implements Iterable<T>{
    //TODO implémenter STREAM --> plus rapide a écrire
    // complexifier voir trouver des exos leetcode / problèmes créatifs -> 
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
    public boolean isSorted(){
        Node<T> current = head;
        while (current != null){
            if (current.getNext() != null && current.get().compareTo(current.getNext().get()) > 0){
                return false;
            }
            current = current.getNext();
        }
        return true;
    }
    public void add(T data){
        System.out.println("Ajout de l'élément : " + data);
        Node<T> newNode = new Node<>(data);
        if (head == null){
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        System.out.println(this);
    }
    public void add(T data, int index){
        checkIndex(index);
        System.out.println("Ajout de l'élément : " + data + " à l'emplacement : "+ index);
        if (index == 0){
            Node<T> node = new Node<>(data);
            node.setNext(head);
            node = head;
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++){
            current = current.getNext();
        }
        Node<T>old_next = current.getNext();
        current.setNext(new Node<>(data));
        current.getNext().setNext(old_next);
        System.out.println(this);
    }
    public void clear(){
        System.out.println("Suppresion des valeurs de la linkedlist");
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
            } else curr = curr.getNext();
        }
    }
    public boolean hasCycle(){
        Node<T> slow = head;
        Node<T> fast = head.getNext();
        while (fast != null && fast.getNext() != null) {
            if (slow.equals(fast)) return true;
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return false;
    }
    public void reverseList(){
        System.out.println("liste avant le reverse + " + this.toString());
        Node<T> curr = head;
        Node<T> prev = null;
        Node<T> next = null;
        while (curr != null){
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
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

    public Node<T> intersection(LinkedList<T> list){
        int thisListSize = this.size();
        int listSize = list.size();
        int sizeDiff = Math.abs(thisListSize - listSize);

        Node<T> curr = this.head;
        Node<T> listcurr = list.head;

        if(thisListSize>listSize){
            while (sizeDiff-- != 0){
                curr = curr.getNext();
            }
        } else {
            while (sizeDiff-- != 0){
                listcurr = listcurr.getNext();
            }
        }
        while (curr != null && listcurr != null) {
            if (curr == listcurr) {
                return curr; // Intersection trouvée
            }
            curr = curr.getNext();
            listcurr = listcurr.getNext();
        }
        return null;
    }
    public void deleteCycle(){
        System.out.println("DeletingCycle...");
        Node<T> slow = head, fast = head;
        boolean cycleDetected = false;

        // Détecter le cycle
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();

            if (slow == fast) {
                cycleDetected = true;
                break;
            }
        }

        if (!cycleDetected) {
            System.out.println("Aucun cycle détecté!");
            return;
        }

        // Trouver la longueur du cycle
        int cycleLength = 0;
        do {
            fast = fast.getNext();
            cycleLength++;
        } while (slow != fast);

        // Trouver le début du cycle
        slow = head;
        fast = head;
        for (int i = 0; i < cycleLength; i++) {
            fast = fast.getNext();
        }
        Node<T> previous = null;
        while (slow != fast) {
            previous = fast;
            slow = slow.getNext();
            fast = fast.getNext();
        }

        // Supprimer le cycle
        if (previous != null) {
            previous.setNext(null);
        }

        System.out.println("Cycle supprimé.");
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

    private BinaryTree<T> LinkedListToTree(){
        BinaryTree<T> tree = new BinaryTree<>();
        for (int i = 0; i < this.size();i++){
            tree.insert(this.get(i));
            System.out.println(" " +this.get(i) + " a été ajouté ");
        }
        return tree;
    }

    private int size() {
        int size = 0;
        Node<T> current = head;
        while (current != null){
            size++;
            current = current.getNext();
        }
        return size;
    }

    public static void main(String[] args) {
        LinkedList<Integer> myList = new LinkedList<>();

        // Ajout d'éléments à la liste
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);

        myList.createCycle(2); // Créer un cycle au noeud avec la valeur 3

        System.out.println("Affichage de la liste avec cycle:");
        System.out.println(myList); // Ici, la liste sera affichée avec la représentation du cycle

        System.out.println("Cycle détecté avant suppression: " + myList.hasCycle());

        myList.deleteCycle();

        System.out.println("Cycle détecté après suppression: " + myList.hasCycle());

        System.out.println("Affichage de la liste sans cycle:");
        System.out.println(myList); // Ici, la liste sera affichée sans cycle après suppression
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree = myList.LinkedListToTree();
        System.out.println(myList.toString());
        System.out.println(tree.toString());

    }


    public void createCycle(int index) {
        if (head == null) throw new NoSuchElementException("La liste est vide.");
        checkIndex(index);

        Node<T> current = head;
        int currentIndex = 0;
        while (currentIndex != index) {
            current = current.getNext();
            currentIndex++;
        }
        tail.setNext(current);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        HashSet<Node<T>> visited = new HashSet<>();
        Node<T> current = head;
        Node<T> cycleStart = null;

        while (current != null) {
            if (visited.contains(current)) {
                cycleStart = current;
                break;
            }
            visited.add(current);
            sb.append(current.get().toString());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }

        if (cycleStart != null) {
            sb.append("] -> Cycle starts at: ");
            sb.append(cycleStart.get().toString());
            sb.append(" -> [");
            current = cycleStart;
            do {
                sb.append(current.get().toString());
                current = current.getNext();
                if (current != cycleStart) {
                    sb.append(", ");
                }
            } while (current != cycleStart && current != null);
            sb.append("] (returns to ");
            sb.append(cycleStart.get().toString());
            sb.append(")");
        } else {
            sb.append("]");
        }

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