import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SudokuBoard {
   private int[][] board;
   
   public SudokuBoard(String fileName) {
      board = new int [9][9];
      try {
         Scanner input = new Scanner(new File(fileName)); // This is trying to open the file and read it
         for(int r = 0; r < board.length; r++) {
            String line = input.nextLine();
            for(int c = 0; c < board.length; c++) {
               char cell = line.charAt(c);
               if (cell == '.') {
                  board[r][c] = 0;
               }
               else {
                  board[r][c] = cell - '0';
               }
            }
         }         
         }
         catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
         }
   }
    
   public String toString() {
      String result = "";
      for(int r = 0; r < board.length; r++) {
         for(int c = 0; c < board.length; c++) {
            int number = board[r][c];
            if (number == 0) {
               result += ".";
            }
            else {
               result += "number";
            }
         }
         result += "\n";
      }
      return result;
   }
}   