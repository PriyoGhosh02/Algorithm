
import java.util.Scanner;

public class timecom {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int a = 0, b = 0;
        int N = 5, M = 5;
        for (int i = 1; i <= N; i++) {
            a += i;
        }
        for (int j = 1; j <= M; j++) {
            b += j;
        }
        System.out.print(a + "\t" + b);
    }
}
