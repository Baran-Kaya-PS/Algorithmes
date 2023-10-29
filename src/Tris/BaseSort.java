package Tris;

import java.util.Arrays;

public class BaseSort {
    int[] array = {123,231,512,511,933,931,12,3,6,1234,43,76,8761};

    public BaseSort() {
        this.array = array;
    }

    public void radixSort() {
        int maxNumber = maxNumber();
        int nombreDeChiffre = figureNumber(maxNumber);
        System.out.println("Nombre maximal: " + maxNumber);
        System.out.println("Nombre de chiffres du nombre maximal: " + nombreDeChiffre);

        for (int position = 1; position <= nombreDeChiffre; position++) {
            System.out.println("\nTri par le chiffre à la position: " + position);
            countingSortByDigit(position);
            System.out.println("Tableau après le tri par le chiffre à la position " + position + ": " + Arrays.toString(array));
        }
    }

    public void countingSortByDigit(int position) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            int digit = getDigitAtPosition(array[i], position); // on récupère le nombre à la position donnée
            count[digit]++; // on augmente le nombre d'occurence de la valeur
        }

        System.out.println("Tableau de comptage après comptage des chiffres: " + Arrays.toString(count));

        for (int i = 1; i < 10; i++) { // on met à jour les positions cumulatives
            count[i] += count[i - 1];
        }

        System.out.println("Tableau de comptage après mise à jour des positions cumulatives: " + Arrays.toString(count));

        for (int i = n - 1; i >= 0; i--) {
            int digit = getDigitAtPosition(array[i], position); // on récupère le nombre à la position donnée
            output[count[digit] - 1] = array[i]; //
            count[digit]--;
        }
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }

    private int figureNumber(int number) {
        return String.valueOf(number).length();
    }

    private int maxNumber() {
        int maxValue = 0;
        for (int i = 0; i < array.length; i++) { // Petite correction: array.length au lieu de array.length-1
            if (array[i] > maxValue) maxValue = array[i];
        }
        return maxValue;
    }

    private int getDigitAtPosition(int number, int position) {
        return (number / (int) Math.pow(10, position - 1)) % 10;
    }

    public static void main(String[] args) {
        BaseSort sorter = new BaseSort();
        System.out.println("Tableau initial: " + Arrays.toString(sorter.array));
        sorter.radixSort();
        System.out.println("\nTableau trié: " + Arrays.toString(sorter.array));
    }
}
