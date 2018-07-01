package by.bsu.alg.t2;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Integer> graphCosts;

    private List<Integer> maxCosts;
    private List<List<Integer>> maxCostPositions;

    public Graph(List<Integer> graphCosts) {
        this.graphCosts = graphCosts;
        maxCosts = new ArrayList<>();
        maxCostPositions = new ArrayList<>();
    }

    public int getMaxCost() {
        return maxCosts.get(maxCosts.size() - 1);
    }
    public List<Integer> getPositions() {
        return maxCostPositions.get(maxCostPositions.size() - 1);
    }

    public void computeMaxCost () {
        //добавляются 1 и 2 узлы, т.к. они обязательно будут учавствовать в ответе
        maxCosts.add(graphCosts.get(0));
        maxCosts.add(graphCosts.get(1));
        ArrayList<Integer> positionList = new ArrayList<>();
        positionList.add(0);
        maxCostPositions.add(positionList);
        positionList = new ArrayList<>();
        positionList.add(1);
        maxCostPositions.add(positionList);

        for (int i = 2; i < graphCosts.size(); ++i) {
            int addedCost = graphCosts.get(i) + maxCosts.get(i - 2);
            int notAddedCost = maxCosts.get(i - 1);

            if (addedCost > notAddedCost) {
                maxCosts.add(addedCost);
                positionList = new ArrayList<>(maxCostPositions.get(i - 2));
                positionList.add(i);
                maxCostPositions.add(positionList);
            } else {
                maxCosts.add(notAddedCost);
                maxCostPositions.add(maxCostPositions.get(maxCostPositions.size()-1));
            }
            System.out.println(maxCosts);
            System.out.println(maxCostPositions);
        }
    }
}
