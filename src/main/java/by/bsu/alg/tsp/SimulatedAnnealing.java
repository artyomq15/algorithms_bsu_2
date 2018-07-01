package by.bsu.alg.tsp;

import by.bsu.alg.tsp.function.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {
    private int[][] graph;

    private Function function;
    private double maxT;
    private double minT;

    private int iterations;

    private List<Integer> cities;
    private int distance;


    public SimulatedAnnealing(int[][] graph, double maxT, double minT, Function function) {
        this.graph = graph;
        this.maxT = maxT;
        this.minT = minT;
        this.function = function;
    }

    public double getMaxT() {
        return maxT;
    }

    public double getMinT() {
        return minT;
    }

    public int getIterations() {
        return iterations;
    }

    public List<Integer> getCities() {
        return cities;
    }

    public int getDistance() {
        return distance;
    }

    public void doAlgorithm(){
        Greedy greedy = new Greedy(graph);
        greedy.doAlgorithm();
        cities = greedy.getCities();
        distance = computeDistance(cities);

        double t = maxT;
        while(t >= minT){
            iterations++;
            List<Integer> newPath = generatePath();
            int newDistance = computeDistance(newPath);
            int E = newDistance - distance;
            if (E <= 0){
                cities = newPath;
                distance = newDistance;
            } else {
                double p = Math.exp(-E/t);
                if (p > Math.random()){
                    cities = newPath;
                    distance = newDistance;
                }
            }

            t = function.T(t, iterations);
        }
    }

    private int computeDistance(List<Integer> cities){
        int count = 0;
        for (int i = 0; i < cities.size(); i++){
            int j;
            if (i < cities.size() - 1){
                j = i+1;
            } else {
                j = 0;
            }
            count += graph[cities.get(i)][cities.get(j)];
        }
        return count;
    }

    private List<Integer> generatePath(){
        List<Integer> newPath = new ArrayList<>(cities);
        int i = new Random().ints(0, cities.size()-3).limit(1).findFirst().getAsInt();
        int j = new Random().ints(i+2, cities.size()-1).limit(1).findFirst().getAsInt();
        int start = i;
        while(j>start){
            newPath.set(i+1, cities.get(j));
            j--;
            i++;
        }
        return newPath;
    }





}
