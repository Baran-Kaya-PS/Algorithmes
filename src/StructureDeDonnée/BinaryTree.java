package StructureDeDonnée;
import com.sun.source.tree.Tree;


import java.util.*;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

public class BinaryTree<T extends Comparable<T>> implements Iterable<T>{
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

    public boolean isIdentic(BinaryTree tree){
        return isSameTree(this.root,tree.root);
    }

    private boolean isSameTree(TreeNode<T> root, TreeNode<T> tree) {
        if (isNull(root) && isNull(tree) || root.getValue() == tree.getValue()) return true;
        if (root.getValue() != tree.getValue()) return false;
        if (root.getLeftChild().getValue() != tree.getLeftChild().getValue()) return false;
        if (root.getRightChild().getValue() != tree.getRightChild().getValue()) return false;
        if (isNull(root.getLeftChild())&& !isNull(tree.getLeftChild()) || !isNull(root.getLeftChild())&& isNull(tree.getLeftChild())) return false;
        if (isNull(root.getRightChild())&& !isNull(tree.getRightChild()) || !isNull(root.getRightChild())&& isNull(tree.getRightChild())) return false;
        return isSameTree(root.getLeftChild(), tree.getLeftChild()) && isSameTree(root.getRightChild(), tree.getRightChild());
    }

    public void insert(T value) {
        root = insertRecursive(root,value);
    }
    private TreeNode<T> insertRecursive(TreeNode<T> current, T value) {
        if (isNull(current)){
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

    public boolean isSymetric(){
        return isMirror(this.root.getLeftChild(),this.root.getRightChild());
    }

    private boolean isMirror(TreeNode<T> left, TreeNode right) {
        if (isNull(left) && !isNull(right)) return false;
        if (!isNull(left) && isNull(right)) return false;
        if (!Objects.equals(left.value, right.value)) return false;
        return isMirror(left.getLeftChild(),right.getRightChild()) && isMirror(left.getRightChild(), right.getLeftChild());
    }

    public boolean contains(T i) {
        return containsRecursive(root,i);
    }

    private boolean containsRecursive(TreeNode<T> root, T i) {
        if (isNull(root)){return false;}
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
        for (int i = 0; i < 3; i ++){
            binaryTree.printLevel(i);
            System.out.println("");
        }
        System.out.println(" \n ");
        String préOrder = binaryTree.préOrderTraversal(binaryTree.root,new StringBuilder()).toString();
        String inOrder = binaryTree.inOrder(binaryTree.root,new StringBuilder()).toString();
        String postOrder = binaryTree.postOrderTraversal(binaryTree.root,new StringBuilder()).toString();

        System.out.println(préOrder + "\n" + inOrder + "\n" + postOrder);
        System.out.println(binaryTree.contains(5));
        System.out.println(binaryTree.contains(23));
        System.out.println(binaryTree.findMin());
        System.out.println(binaryTree.findMax());

        System.out.println("\n\n\n\n reset de l'arbre");
        binaryTree.generateTree(0,500,50);
        int etages = binaryTree.stages(binaryTree.root);
        for (int i = 0; i < etages;i++){
            binaryTree.printLevel(i);
            System.out.println(" ");
        }
        System.out.println(" \n ");
        LinkedList<Integer> linkedList = binaryTree.TreeToLinkedList(binaryTree.root);
        System.out.println(linkedList.isSorted());
    }

    private int stages(TreeNode<T> root) {
        if (isNull(root)) return 0;
        int hauteurGauche = stages(root.left);
        int hauteurDroite = stages(root.right);

        return 1 + Math.max(hauteurDroite,hauteurGauche);
    }

    public void generateTree(int min, int max, int numberOfValues){
        this.root = null;
        Random random = new Random();
        for (int i = 0; i < numberOfValues;i++){
            T value = (T) Integer.valueOf(random.nextInt(max-min-1)+min);
            this.insert(value);
        }
    }

    private void toStringRec(TreeNode<T> node, StringBuilder sb, String prefix, boolean isTail) {
        if (isNull(node)) {
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
        if (isNull(root)) {
            throw new NoSuchElementException("root is null");
        }
        return findMinRecursive(root);
    }

    private T findMinRecursive(TreeNode<T> root) {
        if (isNull(root.left)) return root.value;
        return findMinRecursive(root.getLeftChild());
    }

    public T findMax(){
        if (isNull(root)){
            throw new NoSuchElementException("root is null");
        }
        return findMaxRecursive(root);
    }

    public void printLevel(int level){
        printLevelRecursive(root,level);
    }

    private void printLevelRecursive(TreeNode<T> node, int level) {
        if (isNull(root)) {
            return;
        }
        if (level == 0) {
            System.out.print(node.value + " ");
        } else {
            printLevelRecursive(node.left, level - 1);
            printLevelRecursive(node.right, level - 1);
        }
    }

    private T findMaxRecursive(TreeNode<T> root) {
        if (isNull(root.right)) return root.value;
        return findMaxRecursive(root.getRightChild());
    }

    private int maxHeight(){
        return maxHeightRec(this.root);
    }

    private int maxHeightRec( TreeNode<T> root) {
        if (isNull(root)) return 0;
        else {
            int maxleft = maxHeightRec(root.left);
            int maxright = maxHeightRec(root.right);
            return 1 + Math.max(maxleft, maxright); // incrémente de 1 à partir de la branche la plus basse
        }
    }
    private LinkedList<T> TreeToLinkedList(TreeNode<T> root){
        LinkedList<T> list = new LinkedList<>();
        TreeToLinkedListRec(this.root,list);
        return list;
    }

    private void TreeToLinkedListRec(TreeNode<T> node,LinkedList<T> list) {
        if (isNull(root)){
            list.add(node.value);
            TreeToLinkedListRec(node.left,list);
            TreeToLinkedListRec(node.right,list);
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
            while (isNull(root)) {
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