package StructureDeDonnée;

import java.util.Arrays;

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
    public int indiceGauche(int i){
        return 2*i + 1;
    }
    public int droite(int i){
        return this.tas[2*i+1];
    }
    public int indiceDroite(int i){
        return 2*i+2;
    }
    public int parent(int i){
        return this.tas[i/2];
    }
    public int indiceParent(int i){
        return (i-1)/2;
    }

    public void EntasserMax(int i){
        int max;
        int l = indiceGauche(i);
        int r = indiceDroite(i);
        if (l < this.taille && this.tas[l] > this.tas[i]){
            max = l;
        } else max = i;
        if (r < this.taille && this.tas[r] >this.tas[max]){
            max = r;
        } if (max != i){
            System.out.println("Before : " + Arrays.toString(this.tas));
            swap(i,max);
            System.out.println("After : " + Arrays.toString(this.tas) + " ; swapped values : " + this.tas[i] + " and " + this.tas[max]);
            EntasserMax(max);
        }
    }
    public void EntasserMin(int i){
        int min;
        int l = indiceGauche(i);
        int r = indiceDroite(i);
        if (l <= this.taille && gauche(i) < this.tas[i]){
            min = l;
        } else min = i;
        if (r <= this.taille && droite(i)<this.tas[min]){
            min = r;
        } if (min != i){
            for (int j = 0; j < this.taille; j++) {
                System.out.print(this.tas[j] + " ");
            }
            System.out.println("Avant le swap" + Arrays.toString(this.tas));
            swap(i,min);
            System.out.println("Après le swap" + Arrays.toString(this.tas) + " ; swapped values : " + this.tas[i] + " and " + this.tas[min]);
            EntasserMin(min);
        }
        System.out.println("fin");
        // print les valeurs du tas
        for (int j = 0; j < this.taille; j++) {
            System.out.print(this.tas[j] + " ");
        }
    }
    public void construireTasMax(int[] a){
        this.tas = a;
        this.taille = a.length;
        for (int i = this.taille/2; i >= 0; i--) {
            EntasserMax(i);
        }
    }
    public void construireTasMax2(int[] a){
        this.tas = a;
        this.taille = a.length;
        for (int i = 1; i < this.taille; i++) {
            EntasserMax(i);
        }
    }
    public void swap(int i, int j){
        int temp = this.tas[i];
        this.tas[i] = this.tas[j];
        this.tas[j] = temp;
    }
    public void TriParTas(){
        System.out.println("\nDébut, Tablau initial : " + Arrays.toString(this.tas));
        construireTasMax(this.tas);
        for (int i = this.taille-1;i>= 1; i--){
            swap(0,i);
            this.taille--;
            EntasserMax(0);
        }
        this.taille = this.capacite;
        System.out.println("\n \n Final result of the sort : "+Arrays.toString(this.tas));
    }

    public int Max(){
        return this.tas[0];
    }
    public int ExtraireMax(){
        if (this.taille < 1){
            System.out.println("Erreur : tas vide");
        }
        int max = this.tas[0];
        setTas(Arrays.copyOfRange(this.tas,1,this.taille));
        this.taille--;
        EntasserMax(0);
        return max;
    }
        public void augmenterClé(int i, int clé){
            if (clé < this.tas[i]){
                System.out.println("Erreur : nouvelle clé plus petite que l'ancienne");
            }
            while (i > 0 && this.tas[indiceParent(i)] < this.tas[i]){
                swap(i,indiceParent(i));
                i = indiceParent(i);
            }
        }
        public void inserer(int clé){
            setTas(Arrays.copyOf(this.tas,this.taille+1));
            this.tas[this.taille-1] = Integer.MIN_VALUE;
            augmenterClé(this.taille-1,clé);
        }
        public void supprimer(int i){
            swap(i,this.taille-1);
            this.tas = Arrays.copyOf(this.tas,this.taille-1);
            this.taille--;
            EntasserMax(i);
        }
    }
