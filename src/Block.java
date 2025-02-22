package src;
import java.util.*;
import src.*;
public class Block {
    private char id;
    private char[][] shape;
    private List<String[][]> allShape;

    public Block(char id, String[][] shape, List<String[][]> allShape) {
        this.id = id;
        // ubah String[][] jadi char[][]
        this.shape = new char[shape.length][];
        for (int i = 0; i < shape.length; i++) {
            this.shape[i] = new char[shape[i].length];
            for (int j = 0; j < shape[i].length; j++) {
                this.shape[i][j] = shape[i][j].charAt(0); 
            }
        }
        this.allShape = allShape;
    }

    public char getId() {
        return id;
    }
    
    public char[][] getShape(){
        return shape;
    }

    public List<String[][]> getAllShape(){
        return allShape;
    }

    public void printShape() {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                System.out.print(shape[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String[][] rotateBlock90(String[][] shape){
        int row = shape.length;
        int cols = shape[0].length;
        String[][] result = new String[cols][row];

        for (int i = 0; i < row ;i++){
            for (int j = 0; j < cols; j++){
                result[j][row-1-i] = shape[i][j];
            }
        }
        return result;
    }

    public static String[][] rotateBlock180(String[][] shape){
        return(rotateBlock90(rotateBlock90(shape)));
    }

    public static String[][] rotateBlock270(String[][] shape){
        return(rotateBlock90(rotateBlock90(rotateBlock90(shape))));
    }

    public static String[][] mirrorVertical(String[][] shape) {
        int rows = shape.length;
        int cols = shape[0].length;
        String[][] result = new String[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][cols-1-j] = shape[i][j];
            }
        }
        return result;
    }

    public static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static String[][] convertCharToString(char[][] charMatrix) {
        String[][] stringMatrix = new String[charMatrix.length][charMatrix[0].length];
        for (int i = 0; i < charMatrix.length; i++) {
            for (int j = 0; j < charMatrix[i].length; j++) {
                stringMatrix[i][j] = String.valueOf(charMatrix[i][j]);
            }
        }
        return stringMatrix;
    }
}