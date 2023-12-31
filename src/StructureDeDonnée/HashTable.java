package StructureDeDonnée;

import java.util.*;
import java.util.LinkedList;

import static java.util.Objects.isNull;


public class HashTable<K extends Comparable<K>, V> implements Map<K,V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private HashTableEntry<K,V>[] hashTable;
    private int size;
    private float loadFactor;
    private int threshold;
    public HashTable(){
        hashTable = (HashTableEntry<K, V>[]) new HashTableEntry[DEFAULT_INITIAL_CAPACITY];
        size = 0;
        loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int) (DEFAULT_INITIAL_CAPACITY*DEFAULT_LOAD_FACTOR);
    }
    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns {@code true} if this map contains a mapping for the specified
     * key.  More formally, returns {@code true} if and only if
     * this map contains a mapping for a key {@code k} such that
     * {@code Objects.equals(key, k)}.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     * key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsKey(Object key) {
        if (key == null) {
            throw new NullPointerException("object key is null");
        }
        int index = hash(key);
        HashTableEntry<K,V> currentEntry = hashTable[index];
        while (currentEntry != null){
            if (Objects.equals(currentEntry.getKey(),key)){
                return true;
            }
            currentEntry = currentEntry.getNext();
        }
        return false;
    }

    private int hash(Object key) {
        int index = key.hashCode();
        index ^= (index >>> 20) ^ (index >>> 12); // décalage de bits
        index = index ^ (index >>> 7) ^ (index >>> 4);
        return index & hashTable.length-1;
    }

    /**
     * Returns {@code true} if this map maps one or more keys to the
     * specified value.  More formally, returns {@code true} if and only if
     * this map contains at least one mapping to a value {@code v} such that
     * {@code Objects.equals(value, v)}.  This operation
     * will probably require time linear in the map size for most
     * implementations of the {@code Map} interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return {@code true} if this map maps one or more keys to the
     * specified value
     * @throws ClassCastException   if the value is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified value is null and this
     *                              map does not permit null values
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < hashTable.length; i++) {
            HashTableEntry<K, V> entry = hashTable[i];
            while (entry != null) {
                if (Objects.equals(entry.getValue(), value)) {
                    return true;
                }
                entry = entry.getNext();
            }
        }
        return false;
    }


    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that
     * {@code Objects.equals(key, k)},
     * then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V get(Object key) {
        if (isNull(key)){
            throw new NullPointerException("this map does not permit null keys");
        }
        int index = hash(key);
        HashTableEntry<K,V> entry = hashTable[index];
        while(entry != null && !Objects.equals(key,entry.getKey())){
            entry = entry.getNext();
        }
        if (entry == null) return null;
        return entry.getValue();
    }

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * {@code m} is said to contain a mapping for a key {@code k} if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * {@code true}.)
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     * {@code null} if there was no mapping for {@code key}.
     * (A {@code null} return can also indicate that the map
     * previously associated {@code null} with {@code key},
     * if the implementation supports {@code null} values.)
     * @throws UnsupportedOperationException if the {@code put} operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of the specified key or value
     *                                       prevents it from being stored in this map
     * @throws NullPointerException          if the specified key or value is null
     *                                       and this map does not permit null keys or values
     * @throws IllegalArgumentException      if some property of the specified key
     *                                       or value prevents it from being stored in this map
     */
    @Override
    public V put(K key, V value) {
        if(isNull(key) || isNull(value)) {
            throw new NullPointerException("Key or value is null");
        }
        int index = hash(key);
        HashTableEntry<K,V> existingEntry = hashTable[index];
        HashTableEntry<K,V> prev = null;

        while (existingEntry != null){
            if (Objects.equals(existingEntry.getKey(),key)){
                V oldValue = existingEntry.getValue();
                existingEntry.setValue(value);
                return oldValue;
            }
            prev = existingEntry;
            existingEntry = existingEntry.getNext();
        }
        HashTableEntry<K,V> newEntry = new HashTableEntry<>(key,value);
        if (prev != null){
            prev.setNext(newEntry);
        } else {
            hashTable[index] = newEntry;
        }
        size++;
        if(size >= threshold){
            resize();
        }
        return null;
    }

    public void resize(){
        int newCapacity = hashTable.length*2;
        HashTableEntry<K,V>[] newTable = (HashTableEntry<K, V>[]) new HashTableEntry[newCapacity];

        for (HashTableEntry<K,V> headNode : hashTable){
            HashTableEntry<K,V> currentNode = headNode;
            while (currentNode != null){
                int newIndex = rehash(currentNode.getKey(),newCapacity);
                HashTableEntry<K,V> nextNode = currentNode.getNext();
                currentNode.setNext(newTable[newIndex]);
                newTable[newIndex] = currentNode;

                currentNode = nextNode;
            }
        }
        hashTable = newTable;
        threshold = (int) (newCapacity*loadFactor);
    }

    private int rehash(K key, int newCapacity) {
        int index = key.hashCode();
        index ^= (index >>> 20) ^ (index >>> 12); // décalage de bits
        index = index ^ (index >>> 7) ^ (index >>> 4);
        return index & newCapacity-1;
    }

    public class Main {
        public static void main(String[] args) {
            HashTable<String, Integer> hashTable = new HashTable<>();

            // Test de la méthode put
            hashTable.put("clé1", 1);
            hashTable.put("clé2", 2);
            hashTable.put("clé3", 3);

            // Test de la méthode get
            System.out.println("Valeur pour 'clé1': " + hashTable.get("clé1"));

            // Test de la méthode containsKey
            System.out.println("Contient 'clé2'?: " + hashTable.containsKey("clé2"));

            // Test de la méthode containsValue
            System.out.println("Contient la valeur 3?: " + hashTable.containsValue(3));

            // Test de la méthode size et isEmpty
            System.out.println("Taille: " + hashTable.size());
            System.out.println("Est vide?: " + hashTable.isEmpty());

            // Test de la méthode remove
            hashTable.remove("clé2");
            System.out.println("Contient 'clé2' après suppression?: " + hashTable.containsKey("clé2"));

            // Test de la méthode clear
            hashTable.clear();
            System.out.println("Taille après nettoyage: " + hashTable.size());

            // Ajouter plus d'éléments pour tester resize
            for(int i = 0; i < 20; i++) {
                hashTable.put("clé" + i, i);
            }

            // Vérifier le redimensionnement
            System.out.println("Taille après redimensionnement: " + hashTable.size());
        }
    }

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();

        // Test de la méthode put
        hashTable.put("clé1", 1);
        hashTable.put("clé2", 2);
        hashTable.put("clé3", 3);

        // Test de la méthode get
        System.out.println("Valeur pour 'clé1': " + hashTable.get("clé1"));

        // Test de la méthode containsKey
        System.out.println("Contient 'clé2'?: " + hashTable.containsKey("clé2"));

        // Test de la méthode containsValue
        System.out.println("Contient la valeur 3?: " + hashTable.containsValue(3));

        // Test de la méthode size et isEmpty
        System.out.println("Taille: " + hashTable.size());
        System.out.println("Est vide?: " + hashTable.isEmpty());

        // Test de la méthode remove
        hashTable.remove("clé2");
        System.out.println("Contient 'clé2' après suppression?: " + hashTable.containsKey("clé2"));

        // Test de la méthode clear
        hashTable.clear();
        System.out.println("Taille après nettoyage: " + hashTable.size());

        // Ajouter plus d'éléments pour tester resize
        for(int i = 0; i < 20; i++) {
            hashTable.put("clé" + i, i);
        }

        // Vérifier le redimensionnement
        System.out.println("Taille après redimensionnement: " + hashTable.size());
    }

    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).   More formally, if this map contains a mapping
     * from key {@code k} to value {@code v} such that
     * {@code Objects.equals(key, k)}, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     *
     * <p>Returns the value to which this map previously associated the key,
     * or {@code null} if the map contained no mapping for the key.
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contained no mapping for the key; it's also possible that the map
     * explicitly mapped the key to {@code null}.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     * {@code null} if there was no mapping for {@code key}.
     * @throws UnsupportedOperationException if the {@code remove} operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the key is of an inappropriate type for
     *                                       this map
     *                                       (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified key is null and this
     *                                       map does not permit null keys
     *                                       (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V remove(Object key) {
        if (isNull(key)) throw new NullPointerException("map does not permit null keys");
        int index = hash(key);
        HashTableEntry<K,V> current = hashTable[index];
        HashTableEntry<K,V> prev = null;
        while (current != null) {
            if (Objects.equals(current.getKey(), key)) {
                if (prev == null){
                    HashTableEntry<K,V> next = current.getNext();
                    hashTable[index] = next;
                } else {
                    prev.setNext(current.getNext());
                }
                size--;
                return current.getValue();
            }
            prev = current;
            current = current.getNext();
        }
        return null;
    }

    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object, Object) put(k, v)} on this map once
     * for each mapping from key {@code k} to value {@code v} in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     * @throws UnsupportedOperationException if the {@code putAll} operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of a key or value in the
     *                                       specified map prevents it from being stored in this map
     * @throws NullPointerException          if the specified map is null, or if
     *                                       this map does not permit null keys or values, and the
     *                                       specified map contains null keys or values
     * @throws IllegalArgumentException      if some property of a key or value in
     *                                       the specified map prevents it from being stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} operation
     *                                       is not supported by this map
     */
    @Override
    public void clear() {
        hashTable = (HashTableEntry<K,V>[]) new HashTableEntry[DEFAULT_INITIAL_CAPACITY];
        size = 0;
        threshold = (int) (DEFAULT_INITIAL_CAPACITY*loadFactor);
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own {@code remove} operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear}
     * operations.  It does not support the {@code add} or {@code addAll}
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (HashTableEntry<K,V> entry : hashTable){
            if (entry != null){
                HashTableEntry current = entry;
                while (current != null){
                    set.add((K) current.getKey());
                    current = current.getNext();
                }
            }
        }
        return set;
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own {@code remove} operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the {@code Iterator.remove},
     * {@code Collection.remove}, {@code removeAll},
     * {@code retainAll} and {@code clear} operations.  It does not
     * support the {@code add} or {@code addAll} operations.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        Collection<V> collection = new ArrayList<>();
        for (HashTableEntry<K,V> entry : hashTable){
            if (entry != null){
                HashTableEntry current = entry;
                while (current != null){
                    collection.add((V) current.getValue());
                    current = current.getNext();
                }
            }
        }
        return collection;
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own {@code remove} operation, or through the
     * {@code setValue} operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the {@code Iterator.remove},
     * {@code Set.remove}, {@code removeAll}, {@code retainAll} and
     * {@code clear} operations.  It does not support the
     * {@code add} or {@code addAll} operations.
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
