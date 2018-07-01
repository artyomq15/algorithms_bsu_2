package by.bsu.alg.t1;

import java.util.ArrayList;
import java.util.List;

public class BillboardCost {
    private int distance;
    private List<Integer> positions;
    private List<Integer> costs;

    private List<Integer> maxCosts;
    private List<List<Integer>> maxCostPositions;

    public BillboardCost(List<Integer> positions, List<Integer> costs, int distance) {
        this.positions = positions;
        this.costs = costs;
        this.distance = distance;
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
        //начальные условия для стоимостей
        maxCosts.add(0);
        maxCosts.add(costs.get(0));
        //начальные условия для поззиций размещения
        maxCostPositions.add(new ArrayList<>());
        ArrayList<Integer> positionList = new ArrayList<>();
        positionList.add(positions.get(0));
        maxCostPositions.add(positionList);

        for (int i = 1; i < positions.size(); ++i) {
            //
            int prev = findMaxPreviousIndex(i);
            //стоимость текущего складывается с максимальнойстоимостью установки для найденного и его прошлых
            int addedCost = costs.get(i) + maxCosts.get(prev);

            int notAddedCost = maxCosts.get(i);

            if (addedCost > notAddedCost) {
                maxCosts.add(addedCost);
                positionList = new ArrayList<>(maxCostPositions.get(prev));
                positionList.add(positions.get(i));
                maxCostPositions.add(positionList);
            } else {
                maxCosts.add(notAddedCost);
                maxCostPositions.add(maxCostPositions.get(maxCostPositions.size()-1));
            }
        }
    }

    private int findMaxPreviousIndex (int currentIndex) {
        //т.к. в массивах макс стоимостей добавлено еще одно значение в начале (значение для нулевого индекса) возвращается значение
        //индекса из обычных стоимостей +1
        int current = positions.get(currentIndex);


        //находится ближайший к текущему билборд, отстоящий на более чем 5 миль
        for (int i = currentIndex - 1; i >= 0; --i) {
            if (current - positions.get(i) > distance){
                return i + 1;
            }
        }
        return 0;
    }
}
