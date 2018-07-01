package by.bsu.alg.genetic;

import java.util.*;

public class GeneticAlgorithm {

    private Equation equation;
    private List<Integer> bestSolution;
    private List<List<Integer>> solutions;
    private List<Double> parentChance;
    private double mutationChance;
    private int maxIteration;
    private int iterations;

    public GeneticAlgorithm(Equation equation, int numberOfSolutions, double mutationChance, int maxIteration) {
        this.equation = equation;
        this.mutationChance = mutationChance;
        this.maxIteration = maxIteration;
        generateSolutions(numberOfSolutions);
    }

    public void doAlgorithm() {

        iterations = 0;
        while (solutions.stream().noneMatch(s -> computeError(s) == 0) && iterations < maxIteration) {
            iterations++;

            computeChanceToBeParent();
            //printSolutions(solutions);


            List<List<Integer>> parentsList = chooseParents();
            List<List<Integer>> childSolutions = reproduce(parentsList);

            //printSolutions(childSolutions);



            solutions = childSolutions;


        }

        bestSolution = solutions.stream().min(Comparator.comparingInt(this::computeError)).get();
        //System.out.println(bestSolution);


    }

    private List<List<Integer>> reproduce(List<List<Integer>> parentsList){
        List<List<Integer>> childSolutions = new ArrayList<>();
        double rand;

        for (List<Integer> parents : parentsList) {
            List<Integer> child = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i< equation.getCoefficientLength(); i++){
                rand = Math.random();

                if (rand < mutationChance){
                    child.add(random.ints(0, equation.getResult()).limit(1).findFirst().getAsInt());
                } else {
                    if (Math.random() < 0.5) {
                        child.add(solutions.get(parents.get(0)).get(i));
                    } else {
                        child.add(solutions.get(parents.get(1)).get(i));
                    }
                }
            }
            childSolutions.add(child);


        }
        return childSolutions;
    }

    private List<List<Integer>> chooseParents() {
        List<List<Integer>> parentsList = new ArrayList<>();

        for (int i = 0; i < solutions.size(); i++) {
            final List<Integer> parents = new ArrayList<>();

            do {
                parents.clear();

                int firstParent = chooseParentIndex();
                int secondParent = chooseParentIndex();

                while (secondParent == firstParent) {
                    secondParent = chooseParentIndex();
                }


                parents.add(firstParent);
                parents.add(secondParent);

            } while (parentsList.stream().anyMatch(p -> p.equals(parents)));

            parentsList.add(parents);

        }


        return parentsList;
    }

    private int chooseParentIndex() {
        double rand = Math.random();
        int i = 0;
        double res = 0;
        while (i < parentChance.size()) {
            if (rand < res + parentChance.get(i)) {
                return i;
            }
            res += parentChance.get(i);
            i++;
        }
        throw new RuntimeException("Invalid chances!");
    }

    private void computeChanceToBeParent() {
        parentChance = new ArrayList<>();
        double chanceSum = 0;
        for (List<Integer> s : solutions) {
            double inverseValueOfError = 1d / computeError(s);

            parentChance.add(inverseValueOfError);
            chanceSum += inverseValueOfError;
        }

        for (int i = 0; i < parentChance.size(); i++) {
            parentChance.set(i, parentChance.get(i) / chanceSum);
        }
    }

    private int computeError(List<Integer> solution){
        return Math.abs(equation.getResult() - equation.computeSolutionResult(solution));
    }

    private void generateSolutions(int number) {
        int length = equation.getCoefficientLength();
        int bound = equation.getResult();
        Random random = new Random();

        solutions = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            List<Integer> solution = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                solution.add(random.ints(0, bound).limit(1).findFirst().getAsInt());
            }
            solutions.add(solution);
        }
    }

    private void printSolutions(List<List<Integer>> solutions) {
        for (int i = 0; i < solutions.size(); i++) {
            for (Integer c : solutions.get(i)) {
                System.out.print(c + ", ");
            }
            System.out.println( " error: " + computeError(solutions.get(i)));
            System.out.println();
        }
        System.out.println("______");
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        List<Integer> c = equation.getCoefficients();
        List<Integer> p = equation.getPowers();
        int r = equation.getResult();

        if (maxIteration <= iterations){
            sb.append("Can't find precise solution.\n");
        } else {
            sb.append("Precise solution.\n");
        }
        sb.append("Equation: ");
        for (int i = 0; i < c.size(); i++){
            sb.append(c.get(i)).append("*").append("x").append(i).append("^").append(p.get(i));
            if (i == c.size()-1){
                sb.append(" = ");
            } else {
                sb.append(" + ");
            }
        }
        sb.append(r);
        sb.append("\nSolution: ").append(bestSolution);
        if (maxIteration <= iterations){
            sb.append("\nError: ").append(computeError(bestSolution)).append("\n");
        }
        sb.append("\n").append(iterations).append(" iterations\n");

        System.out.println(sb);
    }





}
