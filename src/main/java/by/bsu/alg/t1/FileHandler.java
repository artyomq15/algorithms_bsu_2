package by.bsu.alg.t1;

import java.io.*;
import java.util.*;

public class FileHandler {
    private List<Integer> positions = new ArrayList<>();
    private List<Integer> costs = new ArrayList<>();

    public FileHandler(String path) throws IOException{
        try (Scanner scanner = new Scanner(new FileInputStream(path))){
            List<Integer> data = new ArrayList<>();
            while (scanner.hasNext()) {
                data.add(Integer.parseInt(scanner.next()));
            }
            int n = data.size()/2;
            for (int i = 0; i< data.size(); ++i) {
                if (i < n) {
                    positions.add(data.get(i));
                } else {
                    costs.add(data.get(i));
                }
            }
        }
    }

    public void write(int maxCost, List<Integer> maxPositions) throws IOException{
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("t1/result.txt"), "utf-8"))) {
            writer.write("max cost: " + maxCost + "\n" + "positions: " +Arrays.toString(maxPositions.toArray()));
        }
    }

    public List<Integer> getPositions(){
        return positions;
    }
    public List<Integer> getCosts(){
        return costs;
    }
}
