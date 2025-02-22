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
        //26 jenis warna 
        String[] color = {
            "\u001B[38;5;196m", 
            "\u001B[38;5;202m", 
            "\u001B[38;5;208m", 
            "\u001B[38;5;214m", 
            "\u001B[38;5;220m", 
            "\u001B[38;5;226m", 
            "\u001B[38;5;190m", 
            "\u001B[38;5;46m",  
            "\u001B[38;5;47m",  
            "\u001B[38;5;51m",  
            "\u001B[38;5;27m",  
            "\u001B[38;5;21m",  
            "\u001B[38;5;57m",  
            "\u001B[38;5;93m",  
            "\u001B[38;5;129m", 
            "\u001B[38;5;201m", 
            "\u001B[38;5;165m", 
            "\u001B[38;5;198m", 
            "\u001B[38;5;160m", 
            "\u001B[38;5;124m", 
            "\u001B[38;5;166m", 
            "\u001B[38;5;202m", 
            "\u001B[38;5;214m", 
            "\u001B[38;5;118m", 
            "\u001B[38;5;50m",  
            "\u001B[38;5;21m"   
        };

        String reset = "\u001B[0m";
        for (int i = 0; i < this.row ; i++){
            for (int j = 0; j <this.col; j++){
                if (grid[i][j] == ('.')){
                    System.out.println(". ");
                }
                else{
                    int colorsIdx = (grid[i][j] - 'A') % color.length;
                    System.out.print(color[colorsIdx] + grid[i][j] + "  " + reset);  
                }
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
