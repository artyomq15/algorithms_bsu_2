package by.bsu.alg.t1;

import java.io.IOException;

public class First {
    public static void main(String[] args) throws IOException{
        int distance = 5;
        Integer[] positions = new Integer[]{6, 7, 12, 14};
        Integer[] costs = new Integer[]{5, 6, 5, 1};

        String dataPath = "t1/data.txt";
        FileHandler fileHandler = new FileHandler(dataPath);

        BillboardCost bc = new BillboardCost(fileHandler.getPositions(), fileHandler.getCosts(), distance);
        bc.computeMaxCost();
        System.out.println(bc.getMaxCost() + " - max cost");
        System.out.println(bc.getPositions() + " - positions");
        fileHandler.write(bc.getMaxCost(), bc.getPositions());

    }
}
