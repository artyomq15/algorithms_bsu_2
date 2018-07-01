package by.bsu.alg.genetic;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Equation {
    private List<Integer> coefficients;
    private List<Integer> powers;
    private int result;
    private int maxPower;

    public Equation(int result, int maxPower, Integer... coefficients){
        this.result = result;
        this.maxPower = maxPower;
        this.coefficients = new ArrayList<>();
        this.coefficients.addAll(Arrays.asList(coefficients));
        generatePowers();
    }
    public int computeSolutionResult(List<Integer> solution){
        if (this.coefficients.size() != solution.size()){
            throw new InvalidParameterException("Wrong length of solution");
        }

        int result = 0;
        for (int i = 0; i < coefficients.size(); i++){
            result += coefficients.get(i) * Math.pow(solution.get(i), powers.get(i));
        }

        return result;
    }

    public int getResult(){
        return result;
    }

    public int getCoefficientLength(){
        return coefficients.size();
    }

    private void generatePowers(){
        powers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < coefficients.size(); i++){
            powers.add(random.ints(1, maxPower).limit(1).findFirst().getAsInt());
        }
    }

    public void setPowers(Integer... powers){
        if (powers.length < coefficients.size()){
            throw new InvalidParameterException("Wrong length of powers");
        }
        this.powers.clear();
        this.powers.addAll(Arrays.asList(powers));
    }

    public List<Integer> getCoefficients() {
        return coefficients;
    }

    public List<Integer> getPowers() {
        return powers;
    }
}
