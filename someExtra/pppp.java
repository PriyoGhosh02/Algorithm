import java.util.*;

class MyGraph {
    private int numberOfVertices;
    private LinkedList<Integer>[] adjacencyList;

       public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);
        adjacencyList[destination].add(source);
    }
    
    public MyGraph(int v) {
        numberOfVertices = v;
        adjacencyList = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adjacencyList[i] = new LinkedList<>();
    }

    public boolean cycle() {
        boolean[] visited = new boolean[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            if (!visited[i] && hasCycleUtil(i, visited, -1)) {
                return true;
            }   }
        return false;
    }

    private boolean hasCycleUtil(int current, boolean[] visited, int parent) {
        visited[current] = true;

        for (Integer neighbor : adjacencyList[current]) {
            if (!visited[neighbor]) {
                if (hasCycleUtil(neighbor, visited, current)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }  }

        return false;
    }  }

public class pppp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        int nodes = scanner.nextInt();

        MyGraph graph = new MyGraph(nodes);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        for (int i = 0; i < edges; i++) {
            System.out.print("Enter an edge (1  2): ");
            int vertex1 = scanner.nextInt();
            int vertex2 = scanner.nextInt();
            graph.addEdge(vertex1, vertex2);
        }

        boolean hasCycle = graph.cycle();

        if (hasCycle) {
            System.out.println("\nThe graph  has cycle.");
        } else {
            System.out.println("\nThe graph is acycle.");
        } } }
