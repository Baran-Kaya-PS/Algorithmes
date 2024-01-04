package Programmation_dynamique;

public class CouperBarre {
    public static void main(String[] args) {

    }
    public int couperBarre(int[] p, int n){
        if (n == 0) return 0;
        int q = Integer.MIN_VALUE;
        for (int i = 0 ; i < n ; i++){
            q = Math.max(q,p[i]+couperBarre(p,n-1));
        }
        return q;
    }
}
