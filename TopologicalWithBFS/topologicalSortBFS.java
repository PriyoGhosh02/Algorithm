package TopologicalWithBFS;

import java.util.*;

public class topologicalSortBFS {

    static class Graph {
        private int numVertices;
        private int[][] adjacencyMatrix;

        Graph(int numVertices) {
            this.numVertices = numVertices;
            adjacencyMatrix = new int[numVertices][numVertices];
        }

        void addEdge(int from, int to) {
            adjacencyMatrix[from][to] = 1;
        }
    }

    public static List<Integer> topologicalSort(Graph graph) {
        List<Integer> result = new ArrayList<>();
        int[] inDegree = new int[graph.numVertices];

        for (int i = 0; i < graph.numVertices; i++) {
            for (int j = 0; j < graph.numVertices; j++) {
                inDegree[j] += graph.adjacencyMatrix[i][j];
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < graph.numVertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);

            for (int i = 0; i < graph.numVertices; i++) {
                if (graph.adjacencyMatrix[vertex][i] == 1) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }

        if (result.size() != graph.numVertices) {
            System.out.println("The graph has a cycle, topological sort is not possible.");
            return new ArrayList<>();
        }

        return result;
    }

    public static boolean hasCycle(Graph graph) {
        boolean[] visited = new boolean[graph.numVertices];
        boolean[] stack = new boolean[graph.numVertices];

        for (int i = 0; i < graph.numVertices; i++) {
            if (isCyclicUtil(graph, i, visited, stack)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isCyclicUtil(Graph graph, int vertex, boolean[] visited, boolean[] stack) {
        if (!visited[vertex]) {
            visited[vertex] = true;
            stack[vertex] = true;

            for (int i = 0; i < graph.numVertices; i++) {
                if (graph.adjacencyMatrix[vertex][i] == 1) {
                    if (!visited[i] && isCyclicUtil(graph, i, visited, stack)) {
                        return true;
                    } else if (stack[i]) {
                        return true;
                    }
                }
            }
        }

        stack[vertex] = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = input.nextInt();
        Graph graph = new Graph(numVertices);

        System.out.print("Enter the number of Edges: ");
        int numEdges = input.nextInt();

        System.out.println("Enter the edges (From To):");
        for (int i = 0; i < numEdges; i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            graph.addEdge(from, to);
        }

        if (hasCycle(graph)) {
            System.out.println("The graph has a cycle.");
        } else {
            List<Integer> result = topologicalSort(graph);

            System.out.println("Topological Sort Order:");
            for (int vertex : result) {
                System.out.print(vertex + " ");
            }
        }

        input.close();
    }
}
