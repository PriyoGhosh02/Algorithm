package someExtra;
import java.util.*;

class BFSCycleDetection {
    public static void main(String[] args) {
        int numVertices = 5;
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        addEdge(adjacencyList, 0, 1);
        addEdge(adjacencyList, 1, 2);
        addEdge(adjacencyList, 2, 3);
        addEdge(adjacencyList, 3, 4);
        addEdge(adjacencyList, 4, 0); // Added this line to create a cycle

        CycleDetector cycleDetector = new CycleDetector();
        boolean hasCycle = cycleDetector.detectCycle(numVertices, adjacencyList);

        if (hasCycle) {
            System.out.println("Yes, the graph contains a cycle.");
        } else {
            System.out.println("No, the graph is acyclic.");
        }
    }

    static void addEdge(List<List<Integer>> adjacencyList, int from, int to) {
        adjacencyList.get(from).add(to);
    }
}

class CycleDetector {
    boolean isCyclic(List<List<Integer>> adjacencyList, int startVertex, boolean[] visited, boolean[] inRecursionStack) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        inRecursionStack[startVertex] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (Integer neighbor : adjacencyList.get(currentNode)) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    inRecursionStack[neighbor] = true;
                } else if (inRecursionStack[neighbor]) {
                    return true;
                }
            }
            inRecursionStack[currentNode] = false;
        }

        return false;
    }

    boolean detectCycle(int numVertices, List<List<Integer>> adjacencyList) {
        boolean[] visited = new boolean[numVertices];
        boolean[] inRecursionStack = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && isCyclic(adjacencyList, i, visited, inRecursionStack)) {
                return true;
            }
        }

        return false;
    }
}
