package by.bsu.alg.t2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public List<Integer> getGraph(String path) throws IOException{
        List<Integer> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(path))){
            while (scanner.hasNext()) {
                data.add(Integer.parseInt(scanner.next()));
            }
        }
        return data;
    }

    public void write(int maxCost, List<Integer> maxPositions) throws IOException{
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("t2/result.txt"), "utf-8"))) {
            writer.write("max cost: " + maxCost + "\n" + "positions: " + Arrays.toString(maxPositions.toArray()));
        }
    }
}
