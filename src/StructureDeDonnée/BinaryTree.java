package StructureDeDonnée;
import com.sun.source.tree.Tree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<T extends Comparable> implements Iterable<T>{
    // TODO implémenter stream dans le code
    TreeNode<T> root;
    public BinaryTree(){root = null;}
    public BinaryTree(T value){
        root.setValue(value);
    }
    public BinaryTree(T[] values){
        for (T value : values){
            insert(value);
        }
    }
    public void insert(T value) {
        root = insertRecursive(root,value);
    }
    private TreeNode<T> insertRecursive(TreeNode<T> current, T value) {
        if (current == null){
            current = new TreeNode<>(value);
        }
        int compare = current.value.compareTo(value);
        if (compare > 0) {
            current.left = insertRecursive(current.left,value);
        } else if (compare < 0){
            current.right = insertRecursive(current.right,value);
        } else {
            return current;
        }
        return current;
    }

    public boolean contains(T i) {
        return containsRecursive(root,i);
    }

    private boolean containsRecursive(TreeNode<T> root, T i) {
        if (root == null){return false;}
        int comp = root.value.compareTo(i);
        if (comp > 0){return containsRecursive(root.left,i);}
        else if (comp < 0){return containsRecursive(root.right,i);}
        else { return true;}
    }

    @Override
    public String toString() {
        return inOrder(root, new StringBuilder()).toString() + " postOrder method";
    }

    private StringBuilder inOrderTraversal(TreeNode<T> node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversal(node.left, sb);
            sb.append(node.value).append(" ");
            inOrderTraversal(node.right, sb);
        }
        return sb;
    }

    public StringBuilder préOrderTraversal(TreeNode<T> node, StringBuilder sb){
        if (node == root) sb.append("préOrderTraversal method : " );
        if (node != null){
            sb.append(node.value).append(" ");
            préOrderTraversal(node.left,sb);
            préOrderTraversal(node.right,sb);
        }
        return sb;
    }

    public StringBuilder postOrderTraversal(TreeNode<T> node, StringBuilder sb){
        if (node == root) sb.append("postOrderTraversal method : ");
        if (node != null){
            postOrderTraversal(node.left,sb);
            postOrderTraversal(node.right,sb);
            sb.append(node.value).append(" ");
        }
        return sb;
    }
    public StringBuilder inOrder(TreeNode<T> node, StringBuilder sb){
        if (node == this.root){sb.append("In Order method : ");}
        if (node != null){
            inOrder(node.left,sb);
            sb.append(String.format("%d",node.value)).append(" ");
            inOrder(node.right,sb);
        }
        return sb;
    }

    public static void main(String[] args){
        BinaryTree binaryTree = new BinaryTree<>();
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(8);
        binaryTree.insert(1);
        binaryTree.insert(4);
        binaryTree.insert(6);
        binaryTree.insert(9);

        String préOrder = binaryTree.préOrderTraversal(binaryTree.root,new StringBuilder()).toString();
        String inOrder = binaryTree.inOrder(binaryTree.root,new StringBuilder()).toString();
        String postOrder = binaryTree.postOrderTraversal(binaryTree.root,new StringBuilder()).toString();

        System.out.println(préOrder + "\n" + inOrder + "\n" + postOrder);
        System.out.println(binaryTree.contains(5));
        System.out.println(binaryTree.contains(23));
        System.out.println(binaryTree.findMin());
        System.out.println(binaryTree.findMax());
    }

    private void toStringRec(TreeNode<T> node, StringBuilder sb, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }
        sb.append(prefix).append((isTail ? "└──" : "├──")).append(node.value).append("\n");
        List<TreeNode<T>> children = new ArrayList<>();
        if (node.left != null) children.add(node.left);
        if (node.right != null) children.add(node.right);

        for (int i = 0; i < children.size() - 1; i++) {
            toStringRec(children.get(i), sb, prefix + (isTail ? "    " : "│  "), false);
        }

        if (children.size() > 0) {
            toStringRec(children.get(children.size() - 1), sb, prefix + (isTail ? "    " : "│  "), true);
        }
    }

    public T findMin() {
        if (root == null) {
            throw new NoSuchElementException("root is null");
        }
        return findMinRecursive(root);
    }

    private T findMinRecursive(TreeNode<T> root) {
        if (root.left == null) return root.value;
        return findMinRecursive(root.getLeftChild());
    }

    public T findMax(){
        if (root == null){
            throw new NoSuchElementException("root is null");
        }
        return findMaxRecursive(root);
    }

    private T findMaxRecursive(TreeNode<T> root) {
        if (root.right == null) return root.value;
        return findMaxRecursive(root.getRightChild());
    }

    private int maxHeight(){
        return maxHeightRec(this.root);
    }

    private int maxHeightRec( TreeNode<T> root) {
        if (root == null) return 0;
        else {
            int maxleft = maxHeightRec(root.left);
            int maxright = maxHeightRec(root.right);
            return 1 + Math.max(maxleft, maxright); // incrémente de 1 à partir de la branche la plus basse
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<T> {
        private Stack<TreeNode> stack = new Stack<>();

        public InOrderIterator() {
            pushLeft(root);
        }

        private void pushLeft(TreeNode<T> root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            TreeNode<T> res = stack.pop();
            pushLeft(res.right);
            return res.value;
        }
    }
}