import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Prims {
    int [] [] matrix;
    int [] [] mstMatrix;
    Set<Integer> visited;

    public Prims(int[][] matrix) {
        this.matrix = matrix;
        this.mstMatrix = new int[matrix.length][matrix.length];
        this.visited = new HashSet<>();
    }

    private void mst(int startingNode) {
        // initial MST has no edges
        for (int r = 0; r < mstMatrix.length; r++) {
            for (int c = 0; c < mstMatrix.length; c++) {
                mstMatrix[r][c] = -1;
            }
        }
        // create a map to map each node to a weight
        Map<Integer, Integer> map = new HashMap<>();

        // set all the weights to infinity, initially
        for (int i = 0; i < mstMatrix.length; i++) {
            map.put(i, Integer.MAX_VALUE);
        }

        // set weight of starting node to zero so it will be picked first
        map.put(startingNode, 0);

        while (visited.size() != mstMatrix.length) {
            int minKeyValue = Integer.MAX_VALUE;
            int minNode = -1;
            // pick a node which has not been visited
            // had has min key value
            for (int i=0; i < mstMatrix.length; ++i) {
                if (!visited.contains(i) && map.get(i) < minKeyValue) {
                    minKeyValue = map.get(i);
                    minNode = i;
                }
            }
            visited.add(minNode);

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
        p.mst(0);
        printMatrix(p.matrix);
        printMatrix(p.mstMatrix);
    }
}
