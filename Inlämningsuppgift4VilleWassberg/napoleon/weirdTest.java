package napoleon;

import java.util.ArrayList;

public class weirdTest {
    private ArrayList<Integer> heltal = new ArrayList<>();
    weirdTest() {
        for(int i = 0; i < 5; i++) {
            heltal.add(i);
        }
        heltal.remove(3);
        for(int k : heltal){
            System.out.println(k);
        }
    }
    public static void main(String[] args) {
        new weirdTest();
    }
}
