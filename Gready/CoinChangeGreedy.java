package Gready;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoinChangeGreedy {

    public static CoinChangeResult coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        reverse(coins);

        List<List<Integer>> allCombinations = new ArrayList<>();
        int count = coinChangeHelper(coins, amount, 0, new ArrayList<>(), allCombinations);

        if (count != -1) {
            return new CoinChangeResult(count, allCombinations);
        } else {
            return new CoinChangeResult(-1, new ArrayList<>());
        }
    }

    private static int coinChangeHelper(int[] coins, int amount, int index, List<Integer> currentCombination, List<List<Integer>> allCombinations) {
        if (amount == 0) {
            allCombinations.add(new ArrayList<>(currentCombination));
            return 1;
        }

        int count = 0;

        for (int i = index; i < coins.length; i++) {
            if (amount >= coins[i]) {
                currentCombination.add(coins[i]);
                count += coinChangeHelper(coins, amount - coins[i], i, currentCombination, allCombinations);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }

        return count;
    }

    private static void reverse(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static class CoinChangeResult {
        private final int count;
        private final List<List<Integer>> allCombinations;

        public CoinChangeResult(int count, List<List<Integer>> allCombinations) {
            this.count = count;
            this.allCombinations = allCombinations;
        }

        public int getCount() {
            return count;
        }

        public List<List<Integer>> getAllCombinations() {
            return allCombinations;
        }
    }

    public static void main(String[] args) {
        int[] coins = {1, 3, 5};
        int amount = 12;

        CoinChangeResult result = coinChange(coins, amount);

        if (result.getCount() != -1) {
            System.out.println("Number of combinations to make " + amount + " taka: " + result.getCount());
            System.out.println("\nAll sets of combinations:");
            for (List<Integer> combination : result.getAllCombinations()) {
                System.out.println(combination);
            }
        } else {
            System.out.println("It's not possible to make change for " + amount + " taka with the given coins.");
        }
    }
}
