package DFS;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class findEdgeCycleFromGraph {
    static int[][] arr = new int[10][10];
    static int[] visit = new int[10];
    static int[] stack = new int[10];
    static int top = 0;
    static int nodes;
    static List<Integer> treeEdges = new ArrayList<>();
    static List<Integer> backEdges = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes:");
        int nodes = sc.nextInt();
        System.out.println("Enter the number of Edges:");
        int edges = sc.nextInt();

        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                arr[i][j] = 0;
            }
        }

        System.out.println("Enter edges as input:");
        for (int i = 0; i < edges; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[a][b] = 1;
        }

        for (int i = 0; i < nodes; i++) {
            visit[i] = 0;
        }

        System.out.println("Enter source:");
        int source = sc.nextInt();
        System.out.println("Enter destination for traversing:");
        int destination = sc.nextInt();

        System.out.println("DFS Traversal Path:");

        boolean hasCycle = dfs(source, destination);

        if (hasCycle) {
            System.out.println("The graph contains a cycle.");
            System.out.println("Back Edges: " + backEdges);
        } else {
            System.out.println("The graph is acyclic.");
            System.out.println("Tree Edges: " + treeEdges);
        }
    }

    static void push(int n) {
        stack[top++] = n;
    }

    static void pop() {
        top--;
    }

    static int peak() {
        return stack[top - 1];
    }

    static boolean dfs(int start, int dest) {
        push(start);
        visit[start] = 1;

        if (start == dest) {
            printTraversalPath();
            return false;
        }

        boolean hasCycle = false;

        for (int child = 0; child < nodes; child++) {
            if (arr[start][child] == 1 && visit[child] == 0) {
                if (dfs(child, dest)) {
                    hasCycle = true;
                    backEdges.add(start); // Store the back edge
                }
            } else if (arr[start][child] == 1 && visit[child] == 1) {
                hasCycle = true;
                backEdges.add(start); // Store the back edge
            }
        }

        pop();

        if (!hasCycle) {
            treeEdges.add(start); // Store the tree edge
        }

        return hasCycle;
    }

    static void printTraversalPath() {
        for (int i = 0; i < top; i++) {
            System.out.print((char)(stack[i] + 65) + " ");
        }
        System.out.println();
    }
}
