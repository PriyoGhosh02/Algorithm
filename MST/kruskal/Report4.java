package MST.kruskal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Report4 {

    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices, edges;
        Edge[] edgeList;

        Graph(int vertices, int edges) {
            this.vertices = vertices;
            this.edges = edges;
            edgeList = new Edge[edges];
        }

        void addEdge(int source, int destination, int weight, int index) {
            edgeList[index] = new Edge(source, destination, weight);
        }
    }

    static class Kruskal {

        Graph graph;

        Kruskal(Graph graph) {
            this.graph = graph;
        }

        int find(int[] parent, int i) {
            if (parent[i] == i) {
                return i;
            }
            return find(parent, parent[i]);
        }

        void union(int[] parent, int[] rank, int x, int y) {
            int rootX = find(parent, x);
            int rootY = find(parent, y);

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }

        int[][] kruskal() {
            int[][] result = new int[graph.vertices - 1][3];
            Arrays.sort(graph.edgeList, Comparator.comparingInt(o -> o.weight));

            int[] parent = new int[graph.vertices];
            int[] rank = new int[graph.vertices];
            for (int i = 0; i < graph.vertices; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            int resultIndex = 0;
            int edgeIndex = 0;

            while (resultIndex < graph.vertices - 1) {
                Edge nextEdge = graph.edgeList[edgeIndex++];
                int x = find(parent, nextEdge.source);
                int y = find(parent, nextEdge.destination);

                if (x != y) {
                    result[resultIndex++] = new int[] { nextEdge.source, nextEdge.destination, nextEdge.weight };
                    union(parent, rank, x, y);
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        Graph graph = new Graph(vertices, edges);

        System.out.println("Enter details for each edge (source destination weight):");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight, i);
        }

        Kruskal kruskal = new Kruskal(graph);
        int[][] mst = kruskal.kruskal();

        // Display the Minimum Spanning Tree
        System.out.println("Minimum Spanning Tree:");
        int total = 0;
        for (int[] edge : mst) {
            System.out.println(edge[0] + " - " + edge[1] + " : " + edge[2]);
            total = total + edge[2];
        }
        System.out.println("Total=" + total);
        scanner.close();
    }
}
