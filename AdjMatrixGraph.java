// Dibyendu Debnath

import java.util.*;

public class AdjMatrixGraph {

    public static final int DEF_VERT_COUNT = 10;   // Default number of vertices
    public double[][] adjMatrix;                   // Adjacency matrix representation
    private int vertexCount;                       // Number of vertices

    // Default constructor
    public AdjMatrixGraph() {
        this(DEF_VERT_COUNT);
    }

    // Parameterized constructor
    public AdjMatrixGraph(int size) {
        if (size <= 0) {
            size = DEF_VERT_COUNT;
        }
        this.vertexCount = size;
        this.adjMatrix = new double[this.vertexCount][this.vertexCount];

        // Initialize matrix values to 0.0
        for (int i = 0; i < this.vertexCount; i++) {
            for (int j = 0; j < this.vertexCount; j++) {
                this.adjMatrix[i][j] = 0.0;
            }
        }
    }

    // Adds an edge between two vertices with a given weight
    public void addEdge(int fromVertex, int toVertex, double weight) {
        if (!isValidIndex(fromVertex) || !isValidIndex(toVertex)) {
            System.out.println("addEdge error: invalid vertex index (" + fromVertex + " -> " + toVertex + ")");
            return;
        }
        this.adjMatrix[fromVertex][toVertex] = weight;
    }

    // Prints the adjacency matrix
    public void printAdjMatrix() {
        for (int i = 0; i < this.vertexCount; i++) {
            for (int j = 0; j < this.vertexCount; j++) {
                System.out.print(String.format("%.1f", this.adjMatrix[i][j]));
                if (j < this.vertexCount - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }

    // Depth First Search starting from vertex 0
    public void printDFS() {
        printDFSFrom(0);
    }

    // Depth First Search starting from a given vertex
    public void printDFSFrom(int start) {
        if (!isValidIndex(start)) {
            System.out.println("printDFSFrom error: invalid start index " + start);
            return;
        }
        boolean[] visited = new boolean[this.vertexCount];
        dfsRecursive(start, visited);
    }

    // Recursive DFS helper
    private void dfsRecursive(int v, boolean[] visited) {
        visited[v] = true;
        System.out.println(v);
        for (int nbr = 0; nbr < this.vertexCount; nbr++) {
            if (!visited[nbr] && this.adjMatrix[v][nbr] != 0.0) {
                dfsRecursive(nbr, visited);
            }
        }
    }

    // Breadth First Search starting from vertex 0
    public void printBFS() {
        printBFSFrom(0);
    }

    // Breadth First Search starting from a given vertex
    public void printBFSFrom(int start) {
        if (!isValidIndex(start)) {
            System.out.println("printBFSFrom error: invalid start index " + start);
            return;
        }

        boolean[] visited = new boolean[this.vertexCount];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.println(v);
            for (int nbr = 0; nbr < this.vertexCount; nbr++) {
                if (!visited[nbr] && this.adjMatrix[v][nbr] != 0.0) {
                    visited[nbr] = true;
                    q.add(nbr);
                }
            }
        }
    }

    // Validates a vertex index
    private boolean isValidIndex(int idx) {
        return idx >= 0 && idx < this.vertexCount;
    }
}
