package by.bsu.alg.tsp;

import by.bsu.alg.tsp.function.Function;

import java.util.*;

public class App {
    public static void main(String[] args) {

        int n = 10;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i == j){
                    graph[i][j] = -1;
                } else {
                    graph[i][j] = graph[j][i] = new Random().ints(1, 100).limit(1).findFirst().getAsInt();

                }
            }
        }


        print(graph);
        BruteForce bruteForce = new BruteForce(graph);
        bruteForce.doAlgorithm();

        Greedy greedy = new Greedy(graph);
        greedy.doAlgorithm();

        SimulatedAnnealing sa = new SimulatedAnnealing(graph, 10, 0.1, (t, iteration) -> t-0.04);
        sa.doAlgorithm();

        SimulatedAnnealing sa1 = new SimulatedAnnealing(graph, 100, 0.1, (t, iteration) -> t*0.98);
        sa1.doAlgorithm();


        System.out.println("Brute: " + bruteForce.getMinDistance() + " ; " + bruteForce.getMinCities());
        System.out.println("Greedy: " + greedy.getDistance() + " ; " + greedy.getCities());
        System.out.println("S.A.: " + sa.getDistance() + " ; " + sa.getCities() + " ; " + sa.getIterations());
        System.out.println("S.A.1: " + sa1.getDistance() + " ; " + sa1.getCities() + " ; " + sa1.getIterations());

    }

    private static void print(int[][] graph){
        for (int i = 0; i < graph.length; i++){
            for (int j = 0; j<graph.length; j++){
                if (graph[i][j]>=0){
                    System.out.print(" " + graph[i][j] + " ");
                } else {
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
