import java.util.Arrays;
import java.util.Random;

public class TriRapideRandomisé {
    int array[];
    public TriRapideRandomisé(int[] array){this.array = array;}

    public void sort(int p, int r){
        if (p < r){
            printArray("Avant partition", p, r);
            int q = Partition(p,r);
            printArray("Après partition", p, r);
            sort(p,q-1);
            sort(q+1,r);
        }
    }
    public int Partition_randomisé(int p, int r){
        int i = new Random().nextInt(r-p+1);
        Permute(i,r);
        return Partition(p,r);
    }
    private int Partition(int p, int r) {
        int x = array[r];
        int i = p-1;
        for (int j = p; j <= r-1; j++){
            if (array[j] <= x){
                i++;
                Permute(i,j);
                printPermutation(i, j);
            }
        }
        Permute(i+1,r);
        printPermutation(i+1, r);
        return i+1;
    }

    private void printPermutation(int i, int r) {
        System.out.println("Permutation des éléments aux indices " + i + " et " + r + "\n valeur du tableau : [" + Arrays.toString(array) + "]");
    }

    private void Permute(int i, int r) {
        int temp = array[i];
        array[i] = array[r];
        array[r] = temp;
    }
    private void printArray(String message, int p, int r) {
        System.out.print(message + " [" + p + ", " + r + "]: ");
        for (int k = p; k <= r; k++) {
            System.out.print(array[k] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        TriRapideRandomisé tri = new TriRapideRandomisé(arr);
        tri.sort(0, arr.length - 1);
        System.out.println("Tableau trié : " + Arrays.toString(arr));
    }
}