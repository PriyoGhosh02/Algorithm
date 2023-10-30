package MST.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Edge {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    private int V;
    private List<Edge> edges;

    public Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    public List<Edge> kruskalMST() {
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
        List<Edge> mst = new ArrayList<>();
        int[] parent = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        for (Edge edge : edges) {
            int root1 = find(parent, edge.source);
            int root2 = find(parent, edge.destination);

            if (root1 != root2) {
                mst.add(edge);
                parent[root1] = root2;
            }
        }

        return mst;
    }

    private int find(int[] parent, int node) {
        while (parent[node] != node) {
            node = parent[node];
        }
        return node;
    }

    public int findSecondBestMSTWeight(List<Edge> mst) {
        int secondBestWeight = Integer.MAX_VALUE;

        for (Edge excludedEdge : mst) {
            List<Edge> modifiedEdges = new ArrayList<>(edges);
            modifiedEdges.remove(excludedEdge);

            Graph modifiedGraph = new Graph(V);
            modifiedGraph.edges = modifiedEdges;

            List<Edge> newMST = modifiedGraph.kruskalMST();
            int newMSTWeight = calculateMSTWeight(newMST);

            if (newMSTWeight < secondBestWeight && newMSTWeight != calculateMSTWeight(mst)) {
                secondBestWeight = newMSTWeight;
            }
        }

        return secondBestWeight;
    }

    public int calculateMSTWeight(List<Edge> mst) {
        int mstWeight = 0;
        for (Edge edge : mst) {
            mstWeight += edge.weight;
        }
        return mstWeight;
    }
}

public class secondBasedMST {
    public static void main(String[] args) {
        int V = 4;
        Graph graph = new Graph(V);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        List<Edge> mst = graph.kruskalMST();
        System.out.println("Minimum Spanning Tree (MST):");
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + " Weight: " + edge.weight);
        }

        int secondBestWeight = graph.findSecondBestMSTWeight(mst);
        System.out.println("Second-Best Minimum Spanning Tree Weight: " + secondBestWeight);
    }
}
