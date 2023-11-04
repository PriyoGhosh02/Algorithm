package Dijkstra;

import java.io.*;
import java.lang.*;
import java.util.*;

public class dijkstraAlgorithum {
    private static final int V = 5;

    int minNode(int dist[], boolean spSet[]) {
        int min = Integer.MAX_VALUE;
        int min_node = -1;

        for (int v = 0; v < V; v++) {
            if (!spSet[v] && dist[v] < min) {
                min = dist[v];
                min_node = v;
            }
        }

        return min_node;
    }

    void printPath(int src, int des, int parent[]) {
      if (src == des) {
             //System.out.print(src + " ");
             return;
        } else {
            printPath(src, parent[des], parent);
            System.out.print(des + " ");
        }
    }

    void dijkstra(int graph[][]) {
        int parent[] = new int[V];
        int dist[] = new int[V];
        boolean spSet[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            spSet[i] = false;
        }
        int src = 0;
        dist[src] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minNode(dist, spSet);
            spSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !spSet[v] && (dist[u] + graph[u][v]) < dist[v]) {
                    parent[v] = u;
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("Edge \tWeight \tPath");
        for (int i = 1; i < V; i++) {
            System.out.print(src + " - " + i + "\t" + dist[i] + "\t" + src + " ");
            printPath(src, i, parent);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        dijkstraAlgorithum dij = new dijkstraAlgorithum();
        int graph[][] = new int[][] {
            { 0, -2, 0, 6, 0 },
            { 2, 0, 3, 8, 5 },
            { 0, 3, 0, 0, 7 },
            { 6, 8, 0, 0, 9 },
            { 0, 5, 7, 9, 0 }
        };

        dij.dijkstra(graph);
    }
}
