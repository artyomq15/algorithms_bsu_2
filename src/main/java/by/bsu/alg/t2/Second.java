package by.bsu.alg.t2;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.util.Arrays;

public class Second {
    public static void main(String[] args) throws IOException{
        //Graph g = new Graph(Arrays.asList(1, 8, 6, 3, 6));
        //Graph g = new Graph(Arrays.asList(1, 7, 9, 10, 9));
        FileHandler fileHandler = new FileHandler();
        Graph g = new Graph(fileHandler.getGraph("t2/data.txt"));
        g.computeMaxCost();
        System.out.println(g.getMaxCost() + " - max cost");
        System.out.println(g.getPositions() + " - positions");
        fileHandler.write(g.getMaxCost(), g.getPositions());
        System.out.println("lolol");
    }
}
