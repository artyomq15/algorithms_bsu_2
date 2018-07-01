package by.bsu.alg.coloring;

import java.util.Arrays;

public class GIS {

    private int[][] graph;
    private int[] color;

    public GIS(int[][] graph) {
        this.graph = graph;
        this.color = new int[graph.length];
    }

    public void doAlgorithm() {
        int c = 1;
        while (Arrays.stream(color).anyMatch(i -> i == 0)) {
            int[][] available = getAvailableGraph();
            while (checkAvailable(available)) {
                int minVertex = findMinVertex(available);
                color[minVertex] = c;
                available = getAvailableGraph();

                //System.out.println(Arrays.toString(color));
            }
            c++;
        }

    }

    private boolean checkAvailable(int[][] available) {
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < color.length; j++) {
                if (available[i][j] > 0 && color[i] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[][] getAvailableGraph() {
        int[][] available = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (color[i] == 0 && color[j] == 0) {
                    available[i][j] = graph[i][j];
                } else {
                    available[i][j] = 0;
                }
            }
        }
        return available;
    }

    private int findMinVertex(int[][] available) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < available.length; i++) {
            int count = 0;
            for (int j = 0; j < available.length; j++) {
                if (available[i][j] > 0) {
                    count++;
                }
            }
            if (count < min) {
                min = count;
                index = i;
            }
        }
        return index;
    }

    public int[] getColor() {
        return color;
    }
}
