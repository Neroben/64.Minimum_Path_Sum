class SolutionTest {

    @org.junit.jupiter.api.Test
    void minPathSum() throws CloneNotSupportedException {

        int[][] grid = {
                {1, 3, 1},
                {1, 5, 8},
                {40, 2, 1}
        };

        System.out.println(Solution.minPathSum(grid));
    }
}