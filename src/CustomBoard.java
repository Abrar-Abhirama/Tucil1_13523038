package src;
import java.util.*;

public class CustomBoard {
    private int row;
    private int col;
    private char[][] grid;
    private boolean[][] customMask;

    public CustomBoard(int row, int col, List<String> config) {
        this.row = row;
        this.col = col;
        this.grid = new char[row][col];
        this.customMask = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (config.get(i).charAt(j) == 'X') {
                    customMask[i][j] = true;
                    grid[i][j] = '.';
                } else {
                    customMask[i][j] = false;
                    grid[i][j] = ' ';
                }
            }
        }
    }

    public boolean isPlaceable(Block block, int shapeIndex, int x, int y) {
        char[][] shape = convertToCharMatrix(block.getAllShape().get(shapeIndex));
        int rowBlock = shape.length;
        int colBlock = shape[0].length;

        for (int i = 0; i < rowBlock; i++) {
            for (int j = 0; j < colBlock; j++) {
                if (shape[i][j] != '.' && (!isValidPosition(x + i, y + j) || grid[x + i][y + j] != '.')) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeBlock(Block block, int shapeIndex, int x, int y) {
        char[][] shape = convertToCharMatrix(block.getAllShape().get(shapeIndex));
        int rowBlock = shape.length;
        int colBlock = shape[0].length;

        for (int i = 0; i < rowBlock; i++) {
            for (int j = 0; j < colBlock; j++) {
                if (shape[i][j] != '.') {
                    grid[x + i][y + j] = shape[i][j];
                }
            }
        }
    }

    public void removeBlock(Block block, int shapeIndex, int x, int y) {
        char[][] shape = convertToCharMatrix(block.getAllShape().get(shapeIndex));
        int rowBlock = shape.length;
        int colBlock = shape[0].length;

        for (int i = 0; i < rowBlock; i++) {
            for (int j = 0; j < colBlock; j++) {
                if (shape[i][j] != '.') {
                    grid[x + i][y + j] = '.';
                }
            }
        }
    }

    public boolean isComplete() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (customMask[i][j] && grid[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col && customMask[x][y];
    }

    private char[][] convertToCharMatrix(String[][] shape) {
        int rows = shape.length;
        int cols = shape[0].length;
        char[][] result = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = shape[i][j].charAt(0);
            }
        }
        return result;
    }
}
