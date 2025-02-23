package src;
import src.*;
import java.util.*;

public class BruteForce {
    private Board board;
    private List<Block> blocklist;
    private boolean success;
    private int count;

    public BruteForce(Board board, List<Block> blocklist) {
        this.board = board;
        this.blocklist = blocklist;
        this.success = false;
        this.count = 0;
    }

    public boolean solve() {
        boolean result = putBlock(0);
        System.out.println("Banyak kasus yang ditinjau : " + count );
        System.out.println();
        return result;
    }

    private boolean putBlock(int index) {
        if (index == blocklist.size()) {
            if (board.isComplete()) {
                printSolution();
                success = true;
                return true;
            }
            return false;
        }

        Block block = blocklist.get(index);
        List<String[][]> shapes = block.getAllShape();
        
        for (int shapeIndex = 0; shapeIndex < shapes.size(); shapeIndex++) {
            for (int x = 0; x < board.getGrid().length; x++) {
                for (int y = 0; y < board.getGrid()[0].length; y++) {
                    count += 1;
                    if (board.isPlaceAble(block, shapeIndex, x, y)) {
                        board.placeBlock(block, shapeIndex, x, y);
                        if (putBlock(index + 1)) {
                            return true;
                        }
                        board.removeBlock(block, shapeIndex, x, y);
                    }
                }
            }
        }
        
        return false;
    }

    private void printSolution() {
        System.out.println("Solusi ditemukan:");
        board.printBoard();
    }
}
