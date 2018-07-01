package by.bsu.alg.tsp;

import java.util.ArrayList;
import java.util.List;

public class BranchAndBound {
    private static int M = -1;
    private int[][] graph;
    private int distance;

    private List<List<Integer>> zeros;
    List<List<Integer>> kommi;

    public BranchAndBound(int[][] graph) {
        this.graph = graph;
    }

    public int getDistance() {
        return distance;
    }


    public List<List<Integer>> getKommi() {
        return kommi;
    }

    public void doAlgorithm() {
        int graphSize = graph.length;
        kommi = new ArrayList<>();

        int[][] graphCopy1 = new int[graphSize][graphSize];
        for (int i = 0; i < graphSize; i++){
            for (int j = 0; j< graphSize; j++){
                graphCopy1[i][j] = graph[i][j];
            }
        }
        //print();
        int minBound = 0;

        while (kommi.size() < graphSize) {
            //print();
            zeros = new ArrayList<>();
            minBound += reduceGraph();
            //System.out.println(minBound);
            markZeros();
            //System.out.println(zeros);
            List<Integer> maxZero = findMaxZero();
            //System.out.println(maxZero);

            int[][] graphCopy = new int[graphSize][graphSize];
            for (int i = 0; i < graphSize; i++){
                for (int j = 0; j< graphSize; j++){
                    graphCopy[i][j] = graph[i][j];
                }
            }


            reduceZeroRowColumn(maxZero.get(0), maxZero.get(1));
            graph[maxZero.get(1)][maxZero.get(0)] = M;

            //print();
            if (maxZero.get(2) < minBound){
                graph = graphCopy;
                graph[maxZero.get(0)][maxZero.get(1)] = M;
            } else {
                kommi.add(maxZero);

                //System.out.println("::::::::::::");
                graph = graphCopy;
                // print();
                reduceZeroRowColumn(maxZero.get(0), maxZero.get(1));
                graph[maxZero.get(1)][maxZero.get(0)] = M;
                //print();
                //System.out.println("::::::::::::");
            }

            //System.out.println("________________________________");
            //System.out.println("kommi: " + kommi);
        }


        for (List<Integer> list: kommi){
            distance += graphCopy1[list.get(0)][list.get(1)];
        }
        //System.out.println(distance);

    }

    private void reduceZeroRowColumn(int row, int column){
        for (int i = 0; i < graph.length; i++){
            for (int j = 0; j < graph.length; j++){
                if (i == row || j == column){
                    graph[i][j] = M;
                }
            }
        }
    }

    private List<Integer> findMaxZero(){
        int maxValue = 0;
        int maxIndex = 0;
        for (int i = 0; i < zeros.size(); i++){
            if (zeros.get(i).get(2) > maxValue){
                maxValue = zeros.get(i).get(2);
                maxIndex = i;
            }
        }
        return zeros.get(maxIndex);
    }

    private void markZeros(){
        for (int i = 0; i < graph.length; i++){
            int minInRow = findRowMinIndexExceptZero(i);

            for (int j = 0; j < graph.length; j++){
                if (graph[i][j] == 0){
                    int minInColumn = findColumnMinIndexExceptZero(j);
                    List<Integer> zeroMark = new ArrayList<>();
                    zeroMark.add(i);
                    zeroMark.add(j);
                    int inRow = graph[i][minInRow];
                    if (inRow<0){
                        inRow = 0;
                    }
                    int inColumn = graph[minInColumn][j];
                    if (inColumn<0){
                        inColumn = 0;
                    }
                    zeroMark.add( inRow + inColumn);
                    zeros.add(zeroMark);
                }
            }
        }
    }

    private int reduceGraph(){
        return reduceRows() + reduceColumns();
    }

    private int reduceRows(){
        int rowSum = 0;
        for (int i = 0; i < graph.length; i++){
            int rowMin = graph[i][findRowMinIndex(i)];
            if (rowMin<0){
                rowMin = 0;
            }
            for (int j = 0; j<graph.length; j++){
                if (graph[i][j] != M) {
                    graph[i][j] -= rowMin;
                }
            }
            rowSum += rowMin;
        }

        //print();
        return rowSum;
    }

    private int reduceColumns(){
        int columnSum = 0;
        for (int i = 0; i < graph.length; i++){
            int columnMin = graph[findColumnMinIndex(i)][i];
            if (columnMin<0){
                columnMin = 0;
            }
            for (int j = 0; j<graph.length; j++){
                if (graph[j][i] != M) {
                    graph[j][i] -= columnMin;
                }
            }
            columnSum += columnMin;
        }

        //print();
        return columnSum;
    }

    private int findRowMinIndex(int i){
        int rowMin = Integer.MAX_VALUE;
        int rowMinIndex = 0;
        for (int j = 0; j < graph.length; j++){
            if (graph[i][j] != M && graph[i][j] < rowMin){
                rowMin = graph[i][j];
                rowMinIndex = j;
            }
        }
        return rowMinIndex;
    }

    private int findRowMinIndexExceptZero(int i){
        int rowMin = Integer.MAX_VALUE;
        int rowMinIndex = 0;
        for (int j = 0; j < graph.length; j++){
            if (graph[i][j] != M && graph[i][j] != 0 && graph[i][j] < rowMin){
                rowMin = graph[i][j];
                rowMinIndex = j;
            }
        }
        return rowMinIndex;
    }

    private int findColumnMinIndex(int i){
        int columnMin = Integer.MAX_VALUE;
        int columnMinIndex = 0;
        for (int j = 0; j < graph.length; j++){
            if (graph[j][i] != M && graph[j][i] < columnMin){
                columnMin = graph[j][i];
                columnMinIndex = j;
            }
        }
        return columnMinIndex;
    }

    private int findColumnMinIndexExceptZero(int i){
        int columnMin = Integer.MAX_VALUE;
        int columnMinIndex = 0;
        for (int j = 0; j < graph.length; j++){
            if (graph[j][i] != M && graph[j][i] != 0 && graph[j][i] < columnMin){
                columnMin = graph[i][j];
                columnMinIndex = j;
            }
        }
        return columnMinIndex;
    }

    private void print(){
        for (int i = 0; i < graph.length; i++){
            for (int j = 0; j<graph.length; j++){
                if (graph[i][j]>=0){
                    System.out.print(" " + graph[i][j] + " ");
                } else {
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
