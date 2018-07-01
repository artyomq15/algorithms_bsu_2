package by.bsu.alg.coloring;

import java.util.Arrays;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        int n = 4;
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


        GIS gis = new GIS(graph);
        gis.doAlgorithm();
        print(graph);
        System.out.println(Arrays.toString(gis.getColor()));
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
