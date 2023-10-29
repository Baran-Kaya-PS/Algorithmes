package StructureDeDonnée;

import java.util.Arrays;

public class DoublePile {
    private int[] array;
    private int topA;
    private int topB;

    public DoublePile(int capacity) {
        array = new int[capacity];
        topA = 0;
        topB = capacity - 1;
    }

    public void pushA(int value) {
        if (topA > topB) {
            throw new RuntimeException("PileA est pleine");
        }
        array[topA++] = value;
    }

    public int popA() {
        if (topA == 0) {
            throw new RuntimeException("PileA est vide");
        }
        return array[--topA];
    }

    public void pushB(int value) {
        if (topB < topA) {
            throw new RuntimeException("PileB est pleine");
        }
        array[topB--] = value;
    }

    public int popB() {
        if (topB == array.length - 1) {
            throw new RuntimeException("PileB est vide");
        }
        return array[++topB];
    }

    @Override
    public String toString() {
        return "DoublePile{" +
                "array=" + Arrays.toString(array) +
                ", topA=" + topA +
                ", topB=" + topB +
                '}';
    }

    public static void main(String[] args) {
        DoublePile doublePile = new DoublePile(10);
        doublePile.pushA(1);
        doublePile.pushA(2);
        doublePile.pushA(3);
        doublePile.pushB(10);
        doublePile.pushB(9);
        doublePile.pushB(8);

        System.out.println(doublePile);

        doublePile.popA();
        doublePile.popB();

        System.out.println(doublePile);
    }
}
