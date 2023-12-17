import java.util.*;

public class findSortest {
    static int[][] mat = new int[10][10];
    static int[] visit = new int[10];
    static int[] queue = new int[10];
    static int[] level = new int[10];
    static int[] parent = new int[10]; 
    static int front = -1, rear = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of nodes:");
        int node = sc.nextInt();

        for (int i = 0; i < node; i++)
            for (int j = 0; j < node; j++) {
                mat[i][j] = 0;
            }

        System.out.println("Enter number of edges:");
        int edge = sc.nextInt();
        for (int i = 0; i < edge; i++) {
            int a = (int) sc.next().charAt(0) - 65;
            int b = (int) sc.next().charAt(0) - 65;
            mat[a][b] = mat[b][a] = 1;
        }

        for (int i = 0; i < node; i++)
            visit[i] = 0;

        System.out.println("Enter your source node:");
        int src = (int) sc.next().charAt(0) - 65;

        System.out.println("Enter your destination node:");
        int dest = (int) sc.next().charAt(0) - 65;

        enqueue(src);
        level[src] = 0;
        parent[src] = -1; 
        while (!isEmpty()) {
            int nd = dequeue();
            if (nd == dest) {
                printShortestPath(src, dest);
                System.out.println("\nLevel from source to destination: " + level[dest]);
                break;
            }
            for (int i = 0; i < node; i++) {
                if (mat[nd][i] == 1 && visit[i] == 0) {
                    enqueue(i);
                    visit[i] = 1;
                    level[i] = level[nd] + 1;
                    parent[i] = nd; 
                }
            }
        }
    }

    static void enqueue(int n) {
        queue[++rear] = n;
        visit[n] = 1;
    }

    static int dequeue() {
        return queue[++front];
    }

    static boolean isEmpty() {
        return front == rear;
    }

    static void printShortestPath(int src, int dest) {
//        System.out.print("Shortest Path: ");
        if (src == dest) {
            System.out.print((char) (src + 65));
        } else if (parent[dest] == -1) {
            System.out.println("There is no path from source to destination.");
        } else {
            printShortestPath(src, parent[dest]);
            System.out.print(" -> " + (char) (dest + 65));
        }
    }
}
