package ru.suvorov.graphiox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MathGraph {
    private ArrayList<ArrayList<Double>> matrix = new ArrayList<>();

    public MathGraph() {
    }

    public MathGraph(String str) throws Exception { //todo обработать ошибки
        if (isList(str)) {
            str = convertToMatrix(str);
        }

        Scanner scanner = new Scanner(str);
        int length;

        length = scanner.nextInt();

        for (int i = 0; i < length; i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < length; j++) {
                double d = scanner.nextDouble();
                matrix.get(i).add(d);
            }
        }

    }

    public void addVertex() {
        matrix.add(new ArrayList<>());
        for (int i = 0; i < matrix.size(); i++) {
            matrix.get(i).add(0.0);
            matrix.get(matrix.size() - 1).add(0.0);
        }
    }

    public void deleteVertex(int vertex) { //todo обработать ошибки
        matrix.remove(vertex - 1);
        for (int i = 0; i < matrix.size(); i++) {
            matrix.get(i).remove(vertex - 1);
        }
    }

    public int getVertexCount() {
        return matrix.size();
    }

    public void addEdge(int vertex1, int vertex2, double weight) {
        matrix.get(vertex1 - 1).set(vertex2 - 1, weight);
        matrix.get(vertex2 - 1).set(vertex1 - 1, weight);
    }

    public void deleteEdge(int vertex1, int vertex2) {
        matrix.get(vertex1 - 1).set(vertex2 - 1, 0.0);
        matrix.get(vertex2 - 1).set(vertex1 - 1, 0.0);
    }

    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = i; j < matrix.size(); j++) {
                if (matrix.get(i).get(j) != 0)
                    count++;
            }
        }
        return count;
    }

    public boolean isaAdjacentEdges(int vertex1, int vertex2) {
        return matrix.get(vertex1 - 1).get(vertex2 - 1) != 0;
    }

    public double getEdgeWeight(int vertex1, int vertex2) {
        return matrix.get(vertex1 - 1).get(vertex2 - 1);
    }

    public String convertToList() {
        String str = String.format("%d", matrix.size());
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i).get(j) != 0) {
                    str += String.format("\n%d %d %.2f", i + 1, j + 1, matrix.get(i).get(j));
                }
            }
        }
        return str;
    }

    public String convertToMatrix(String list) {
        Scanner scanner = new Scanner(list);

        int length = scanner.nextInt();
        Double[][] newmatrix = new Double[length][length];

        while (scanner.hasNext()) {
            int a = scanner.nextInt() - 1;
            int b = scanner.nextInt() - 1;
            double c = scanner.nextDouble();
            newmatrix[a][b] = c;
            newmatrix[b][a] = c;
        }

        String str = String.format("%d", length);

        for (int i = 0; i < length; i++) {
            str += "\n";
            for (int j = 0; j < length; j++) {
                if (newmatrix[i][j] == null) {
                    str += 0 + " ";
                } else {
                    str += String.format("%.2f ", newmatrix[i][j]);
                }
            }
        }
        return str;
    }

    private boolean isList(String string) {
        Scanner scanner = new Scanner(string);
        scanner.nextInt();
        if (scanner.nextInt() == 0) return false;
        return true;
    }

    @Override
    public String toString() {
        String str = String.format("%d", matrix.size());
        String matrixStr = "";
        for (int i = 0; i < matrix.size(); i++) {
            matrixStr += "\n";
            for (int j = 0; j < matrix.size(); j++) {
                matrixStr += matrix.get(i).get(j) + " ";
            }

        }
        return str + matrixStr;
    }
}
