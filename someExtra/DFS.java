// package someExtra;
import java.util.Scanner;

public class DFS {
    static int[][] arr = new int[10][10];
    static int[] visit = new int[10];
    static int[] stack = new int[10];
    static int[] distance = new int[10];
    static int[] parents = new int[10];
    static int top = 0;

    public static void main(String[] args) {
        int flag = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of nodes:");
        int node = sc.nextInt();
        System.out.println("Enter number of edges:");
        int edges = sc.nextInt();

        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                arr[i][j] = 0;
            }
        }
        System.out.println("Enter edges input");

        for (int i = 0; i < edges; i++) {
            int a = (int) sc.next().charAt(0) - 65;
            int b = (int) sc.next().charAt(0) - 65;
            arr[a][b] = arr[b][a] = 1;
        }
        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < node; i++) {
            visit[i] = 0;
        }

        System.out.println("Enter source of Traversing:");
        int source = (int) sc.next().charAt(0) - 65;
        System.out.println("Enter destination of Traversing:");
        int des = (int) sc.next().charAt(0) - 65;

        push(source);
        while (top != 0) {
            int par = peak();
            flag = 0;
            for (int child = 0; child < node; child++) {
                if (arr[par][child] == 1 && visit[child] == 0) {
                    push(child);
                    parents[child] = par;
                    visit[child] = 1;
                    if (child == des) {
                        flag = 1;
                        break;
                    }
                }
            }
            if (flag == 0) {
                pop();
            }
        }

        if (visit[des] == 0) {
            System.out.println("No path exists from source to destination.");
        } else {
            System.out.println("Path from " + (char) (source + 65) + " to " + (char) (des + 65) + ":");
            printPath(des);
        }
    }

    static void push(int n) {
        System.out.print((char) (n + 65) + " ");
        stack[top++] = n;
    }

    static void pop() {
        top--;
    }

    static int peak() {
        return stack[top - 1];
    }

    static void printPath(int node) {
        if (parents[node] != -1) {
            printPath(parents[node]);
            System.out.print(" -> ");
        }
        System.out.print((char) (node + 65));
    }
}
