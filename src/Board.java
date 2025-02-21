package src;
import src.*;

public class Board {
    private int row;
    private int col;
    private char[][] grid;

    public Board(int row, int col){
        this.row = row;
        this.col = col;
        this.grid = new char[row][col];

        for (int i = 0; i < row ; i++){
            for (int j = 0; j < row; j++){
                grid[i][j] = '.';
            }
        }
    }
    
    public char[][] getGrid(){
        return grid;
    }

    public boolean isPlaceAble(Block block, int shapeIndex, int X, int Y){
        char[][] shape = convertToCharMatrix(block.getAllShape().get(shapeIndex));
        int rowBlock = shape.length;
        int colBlock = shape[0]. length;

        if (X + rowBlock > row || Y + colBlock > col){
            return false;
        }

        for (int i = 0; i < rowBlock; i++){
            for (int j =0; j < colBlock; j++){
                if (shape[i][j] != '.' && grid[X + i][Y+j] != '.'){
                    return false;
                }
            }
        }

        return true;
    }

    public void placeBlock(Block block, int shapeIndex, int X, int Y){
        char[][] shape = convertToCharMatrix(block.getAllShape().get(shapeIndex));
        int rowBlock = shape.length;
        int colBlock = shape[0].length;
        
        for (int i = 0; i < rowBlock; i++){
            for (int j = 0; j < colBlock ; j++){
                if (shape[i][j] != '.'){
                    grid[X + i][Y + j] = shape[i][j];
                }
            }
        }
    }


    public void removeBlock(Block block, int shapeIndex, int startX, int startY) {
        char[][] shape = convertToCharMatrix(block.getAllShape().get(shapeIndex));
        int blockRows = shape.length;
        int blockCols = shape[0].length;
    
        for (int i = 0; i < blockRows; i++) {
            for (int j = 0; j < blockCols; j++) {
                if (shape[i][j] != '.') {
                    grid[startX + i][startY + j] = '.'; 
                }
            }
        }
    }

    public boolean isComplete(){
        int rowBoards = grid.length;
        int colBoards = grid[0].length;

        for (int i =0 ;i < rowBoards ; i++){
            for (int j = 0; j < colBoards; j++){
                if (grid[i][j] == '.'){
                    return false;
                }
            }
        }

        return true;
    }

    public void printBoard(){
        for (int i = 0; i < this.row ; i++){
            for (int j = 0; j <this.col; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
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
