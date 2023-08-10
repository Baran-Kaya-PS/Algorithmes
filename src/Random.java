import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
public class Random {
    LocalTime time = LocalTime.now();
    LocalDate date = LocalDate.now();
    String dayAndTime = time.toString() + date.toString();

    Long seed;
    int Modulus;
    List<Integer> randomList = new LinkedList<Integer>();
    public Random(long seed, int Modulus){
        this.Modulus = Modulus;
        this.seed = stringToInt(dayAndTime);
        randomList.add((int) seed);
    }
    public String generatedValues(){
        return randomList.toString();
    }
    public void setSeed(long a){
        this.seed = a;
    }
    public float Random(int a, int b){

        int lastValue = randomList.get(randomList.size()-1);
        int nextValue = (int) ((Math.pow(seed,seed%14) + lastValue)% this.Modulus);
        randomList.add((int) nextValue);
        return a+(float)nextValue/Modulus * (b-a);
    }
    public Long stringToInt(String str) {
        long result = 0;
        for (char c : str.toCharArray()) {
            result = (result * 31 + (int) c) % Modulus;
        }
        return result;
    }

}
