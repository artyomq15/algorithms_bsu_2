package by.bsu.alg.tsp;

import java.util.Random;

public class F {
    public static void main(String[] args) {

        for (int i = 0; i< 10;i++) {
            System.out.println(new Random().ints(0, 9).limit(1).findFirst().getAsInt());
        }
    }
}
