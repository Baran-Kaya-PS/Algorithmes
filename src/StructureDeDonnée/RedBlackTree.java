package StructureDeDonnée;

public class RedBlackTree<T extends Comparable<T>> {
    private RedBlackNode<T> root;
    public RedBlackTree(){}
    public RedBlackTree(Iterable I){}
    public void leftRotate(RedBlackNode<T> node){
        if (node == root){

        }
        RedBlackNode<T> cRight = node.getRight();
        node.getParent().setLeft(cRight);
        node.setRight(cRight.getLeft());
        cRight.setLeft(node);
    }
    public void rightRotate(RedBlackNode<T> node){
        if (node == root){

        }
        RedBlackNode<T> cLeft = node.getLeft();
        node.getParent().setRight(cLeft);
        node.setLeft(cLeft.getRight());
        cLeft.setRight(node);
    }
    public void insert(RedBlackNode<T> node){
        System.out.println("Insertion des données : " + node.getData());
        insertRecursive(node,this.root);
    }

    private void insertRecursive(RedBlackNode<T> node, RedBlackNode<T> curr) {
        if (root == null) {
            root = node;
            root.setColor(RedBlackNode.Color.BLACK);
        } else {
            if (node.getData().compareTo(curr.getData()) < 0){ // inférieur
                if(curr.getLeft() == null){
                    curr.setLeft(node);
                    node.setParent(curr);
                } else {
                    insertRecursive(node,curr.getLeft());
                }
            }
            if (node.getData().compareTo(curr.getData()) > 0){ // inférieur
                if(curr.getRight() == null){
                    curr.setRight(node);
                    node.setParent(curr);
                } else {
                    insertRecursive(node,curr.getRight());
                }
            }
        }
    }
    private void fixUp(RedBlackNode node){
        while(node.getParent().getColor() == RedBlackNode.Color.RED){

        }
    }

    public void colorFlip(RedBlackNode<T> node){
        if (node.getColor() == RedBlackNode.Color.RED){
            node.setColor(RedBlackNode.Color.BLACK);
        } else {
            node.setColor(RedBlackNode.Color.RED);
        }
    }
    public boolean isRed(RedBlackNode<T> node){
        return node.getColor() == RedBlackNode.Color.RED;
    }
    public boolean isBlack(RedBlackNode<T> node){
        return !isRed(node);
    }
    public static void main(String[] args){

    }


}
