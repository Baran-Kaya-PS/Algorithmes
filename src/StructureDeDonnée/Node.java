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

    @Override
    public boolean equals(Object obj) {
        // Vérifie si les références sont identiques
        if (this == obj) {
            return true;
        }
        // Vérifie si l'objet passé est null ou s'il est d'une classe différente
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Cast l'objet vers Node<T>
        Node<?> other = (Node<?>) obj;
        // Compare les valeurs, en gérant le cas où elles sont null
        if (val == null) {
            return other.val == null;
        } else {
            return val.equals(other.val);
        }
    }

    @Override
    public int hashCode() {
        return val != null ? val.hashCode() : 0;
    }
}
