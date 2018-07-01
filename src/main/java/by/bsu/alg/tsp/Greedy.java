package by.bsu.alg.tsp;

import java.util.LinkedList;

public class Greedy {
    private int[][] graph;

    private int distance;
    private LinkedList<Integer> cities = new LinkedList<>();

    public Greedy(int[][] graph) {
        this.graph = graph;
    }

    public int getDistance() {
        return distance;
    }

    public LinkedList<Integer> getCities() {
        return cities;
    }

    public void doAlgorithm(){
        cities.add(0);

        while(cities.size() < graph.length){
            nextCity();
        }
        distance += graph[cities.getLast()][cities.getFirst()];
    }

    private void nextCity(){
        int min = Integer.MAX_VALUE;
        int lastCity = cities.getLast();
        int bestNew = lastCity;

        for (int i = 0; i < graph.length; i++){
            final int k = i;
            if (cities.stream().noneMatch(c -> c == k)){
                if (graph[lastCity][k] < min){
                    min = graph[lastCity][k];
                    bestNew = k;
                }
            }
        }
        cities.add(bestNew);
        distance += min;
    }
}
