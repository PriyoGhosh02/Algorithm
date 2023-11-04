package MST.Prims;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class primsAdjMatrix {

    public static class KruskalMST {
        static class edge {
            int u, v, wt;

            edge(int u, int v, int wt) {
                this.u = u;
                this.v = v;
                this.wt = wt;
            }
        }

        static int[] parent;

        static void make(int vertex) {
            parent[vertex] = vertex;
        }

        static int find(int vertex) {
            if (parent[vertex] == vertex)
                return vertex;
            return parent[vertex] = find(parent[vertex]);
        }

        static void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a != b) {
                parent[b] = a;
            }
        }

        static ArrayList<KruskalMST.edge> list = new ArrayList<>();

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int total_cost = 0;
            int vertex = sc.nextInt();
            int edge = sc.nextInt();
            parent = new int[vertex];
            for (int i = 0; i < vertex; i++) { 
                make(i);
            }
            for (int i = 0; i < edge; i++) {
                int U, V, Wt;
                U = sc.nextInt();
                V = sc.nextInt();
                Wt = sc.nextInt();
                list.add(new edge(U, V, Wt));
            }
            list.sort(Comparator.comparing(o -> o.wt));
            for (KruskalMST.edge Edge : list) {
                int wt = Edge.wt;
                int a = Edge.u;
                int b = Edge.v;
                if (find(a) != find(b)) {
                    union(a, b);
                    System.out.println(wt + " : " + a + " -> " + b);
                    total_cost += wt;
                }
            }
            System.out.println("Minimum Cost Spanning Tree : " + total_cost);
        }
    }

    public static void main(String[] args) {
        KruskalMST.main(args);
    }
}


/***
 * implement prims algorithm using adacency matrix
 * 1. make a mstset
 * 2. make a dist[] array to put weight
 * 3. initilize all distance as inf and distance a so
 * 4. take mindistance node and updte all adjceny node
 * 
 * repeat this untill all node add in mstset
 */