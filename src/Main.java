package src;
import java.io.*;
import java.util.*;
import src.*;

public class Main {
    public static void main(String[] args){
        try{
            // Baca File
            String file = "STIMA\\src\\testcase\\tes.txt";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String[][]> matrix = new ArrayList<>();
            String words;
            String mode;
            int currMax = -1;
            int rowBoard = 0, colBoard = 0, blocks = 0;
           

            // Simpan Semua Blocknya
            List<Block> blocklist = new ArrayList<>();
            List<String[][]> allShape = new ArrayList<>();

            // Baca Row, Cols, Jumlah Blok
            if ((words = reader.readLine()) != null){
                String[] word = words.split(" ");
                if (word.length >= 3){
                    rowBoard = Integer.parseInt(word[0]);
                    colBoard = Integer.parseInt(word[1]);
                    blocks = Integer.parseInt(word[2]);
                    // System.out.println("row = " + rowBoard);
                    // System.out.println("col = " + colBoard);
                    // System.out.println("blocks = " + blocks);
                }
            }
            
            Board board = new Board(rowBoard, colBoard);
            // board.printBoard();

            if ((words = reader.readLine()) != null){
                mode = words;
                // System.out.println("Mode = " + mode);
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
                    String[][] block90 = Block.rotateBlock90(matrixblock);
                    String[][] block180 = Block.rotateBlock180(matrixblock);
                    String[][] block270 = Block.rotateBlock270(matrixblock);
                    String[][] blockMirror = Block.mirrorVertical(matrixblock);
                    String[][] blockMirror90 = Block.mirrorVertical(block90);
                    String[][] blockMirror180 = Block.mirrorVertical(block180);
                    String[][] blockMirror270 = Block.mirrorVertical(block270);
                    allShape.add(matrixblock);
                    allShape.add(block90);
                    allShape.add(block180);
                    allShape.add(block270);
                    allShape.add(blockMirror);
                    allShape.add(blockMirror90);
                    allShape.add(blockMirror180);
                    allShape.add(blockMirror270);

                    List<String[][]> newAllShape = new ArrayList<>(allShape);
                    Block block = new Block(prevchar.charAt(0), matrixblock, new ArrayList<>(newAllShape));
                    blocklist.add(block);
                    
                    currentCharMatrix.clear();
                    allShape.clear();
                    currMax = -1;
                }

                currentCharMatrix.add(row);
                prevchar = currentChar;
            }

            if (!currentCharMatrix.isEmpty()){
                matrix.add(currentCharMatrix.toArray(new String[0][]));
                String[][] matrixblock = convertToMatrix(currentCharMatrix);
                String[][] block90 = Block.rotateBlock90(matrixblock);
                String[][] block180 = Block.rotateBlock180(matrixblock);
                String[][] block270 = Block.rotateBlock270(matrixblock);
                String[][] blockMirror = Block.mirrorVertical(matrixblock);
                String[][] blockMirror90 = Block.mirrorVertical(block90);
                String[][] blockMirror180 = Block.mirrorVertical(block180);
                String[][] blockMirror270 = Block.mirrorVertical(block270);
                allShape.add(matrixblock);
                allShape.add(block90);
                allShape.add(block180);
                allShape.add(block270);
                allShape.add(blockMirror);
                allShape.add(blockMirror90);
                allShape.add(blockMirror180);
                allShape.add(blockMirror270);
                
                List<String[][]> newAllShape = new ArrayList<>(allShape);
                Block block = new Block(prevchar.charAt(0), matrixblock, new ArrayList<>(newAllShape));
                blocklist.add(block);
            }


            reader.close();

            // Debug
            // System.out.println("\nDaftar Blok:");
            // for (Block b : blocklist) {
            //     System.out.println("Block ID: " + b.getId());
            //     Block.printMatrix(Block.convertCharToString(b.getShape()));

            // }
            
            int totalCells = rowBoard * colBoard;
            int totalBlockCells = 0;

            for (Block block : blocklist) {
                char[][] shape = block.getShape();
                for (char[] row : shape) {
                    for (char cell : row) {
                        if (cell != '.') {
                            totalBlockCells++;
                        }
                    }
                }
            }

            if (totalBlockCells < totalCells) {
                System.out.println("Blok yang diberikan kurang untuk mengisi papan.\nTidak ada solusi.");
                return;
            } else if (totalBlockCells > totalCells) {
                System.out.println("Blok yang diberikan lebih banyak dari yang dibutuhkan.\nTidak ada solusi.");
                return;
            }

            long startTime = System.nanoTime();
            BruteForce bruteforce = new BruteForce(board, blocklist);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            System.out.println("Waktu pencarian: " + duration + " ms");
            if (!bruteforce.solve()) {
                System.out.println("Solusi Tidak Ada");
            }
            else{
                System.out.println("Apakah anda ingin menyimpan solusi? (ya/tidak)");
                Scanner scanner = new Scanner(System.in);
                String save = scanner.nextLine().trim().toLowerCase();
                if (save.equals("ya")){
                    System.out.print("Ketikkan Nama file: ");
                    String File = scanner.nextLine().trim();

                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(File))){
                        char[][] grid = board.getGrid();
                        for (int j= 0; j < grid.length; j++){
                            for (int k =0; k < grid[j].length;k++){
                                writer.write(grid[j][k] + " ");
                            }
                            writer.newLine();
                        }        
                    }
                    catch (IOException error){
                        System.out.println("Ada Error dalam menyimpan file");
                    }
                }

                else if (save.equals("tidak")){
                    System.out.println("Tidak di Simpan");
                }

                else{
                    System.out.println("Input Tidak Valid");
                }
                scanner.close();

            }

        } 
        catch(IOException e){ 
            e.printStackTrace();
        }
    }

    public static String[][] convertToMatrix(List<String[]> list) {
        int rows = list.size();
        int maxColumns = list.stream().mapToInt(arr -> arr.length).max().orElse(0);

        String[][] matrix = new String[rows][maxColumns];

        for (int i = 0; i < rows; i++) {
            String[] rowArray = list.get(i);
            for (int j = 0; j < maxColumns; j++) {
                if (j < rowArray.length && rowArray[j] != null) {
                    matrix[i][j] = rowArray[j]; 
                } else {
                    matrix[i][j] = "."; 
                }
            }
        }


        return matrix;
    }

    
}
