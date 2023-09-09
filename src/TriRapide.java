public class TriRapide {
    int[] array;

    public TriRapide(int[] array){
        this.array = array;
    }
    public void sort(int p, int r){
        if (p < r){
            int q = Partition(p,r); 
            sort(p,q-1);
            sort(q+1,r);
        }
    }
    private int Partition(int p, int r) {
        int x = array[r];
        int i = p-1;
        for (int j = p; j < r-1;j++){
            if (array[j] <= x){
                i++;
                Permute(i,j);
            }
        }
        Permute(i+1,r);
        return i+1;
    }

    private void Permute(int i, int j) {
        int temp = i;
        array[i] = array[j];
        array[j] = temp;
    }
}
