import StructureDeDonnée.Tas;

import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] a = new int[]{5,3,1,17,10,84,19,6,22,9};
        int capacité = a.length;
        Tas t = new Tas(capacité);
        t.setTas(a);
        t.construireTasMax(a);
        System.out.println(t.Max());
        t.augmenterClé(8,100);
    }

    public static int[] permute_par_tri(int[] a){
        int n = a.length;
        int[] p = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            p[i] = r.nextInt((int) Math.pow(n,3));
        }
        return p;
    }
    public static void RandomisationDirecte(int[] A){
        Random r = new Random();
        int n = A.length;
        for (int i = 0; i < n ; i++){
            int j = r.nextInt(n);
            echanger(A,i,j);
        }
    }
    public static void echanger(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    public static int rechercheAleatoire(int[] a, int b){
        Random r = new Random();
        HashSet<Integer> set = new HashSet<>();
        int index = 0;
        int end = 0;
        while (a[index] != b && end != a.length-1){
            if (set.contains(index)){
                while (set.contains(index)) {
                    index = r.nextInt(0, a.length - 1);
                }
            }
            if (a[index] == b){
                return index;
            }
            set.add(index);
            end ++;
        }
        return -1;
    }
}
