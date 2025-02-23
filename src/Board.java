package src;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Board {
    private int row;
    private int col;
    private char[][] grid;
    private char[][] customGrid;

    public Board(int row, int col) { 
        this.row = row;
        this.col = col;
        this.grid = new char[row][col];
        this.customGrid = new char[row][col]; 
    
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.grid[i][j] = '.'; 
                this.customGrid[i][j] = 'X'; 
            }
        }
    }

    public Board(int row, int col, char[][] customGrid) {  
        this.row = row;
        this.col = col;
        this.grid = new char[row][col];
        this.customGrid = customGrid; 
    
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (customGrid[i][j] == 'X') {
                    this.grid[i][j] = '.'; 
                } else {
                    this.grid[i][j] = ' '; 
                }
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

        for (int i = 0; i < rowBlock; i++) {
            for (int j = 0; j < colBlock; j++) {
                if (shape[i][j] != '.') {
                    if (grid[X + i][Y + j] != '.' || customGrid[X + i][Y + j] != 'X') {
                        return false;
                    }
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
                if (customGrid[i][j] == 'X' && grid[i][j] == '.') {
                    return false;
                }
            }
        }

        return true;
    }

    public void printBoard(){
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
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                char cell = grid[i][j];
                if (cell == '.') {
                    System.out.print(".  ");
                } 
                else if (cell < 'A' || cell > 'Z') {
                    System.out.print(cell + "  ");
                } 
                else {
                    int colorsIdx = (cell - 'A') % color.length;
                    System.out.print(color[colorsIdx] + cell + "  " + reset);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void saveImage(String file) {
        int cellSize = 50; 
        int width = col * cellSize;
        int height = row * cellSize;
    
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
     
        Color[] colors = {
            new Color(255, 0, 0), new Color(255, 95, 0), new Color(255, 135, 0),
            new Color(255, 175, 0), new Color(255, 215, 0), new Color(255, 255, 0),
            new Color(190, 255, 0), new Color(0, 255, 0), new Color(0, 255, 95),
            new Color(0, 255, 255), new Color(0, 175, 255), new Color(0, 0, 255),
            new Color(95, 0, 255), new Color(175, 0, 255), new Color(215, 0, 255),
            new Color(255, 0, 215), new Color(255, 0, 135), new Color(255, 0, 175),
            new Color(255, 0, 95), new Color(215, 0, 0), new Color(175, 95, 0),
            new Color(255, 95, 0), new Color(255, 175, 0), new Color(0, 255, 135),
            new Color(0, 175, 95), new Color(0, 0, 175)
        };
    
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char block = grid[i][j];
                int x = j * cellSize;
                int y = i * cellSize;
    
                g.setColor(Color.WHITE);
                g.drawRect(x, y, cellSize, cellSize);
    
                if (block == '.' || block == ' ') {
                   continue; 
                } else if (block >= 'A' && block <= 'Z') {
                    int colorIndex = (block - 'A') % colors.length;
                    g.setColor(colors[colorIndex]);
                }
    
                g.fillOval(x + 1, y + 1, cellSize - 4, cellSize - 4);
            }
        }
    
        g.dispose();
    
        try {
            ImageIO.write(image, "png", new File(file));
            System.out.println("Solusi telah disimpan di: " + file);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan gambar: " + e.getMessage());
        }
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
