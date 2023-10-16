package TopologicalWithBFS;

import java.util.*;

public class topologicalSortBFS {
    
  public static void main(String[] args) {
   int numVertices = 6;
        Graph graph = new Graph(numVertices);
        
        // Add edges to the graph
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        
        // Perform topological sort
        List<Integer> result = topologicalSort(graph);
        
        // Print the sorted order
        System.out.println("Topological Sort Order:");
        for (int vertex : result) {
            System.out.print(vertex + " ");
        }
    }
    
    static class Graph {
        private int numVertices;
        private List<Integer>[] adjacencyList;

        Graph(int numVertices) {
            this.numVertices = numVertices;
            adjacencyList = new ArrayList[numVertices];
            for (int i = 0; i < numVertices; i++) {
                adjacencyList[i] = new ArrayList<>();
            }
        }

        void addEdge(int from, int to) {
            adjacencyList[from].add(to);
        }
    }

    public static List<Integer> topologicalSort(Graph graph) {
        List<Integer> result = new ArrayList<>();
        int[] inDegree = new int[graph.numVertices];
        
        // Calculate in-degrees for each vertex
        for (int i = 0; i < graph.numVertices; i++) {
            for (int neighbor : graph.adjacencyList[i]) {
                inDegree[neighbor]++;
            }
        }
        
        // Initialize a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        
        // Add vertices with in-degree 0 to the queue
        for (int i = 0; i < graph.numVertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Perform BFS
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            
            for (int neighbor : graph.adjacencyList[vertex]) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        if (result.size() != graph.numVertices) {
            System.out.println("The graph has a cycle, topological sort is not possible.");
            return new ArrayList<>();
        }
        
        return result;
  }  
}
