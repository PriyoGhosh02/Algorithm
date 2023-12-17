package time_complexity;


public class fatorialRecurction {

    public static int factRec(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factRec(n - 1);
        }
    }

    public static int factLoop(int n) {
        int fact = 1;
        for (int i = n; i >= 1; i--) {
            fact = fact * i;
        }
        return fact;
    }

    public static void main(String[] args) {
        int n = 6;

        System.out.println(factRec(n));
        System.out.println(factLoop(n));

    }
}
