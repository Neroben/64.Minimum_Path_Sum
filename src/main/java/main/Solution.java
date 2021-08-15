package main;

public class Solution {

    private Solution() {
    }

    public static int minPathSum(int[][] grid) throws CloneNotSupportedException {
        if(!validationGrid(grid)) {
            throw new RuntimeException("Grid is not valid");
        }

        int[][] minPath = new int[grid.length][grid[0].length];
        minPath[0][0] = grid[0][0];

        VectorSteps vectorStep = new VectorSteps(grid);

        findRight(vectorStep, minPath);
        findLeft(vectorStep, minPath);
        findUp(vectorStep, minPath);
        findDown(vectorStep, minPath);

        return minPath[grid.length - 1][grid.length - 1];
    }

    private static boolean validationGrid(int[][] grid) {
        if (grid.length == 0) {
            return false;
        }
        int size = grid[0].length;
        for (int[] row : grid) {
            if (row.length != size) {
                return false;
            }
        }
        return true;
    }

    private static void findRight(VectorSteps step, int[][] minPath) throws CloneNotSupportedException {
        step = step.clone();
        if (step.setRight() && step.isNotWasHere()) {
            step.setWasHere();
            if (minPath[step.getIndexX()][step.getIndexY()] == 0 ||
                    minPath[step.getIndexX()][step.getIndexY()] > step.getValue()) {
                minPath[step.getIndexX()][step.getIndexY()] = step.getValue();
                step.resetWasHere();
            }
            findRight(step, minPath);
            findDown(step, minPath);
            findUp(step, minPath);
        }
    }

    private static void findLeft(VectorSteps step, int[][] minPath) throws CloneNotSupportedException {
        step = step.clone();
        if (step.setLeft() && step.isNotWasHere()) {
            step.setWasHere();
            if (minPath[step.getIndexX()][step.getIndexY()] == 0 ||
                    minPath[step.getIndexX()][step.getIndexY()] > step.getValue()) {
                minPath[step.getIndexX()][step.getIndexY()] = step.getValue();
                step.resetWasHere();
            }
            findLeft(step, minPath);
            findDown(step, minPath);
            findUp(step, minPath);
        }
    }

    private static void findUp(VectorSteps step, int[][] minPath) throws CloneNotSupportedException {
        step = step.clone();
        if (step.setUp() && step.isNotWasHere()) {
            step.setWasHere();
            if (minPath[step.getIndexX()][step.getIndexY()] == 0 ||
                    minPath[step.getIndexX()][step.getIndexY()] > step.getValue()) {
                minPath[step.getIndexX()][step.getIndexY()] = step.getValue();
                step.resetWasHere();
            }
            findUp(step, minPath);
            findLeft(step, minPath);
            findRight(step, minPath);
        }
    }

    private static void findDown(VectorSteps step, int[][] minPath) throws CloneNotSupportedException {
        step = step.clone();
        if (step.setDown() && step.isNotWasHere()) {
            step.setWasHere();
            if (minPath[step.getIndexX()][step.getIndexY()] == 0 ||
                    minPath[step.getIndexX()][step.getIndexY()] > step.getValue()) {
                minPath[step.getIndexX()][step.getIndexY()] = step.getValue();
                step.resetWasHere();
            }
            findDown(step, minPath);
            findRight(step, minPath);
            findLeft(step, minPath);
        }
    }

    private static class VectorSteps implements Cloneable {
        private int indexX = 0;
        private int indexY = 0;
        private int value;
        private final int[][] grid;
        private boolean[][] wasHere;
        private final int rows;
        private final int columns;

        // размерность сетки
        VectorSteps(int[][] grid) {
            this.grid = grid;
            wasHere = new boolean[grid.length][grid[0].length];
            rows = grid.length;
            columns = grid[0].length;
            value = grid[0][0];
        }

        public int getIndexX() {
            return indexX;
        }

        public int getIndexY() {
            return indexY;
        }

        public int getValue() {
            return value;
        }

        boolean isNotWasHere() {
            return !wasHere[indexX][indexY];
        }

        void setWasHere() {
            wasHere[indexX][indexY] = true;
        }

        void resetWasHere() {
            wasHere = new boolean[grid.length][grid[0].length];
        }

        boolean setRight() {
            if (indexY < columns - 1) {
                indexY += 1;
                value += grid[indexX][indexY];
                return true;
            }
            return false;
        }

        boolean setLeft() {
            if (indexY > 0) {
                indexY -= 1;
                value += grid[indexX][indexY];
                return true;
            }
            return false;
        }

        boolean setDown() {
            if (indexX < rows - 1) {
                indexX += 1;
                value += grid[indexX][indexY];
                return true;
            }
            return false;
        }

        boolean setUp() {
            if (indexX > 0) {
                indexX -= 1;
                value += grid[indexX][indexY];
                return true;
            }
            return false;
        }

        @Override
        public VectorSteps clone() throws CloneNotSupportedException {
            //I understand why sonar swears, but in this case everything works correctly
            return  (VectorSteps) super.clone();
        }
    }

}