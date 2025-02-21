package src;
import java.io.*;
import java.util.*;
import src.Block;

public class Main {
    public static void main(String[] args){
        try{
            // Baca File
            String file = "STIMA\\src\\testcase\\tes.txt";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            // ini kemungkinan di hapus
            List<String[][]> matrix = new ArrayList<>();
            String words;
            String mode;
            int currMax = -1;

            // Simpan Semua Blocknya
            List<Block> blocklist = new ArrayList<>();
            
            // Baca Row, Cols, Jumlah Blok
            if ((words = reader.readLine()) != null){
                String[] word = words.split(" ");
                if (word.length >= 3){
                    int row = Integer.parseInt(word[0]);
                    int col = Integer.parseInt(word[1]);
                    int blocks = Integer.parseInt(word[2]);
                    System.out.println("row = " + row);
                    System.out.println("col = " + col);
                    System.out.println("blocks = " + blocks);
                }
            }

            if ((words = reader.readLine()) != null){
                mode = words;
                System.out.println("Mode = " + mode);
            }

            List<String[]> currentCharMatrix= new ArrayList<>();
            String prevchar = "";

            while((words = reader.readLine()) != null){
                if (words.trim().isEmpty()) {
                    continue; // Skip baris kosong
                }
                String dots = words.replace(" ", ".");
                // Ubah 1 line jadi array
                String[] row = dots.split("");
                if (row.length > currMax){
                    currMax = row.length;
                }
                
                int i = 0;
                //cari sampai bukan "." , harus char A-Z
                while (row[i].equals(".") && i < row.length){
                    i++;
                    
                }
                
                String currentChar = row[i];
                
                // System.out.println(currentChar);
                i = 0;

                if (!currentChar.equals(prevchar) && !currentCharMatrix.isEmpty()){
                    // biar N x M, yang spasi di isi "."
                    for (int k = 0; k< currentCharMatrix.size(); k++){
                        String[] rowMatrix = currentCharMatrix.get(k);
                        if (rowMatrix.length < currMax){
                            String[] newRowMatrix = new String[currMax];
                            for (int z = 0; z < rowMatrix.length; z++){
                                newRowMatrix[z] = rowMatrix[z];
                            }

                            for(int x = rowMatrix.length; x < currMax ;x++){
                                newRowMatrix[x] = ".";
                            }

                            currentCharMatrix.set(k, newRowMatrix);
                        }
                    }
                    matrix.add(currentCharMatrix.toArray(new String[0][]));
                    
                    // Ubah list ke matrix (biar gampang)
                    
                    String[][] matrixblock = convertToMatrix(currentCharMatrix);
                    Block block = new Block(prevchar.charAt(0), matrixblock);
                    blocklist.add(block);

                    System.out.println("Block ID: " + prevchar);
                    block.printShape();
                    
                    currentCharMatrix.clear();
                    currMax = -1;
                }

                currentCharMatrix.add(row);
                prevchar = currentChar;
            }

            if (!currentCharMatrix.isEmpty()){
                matrix.add(currentCharMatrix.toArray(new String[0][]));
                // String[][] matrixblock = convertToMatrix(currentCharMatrix);
                // for (int b = 0; b < matrixblock.length; b++) {
                //     for (int j = 0; j < matrixblock[b].length; j++) {
                //         System.out.print(matrixblock[b][j] + " ");
                //     }
                //     System.out.println();
                // }
            }


            reader.close();

            // System.out.println("Matrix : ");
            // for (String [][] matrixs : matrix){
            //     System.out.println("[");
            //     for (String[] row : matrixs){
            //         System.out.print("[");
            //         for (int i = 0; i < row.length; i++){
            //             System.out.print("\"" + row[i] + "\"");
            //             if (i < row.length - 1) System.out.print(", ");
            //         }
            //         System.out.println("]");
            //     }
            //     System.out.println("]");
            // }

        }


        catch(IOException e){ 
            e.printStackTrace();
        }
    }

    public static String[][] convertToMatrix(List<String[]> list) {
        // Ubah list jadi matrix
        int rows = list.size();
        int maxColumns = 0;
        for (String[] array : list) {
            if (array.length > maxColumns) {
                maxColumns = array.length;
            }
        }
        String[][] matrix = new String[rows][maxColumns];

        for (int i = 0; i < rows; i++) {
            String[] rowArray = list.get(i);
            for (int j = 0; j < rowArray.length; j++) {
                matrix[i][j] = rowArray[j];
            }
        }

        return matrix;
    }
}
