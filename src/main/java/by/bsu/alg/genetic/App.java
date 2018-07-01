package by.bsu.alg.genetic;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        Random r = new Random();
        int result = r.ints(50, 200).limit(1).findFirst().getAsInt();
        Integer[] coeffs = new Integer[3];
        for (int i = 0; i < coeffs.length; i++) {
            coeffs[i] = r.ints(1, 10).limit(1).findFirst().getAsInt();
        }

        int[] sol = new int[]{10, 100, 500};
        for (int j = 0; j<3; j++) {

            Equation equation = new Equation(result, 4, coeffs);
            equation.setPowers(2, 2, 1);
            GeneticAlgorithm ga = new GeneticAlgorithm(equation, sol[j], 0.05, 1000);

            ga.doAlgorithm();
            ga.print();
        }
    }
}
