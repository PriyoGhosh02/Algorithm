package assigenment;



public class gird {
    public static void main(String[] args) {
        int[][] grid = new int[6][6];

        // Inserting the provided matrix into the grid
        int[][] providedMatrix = {
                {1, 5, 1, 5, 1, 5},
                {3, 3, 2, 3, 3, 4},
                {2, 3, 4, 4, 3, 2},
                {2, 2, 3, 2, 2, 4},
                {0, 2, 4, 3, 4, 2},
                {4, 4, 4, 4, 2, 3}
        };

        for (int i = 0; i < 6; i++) {
            System.arraycopy(providedMatrix[i], 0, grid[i], 0, 6);
        }

        // Calculate the highest and lowest totals
        int highestTotal = calculateTotal(grid, true);
        int lowestTotal = calculateTotal(grid, false);

        // Print the results
        System.out.println("Highest Total: " + highestTotal);
        System.out.println("Lowest Total: " + lowestTotal);
    }

    private static int calculateTotal(int[][] grid, boolean isHighest) {
        int total = 0;
        int row = 0;
        int col = 0;

        while (row < 5) {
            total += grid[row][col];

            if (isHighest) {
                // Move to the maximum adjacent value
                int maxAdjacent = Math.max(grid[row + 1][col], grid[row + 1][col + 1]);
                col = (maxAdjacent == grid[row + 1][col + 1]) ? col + 1 : col;
            } else {
                // Move to the minimum adjacent value
                int minAdjacent = Math.min(grid[row + 1][col], grid[row + 1][col + 1]);
                col = (minAdjacent == grid[row + 1][col + 1]) ? col + 1 : col;
            }

            row++;
        }

        // Add the value in the last row
        total += grid[row][col];

        return total;
    }
}
