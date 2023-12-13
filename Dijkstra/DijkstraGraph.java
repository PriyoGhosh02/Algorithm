package Dijkstra;

import java.util.Scanner;

public class DijkstraGraph {
    private int[][] adjacencyMatrix;
    private int numVertices;

    public DijkstraGraph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyMatrix = new int[numVertices][numVertices];
    }

    public void addEdge(int source, int destination, int pathCost) {
        adjacencyMatrix[source][destination] = pathCost;
        adjacencyMatrix[destination][source] = pathCost;
    }

    public void dijkstra(int source) {
        int[] distance = new int[numVertices];
        int[] previousVertex = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            distance[i] = Integer.MAX_VALUE;
            previousVertex[i] = -1; 
            visited[i] = false;
        }

        distance[source] = 0;

        for (int count = 0; count < numVertices - 1; count++) {
            int u = minDistance(distance, visited);
            visited[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && adjacencyMatrix[u][v] != 0 && distance[u] != Integer.MAX_VALUE &&
                        distance[u] + adjacencyMatrix[u][v] < distance[v]) {
                    distance[v] = distance[u] + adjacencyMatrix[u][v];
                    previousVertex[v] = u;
                }
            }
        }

        for (int i = 0; i < numVertices; i++) {
            if (i != source) {
                printShortestPath(source, i, previousVertex, distance[i]);
            }
        }
    }

    private void printShortestPath(int source, int destination, int[] previousVertex, int totalCost) {
        System.out.print("Shortest path from " + source + " to " + destination + ": ");
        printPath(source, destination, previousVertex);
        System.out.println(" (Total Cost: " + totalCost + ")");
    }

    private void printPath(int source, int current, int[] previousVertex) {
        if (current == -1) {
            return;
        }
        printPath(source, previousVertex[current], previousVertex);
        System.out.print(current + " ");
    }

    private int minDistance(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < numVertices; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();

        DijkstraGraph graph = new DijkstraGraph(numVertices);

        System.out.println("Enter the adjacency matrix (0 for no connection):");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                graph.addEdge(i, j, scanner.nextInt());
            }
        }

        System.out.print("Enter the source vertex: ");
        int sourceVertex = scanner.nextInt();

        graph.dijkstra(sourceVertex);

        scanner.close();
    }
}
