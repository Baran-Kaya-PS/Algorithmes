import StructureDeDonnée.Tas;

import java.util.HashSet;
import java.util.Random;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {

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
