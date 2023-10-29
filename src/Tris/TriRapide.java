package Tris;

import java.util.Arrays;

public class TriRapide {
    int[] array;

    public TriRapide(int[] array){
        this.array = array;
    }

    public void sort(int p, int r){
        if (p < r){
            printArray("Avant partition", p, r);
            int q = PartitionDeHoareCorrigé(p,r);
            printArray("Après partition", p, r);
            sort(p,q);
            sort(q+1,r);
        }
    }

    private int PartitionDeHoareCorrigé(int p, int r){
        int x = array[p]; // pivot
        System.out.println("Pivot actuel : " + x);
        int i = p;
        int j = r;
        while (true){
            while (array[j] > x) {
                j--;
            }
            while (array[i] < x) {
                i++;
            }
            if (i < j){
                System.out.println("Échange du pivot " + x + " avec la valeur " + array[j] + " (indice " + j + ") car pivot > " + array[j]);
                Permute(i,j);
                printPermutation(i, j);
            } else {
                printArray("Tableau après avoir placé le pivot", p, r);
                return j;
            }
        }
    }

    private void Permute(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void printArray(String message, int p, int r) {
        System.out.print(message + " [" + p + ", " + r + "]: ");
        for (int k = p; k <= r; k++) {
            System.out.print(array[k] + " ");
        }
        System.out.println();
    }

    private void printPermutation(int i, int j) {
        System.out.println("Permutation des éléments aux indices " + i + " et " + j + "\n valeur du tableau : [" + Arrays.toString(array) + "]");
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        TriRapide tri = new TriRapide(arr);
        tri.sort(0, arr.length - 1);
        System.out.println("Tableau trié : " + Arrays.toString(arr));
    }
}
