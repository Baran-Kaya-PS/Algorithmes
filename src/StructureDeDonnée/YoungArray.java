package StructureDeDonnée;

public class YoungArray {
    public int n;
    public int m;
    public int[][] a;
    public YoungArray(int[][] a){
        this.a = a;
        this.n = a.length;
        this.m = a[0].length;
        System.out.println("StructureDeDonnée.YoungArray créé : " + this.toString());
    }
    public YoungArray(int[] a) throws Exception {
        if (Math.sqrt(a.length) != (int) Math.sqrt(a.length)){
            throw new Exception("La taille du tableau n'est pas un carré parfait");
        }
        this.n = (int) Math.sqrt(a.length);
        this.m = (int) Math.sqrt(a.length);
        this.a = new int[this.n][this.m];
        int k = 0;
        for (int i = 0; i < this.n; i++){
            for (int j = 0; j < this.m; j++){
                this.a[i][j] = a[k];
                k++;
            }
        }
        System.out.println("StructureDeDonnée.YoungArray créé : " + this.toString());
    }
    public int get(int i, int j){
        return this.a[i][j];
    }
    public void set(int i, int j, int value){
        this.a[i][j] = value;
    }
    public int getN(){
        return this.n;
    }
    public int getM(){
        return this.m;
    }
    public int getRacine(){
        return this.a[0][0];
    }


}
