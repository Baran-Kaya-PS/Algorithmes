package StructureDeDonnée;

import java.util.Arrays;

public class File {
    int array[];
    public File(int values[]) {
        this.array = Arrays.copyOf(values, values.length);
    }
    public static int[] push(File file, int value) {
        file.array = Arrays.copyOf(file.array, file.array.length + 1);
        file.array[file.array.length - 1] = value;
        System.out.println(Arrays.toString(file.array));
        return file.array;
    }
    public static boolean isEmpty(File file) {
        return file.array.length == 0;
    }

    public static void pop(File file) {
        if (!isEmpty(file)) {
            int removedValue = file.array[0];
            System.out.println(removedValue + " à été retirée");
            System.arraycopy(file.array, 1, file.array, 0, file.array.length - 1); // décale les éléments sur la droite
            file.array = Arrays.copyOf(file.array, file.array.length - 1); // Réduire la taille du tab
        }
    }
    public static int get(File file) throws Exception {
        if (!isEmpty(file))
            return file.array[0];
        throw new Exception("array empty");
    }
    @Override
    public String toString() {
        return "File{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public static void main(String[] args) throws Exception {
        int[] values = {1, 2, 3, 4, 5};
        File myFile = new File(values);
        int taille = values.length;
        System.out.println(myFile.toString());
        for (int i = 0; i < taille; i++) {
            File.pop(myFile);
        }
    }
}
