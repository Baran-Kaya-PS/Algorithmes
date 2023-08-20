public class Tas {
    private int[] tas;
    private int taille;
    private int capacite;

    public Tas(int capacite){
        this.capacite = capacite;
        this.taille = 0;
        this.tas = new int[capacite];
    }

    public void setTas(int[] tas){
        this.tas = tas;
        setTaille(tas.length);
    }
    public void setTaille(int taille){
        this.taille = taille;
    }

    public int getTaille(){
        return this.taille;
    }

    public int getCapacite(){
        return this.capacite;
    }

    public int get(int index){
        return this.tas[index];
    }

    public int getRacine(){
        return this.tas[0];
    }

    public int gauche(int i){
        return this.tas[2*i];
    }
    public int droite(int i){
        return this.tas[2*i+1];
    }
    public int parent(int i){
        return this.tas[i/2];
    }

    public void EntasserMax(int i){
        int max;
        int l = gauche(i);
        int r = droite(i);
        if (l <= this.taille && this.tas[l] > this.tas[i]){
            max = l;
        } else max = i;
        if (r <= this.taille && this.tas[r]>this.tas[max]){
            max = r;
        } if (max != i){
            swap(i,max);
            EntasserMax(max);
        }
    }
    public void EntasserMin(int i){
        int min;
        int l = gauche(i);
        int r = droite(i);
        if (l <= this.taille && this.tas[l] < this.tas[i]){
            min = l;
        } else min = i;
        if (r <= this.taille && this.tas[r]<this.tas[min]){
            min = r;
        } if (min != i){
            System.out.println("swap");
            // print les valeurs du tas
            for (int j = 0; j < this.taille; j++) {
                System.out.print(this.tas[j] + " ");
            }
            swap(i,min);
            EntasserMin(min);
        }
    }

    public void EntasserIteratif(int i){
        int max;
        int l = gauche(i);
        int r = droite(i);
        while (i <= this.taille){
            if (l <= this.taille && this.tas[l] > this.tas[i]){
                max = l;
            } else max = i;
            if (r <= this.taille && this.tas[r]>this.tas[max]){
                max = r;
            } if (max != i){
                swap(i,max);
                i = max;
                l = gauche(i);
                r = droite(i);
            } else break;
        }
    }

    public void construireTasMax(int[] a){
        this.tas = a;
        this.taille = a.length;
        for (int i = this.taille/2; i >= 0; i--) {
            EntasserMax(i);
        }
    }


    public void swap(int i, int j){
        int temp = this.tas[i];
        this.tas[i] = this.tas[j];
        this.tas[j] = temp;
    }


}
