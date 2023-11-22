package StructureDeDonnée;

import com.sun.source.tree.Tree;

public class TreeNode<T extends Comparable> implements Comparable<TreeNode<T>>{
    T value;
    TreeNode left;
    TreeNode right;

    public TreeNode(T value){
        this.value = value;
        left = null;
        right = null;
    }
    public void setValue(T val){
        this.value = val;
    }
    public void setLeft(TreeNode<T> child){
        this.left = child;
    }
    public void setRight(TreeNode<T> child){
        this.right = child;
    }
    public TreeNode<T> getLeftChild(){
        return this.left;
    }
    public TreeNode<T> getRightChild(){
        return this.right;
    }
    public T getValue(){
        return this.value;
    }
    public boolean isLeaf() {
        return left == null && right == null;
    }
    public void addLeft(T value) {
        this.left = new TreeNode<>(value);
    }
    public void addRight(T value) {
        this.right = new TreeNode<>(value);
    }
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", left=" + (left == null ? "null" : left.getValue()) +
                ", right=" + (right == null ? "null" : right.getValue()) +
                '}';
    }

    @Override
    public int compareTo(TreeNode<T> o) {
        if (o == null){
            throw new IllegalArgumentException("Node passé en argument nul");
        }
        return this.value.compareTo(o.value);
    }
}
