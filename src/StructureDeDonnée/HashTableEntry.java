package StructureDeDonnée;

import java.util.Map;
import java.util.Objects;

public class HashTableEntry<K extends Comparable<K>,V> implements Map.Entry<K,V>,Comparable<HashTableEntry<K,V>> {
    private K key;
    private V value;
    HashTableEntry<K,V> next;
    public HashTableEntry(K key, V value){
        this.key = key;
        this.value = value;
        this.next = null;
    }
    @Override
    public K getKey() {
        return this.key;
    }
    @Override
    public V getValue() {
        return this.value;
    }
    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }
    public HashTableEntry<K, V> getNext() {
        return this.next;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public void setNext(HashTableEntry<K, V> next) {
        this.next = next;
    }
    public int compareTo(HashTableEntry<K,V> other){
        return this.getKey().compareTo(compareTo(other.getKey()));
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof HashTableEntry)) return false;
        HashTableEntry<?,?> other = (HashTableEntry<?, ?>) o;
        return Objects.equals(getKey(),other.getKey()) && Objects.equals(getValue(),other.getValue());
    }
    @Override
    public int hashCode(){
        return Objects.hash(getKey(),getValue());
    }
}
