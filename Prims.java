import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Prims {
    private static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override public String toString() {
            return "(" + from + "," + to + ")";
        }
    }
    int [] [] matrix;
    int [] [] mstMatrix;
    Set<Integer> visited;

    int [] weights;

    public Prims(int[][] matrix) {
        this.matrix = matrix;
        this.mstMatrix = new int[matrix.length][matrix.length];
        this.visited = new HashSet<>();
        this.weights = new int[matrix.length];
    }

    public boolean hasEdge(int a, int b) {
        if (a == b) return false;
        boolean answer =  matrix[a][b] < Integer.MAX_VALUE &&
                matrix[a][b] > -1;
        return answer;
    }

    private Edge nextEdgeToVisit() {
        Edge nextEdge = null;
        // no more nodes to visit
        if (visited.size() == mstMatrix.length)
            return null;
        int minEdgeValue = Integer.MAX_VALUE;
        for (Integer visitedNode : visited) {
            for (int i = 0; i < matrix.length; i++) {
                if (!visited.contains(i)) {
                    if (hasEdge(visitedNode, i) && matrix[visitedNode][i] < minEdgeValue) {
                        minEdgeValue = matrix[visitedNode][i];
                        nextEdge = new Edge(visitedNode, i);
                    }
                }
            }
        }
        return nextEdge;
    }

    private void mst(int startingNode) {
        // initial MST has no edges
        for (int r = 0; r < mstMatrix.length; r++) {
            for (int c = 0; c < mstMatrix.length; c++) {
                mstMatrix[r][c] = -1;
            }
        }
        System.out.println("initial MST Matrix");
        printMatrix(mstMatrix);

        // set the starting node to visited so first edge will be added there
        visited.add(startingNode);

        // while not all nodes have been visited
        for (Edge nextEdge = nextEdgeToVisit(); nextEdge != null; nextEdge = nextEdgeToVisit() ) {
            System.out.println("Next edge to be added is: " + nextEdge);
            visited.add(nextEdge.to);
            mstMatrix[nextEdge.from][nextEdge.to] = matrix[nextEdge.from][nextEdge.to];
            mstMatrix[nextEdge.to][nextEdge.from] = matrix[nextEdge.to][nextEdge.from];
            printMatrix(mstMatrix);
            System.out.println("visited: " + visited);
            System.out.println();
        } 
    }

    public static void printMatrix(int[][] a) {
        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < a.length; c++) {
                String item = a[r][c] == -1 ? "-" : a[r][c] +"";
                System.out.printf("%2s ", item);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int [][] matrix = {
                //A B  C  D  E  F  G  H  I
                {-1, 7,-1,-1,-1,-1,-1,-1,15},
                { 7,-1, 5,-1,-1,-1,-1,-1,-1},
                {-1, 5,-1, 4, 2,-1, 3,-1,-1},
                {-1,-1, 4,-1, 1, 9,-1,-1,-1},
                {-1,-1, 2, 1,-1,-1,-1,-1,-1},
                {-1,-1,-1, 9,-1,-1, 5,12,-1},
                {-1,-1, 3,-1,-1, 5,-1,-1, 6},
                {-1,-1,-1,-1,-1,12,-1,-1,10},
                {15,-1,-1,-1,-1,-1, 6,10,-1}
        };

        Prims p = new Prims(matrix);
        printMatrix(p.matrix);
        p.mst(0);
        printMatrix(p.mstMatrix);
    }
}
