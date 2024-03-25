package StructureDeDonnée;

public class RedBlackNode<T>{
    public enum Color {
        RED,BLACK;
    }
    public RedBlackNode(){}
    private Color color; // Rouge == true, Black == false
    private T data;
    private RedBlackNode<T> left,right,parent;
    public T getData(){
        return this.data;
    }
    public RedBlackNode<T> getLeft(){
        return this.left;
    }

    public RedBlackNode<T> getRight() {
        return right;
    }

    public RedBlackNode<T> getParent() {
        return parent;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(RedBlackNode<T> left) {
        this.left = left;
    }

    public void setParent(RedBlackNode<T> parent) {
        this.parent = parent;
    }

    public void setRight(RedBlackNode<T> right) {
        this.right = right;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return "RedBlackNode{" +
                "color=" + color +
                ", data=" + data +
                ", left=" + left +
                ", right=" + right +
                ", parent=" + parent +
                '}';
    }
    public static void main(String[] args){
        // works
    }
}
