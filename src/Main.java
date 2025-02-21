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
                    System.out.println("row = " + rowBoard);
                    System.out.println("col = " + colBoard);
                    System.out.println("blocks = " + blocks);
                }
            }
            
            Board board = new Board(rowBoard, colBoard);
            // board.printBoard();

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

                // Debugging: Menampilkan baris mentah yang dibaca
                System.out.println("Raw input line: " + words);

                // Hilangkan spasi ekstra
                words = words.trim().replaceAll("\\s+", " ");
                String dots = words.replace(" ", ".");
                String[] row = dots.split("");

                // Debugging: Menampilkan hasil parsing row
                System.out.println("Parsed row: " + Arrays.toString(row));

                if (row.length > currMax){
                    currMax = row.length;
                }

                int i = 0;
                // cari sampai bukan ".", harus char A-Z
                while (i < row.length && row[i].equals(".")){
                    i++;
                }
                
                if (i >= row.length) {
                    continue; // Lewati baris kosong yang hanya berisi titik
                }

                String currentChar = row[i];

                if (!currentChar.equals(prevchar) && !currentCharMatrix.isEmpty()){
                    // Debugging: Menampilkan isi blok sebelum perataan
                    System.out.println("Blok sebelum perataan:");
                    for (String[] line : currentCharMatrix) {
                        System.out.println(Arrays.toString(line));
                    }

                    // Pastikan setiap baris dalam blok memiliki panjang `currMax`
                    for (int k = 0; k < currentCharMatrix.size(); k++){
                        String[] rowMatrix = currentCharMatrix.get(k);
                        if (rowMatrix.length < currMax){
                            String[] newRowMatrix = new String[currMax];
                            for (int z = 0; z < currMax; z++) {
                                if (z < rowMatrix.length) {
                                    newRowMatrix[z] = (rowMatrix[z] != null) ? rowMatrix[z] : ".";  
                                } else {
                                    newRowMatrix[z] = ".";  
                                }
                            }
                            currentCharMatrix.set(k, newRowMatrix);
                        }
                    }

                    matrix.add(currentCharMatrix.toArray(new String[0][]));
                    
                    // Konversi list ke matriks
                    String[][] matrixblock = convertToMatrix(currentCharMatrix);

                    // Debugging: Menampilkan isi matrix sebelum transformasi
                    System.out.println("Blok setelah konversi ke matrix:");
                    Block.printMatrix(matrixblock);

                    // Proses transformasi (rotasi dan cerminan)
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

                    // Debugging: Menampilkan hasil transformasi blok
                    System.out.println("Hasil transformasi blok:");
                    Block.printMatrix(block90);
                    Block.printMatrix(block180);
                    Block.printMatrix(block270);
                    Block.printMatrix(blockMirror);
                    Block.printMatrix(blockMirror90);
                    Block.printMatrix(blockMirror180);
                    Block.printMatrix(blockMirror270);

                    currentCharMatrix.clear();
                    allShape.clear();
                    currMax = -1;
                }

                currentCharMatrix.add(row);
                prevchar = currentChar;
            }

            if (!currentCharMatrix.isEmpty()) {
                // Debugging: Menampilkan isi blok terakhir sebelum diproses
                System.out.println("Blok terakhir sebelum konversi:");
                for (String[] row : currentCharMatrix) {
                    System.out.println(Arrays.toString(row));
                }
            
                // Pastikan setiap baris dalam blok terakhir memiliki panjang `currMax`
                for (int k = 0; k < currentCharMatrix.size(); k++) {
                    String[] rowMatrix = currentCharMatrix.get(k);
                    if (rowMatrix.length < currMax) {
                        String[] newRowMatrix = new String[currMax];
                        for (int z = 0; z < currMax; z++) {
                            if (z < rowMatrix.length) {
                                newRowMatrix[z] = (rowMatrix[z] != null) ? rowMatrix[z] : ".";  
                            } else {
                                newRowMatrix[z] = ".";  
                            }
                        }
                        currentCharMatrix.set(k, newRowMatrix);
                    }
                }
            
                matrix.add(currentCharMatrix.toArray(new String[0][]));
            
                // Konversi ke matriks
                String[][] matrixblock = convertToMatrix(currentCharMatrix);
            
                // Debugging: Pastikan blok terakhir dikonversi dengan benar
                System.out.println("Blok terakhir setelah konversi ke matrix:");
                Block.printMatrix(matrixblock);
            
                // Tambahkan semua rotasi dan cerminan
                allShape.add(matrixblock);
                allShape.add(Block.rotateBlock90(matrixblock));
                allShape.add(Block.rotateBlock180(matrixblock));
                allShape.add(Block.rotateBlock270(matrixblock));
                allShape.add(Block.mirrorVertical(matrixblock));
                allShape.add(Block.mirrorVertical(Block.rotateBlock90(matrixblock)));
                allShape.add(Block.mirrorVertical(Block.rotateBlock180(matrixblock)));
                allShape.add(Block.mirrorVertical(Block.rotateBlock270(matrixblock)));
                
                Block.printMatrix(block90);
                Block.printMatrix(block180);
                Block.printMatrix(block270);
                Block.printMatrix(blockMirror);
                Block.printMatrix(blockMirror90);
                Block.printMatrix(blockMirror180);
                Block.printMatrix(blockMirror270);
                
                // Tambahkan ke daftar blok
                Block block = new Block(prevchar.charAt(0), matrixblock, new ArrayList<>(allShape));
                blocklist.add(block);
            }

            reader.close();

            // Debugging: Menampilkan daftar blok sebelum dijalankan oleh BruteForce
            System.out.println("\nDaftar Blok yang Akan Diproses:");
            for (Block b : blocklist) {
                System.out.println("Block ID: " + b.getId());
                Block.printMatrix(Block.convertCharToString(b.getShape()));

            }

            BruteForce bruteforce = new BruteForce(board, blocklist);
            if (!bruteforce.solve()) {
                System.out.println("Solusi Tidak Ada");
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

        // Debugging: Menampilkan hasil konversi ke matrix
        System.out.println("Matrix hasil konversi:");
        for (String[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        return matrix;
    }

    
}
