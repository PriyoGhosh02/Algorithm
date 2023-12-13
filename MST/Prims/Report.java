package MST.Prims;

import java.util.*;

public class Report {
    static class Edge implements Comparable<Edge> {
        int vertex1;
        int vertex2;
        int weight;

        public Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        Report run = new Report();
        run.prim(graph);
    }

    public void prim(int[][] graph) {
        int vertices = graph.length;
        int[] parent = new int[vertices];
        int[] key = new int[vertices];
        boolean[] mstSet = new boolean[vertices];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mstSet, false);

        key[0] = 0; // Start with the first vertex

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(-1, 0, 0)); 

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int currentVertex = edge.vertex2;

            if (mstSet[currentVertex]) continue;

            mstSet[currentVertex] = true;

           
            for (int nextVertex = 0; nextVertex < vertices; nextVertex++) {
                if (graph[currentVertex][nextVertex] != 0 && !mstSet[nextVertex] &&
                        graph[currentVertex][nextVertex] < key[nextVertex]) {
                    key[nextVertex] = graph[currentVertex][nextVertex];
                    parent[nextVertex] = currentVertex;

                    priorityQueue.add(new Edge(currentVertex, nextVertex, graph[currentVertex][nextVertex]));
                }
            }
        }

        printMST(parent, graph);
    }

    private void printMST(int[] parent, int[][] graph) {
        System.out.println("Minimum Spanning Tree:");
        for (int i = 1; i < graph.length; i++) {
            System.out.println("Edge: " + parent[i] + " - " + i + " Weight: " + graph[parent[i]][i]);
        }
    }
}
