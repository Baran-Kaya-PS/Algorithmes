package Tris;

import java.util.Arrays;

public class Dénombrement {
    int[] array;
    public Dénombrement(int array[]){this.array = array;};

    // A,B,k en param (a = array)
    public void Tri_Par_Dénombrement(int[] B, int k){
        int C[] = new int[k-1];
        for (int i = 0; i < k-1; i++){
            C[i] = 0; // initialisation du tableau C valeurs à 0
        }
        for (int j = 0; j < array.length; j++){ // parcours du tableau A
            C[array[j]] = C[array[j]] + 1; // incrémentation de C[i] pour chaque valeur de A
        }
        for (int i = 1; i < k-1; i++){
            C[i] = C[i] + C[i-1]; // Valeur de C[i] = C[i] + C[i-1] car C[i] contient le nombre d'éléments inférieurs ou égaux à i
        }
        for (int j = array.length-1; j >= 0; j--){
            B[C[array[j]]] = array[j]; // B[C[A[j]]] = A[j] car C[A[j]] contient le nombre d'éléments inférieurs ou égaux à A[j]
            C[array[j]] = C[array[j]] - 1; // décrémentation de C[A[j]]
        }
    }
    public static void main(String[] args) {
    }
}
