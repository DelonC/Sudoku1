import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

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
               result += number;
            }
         }
         result += "\n";
      }
      return result;
   }
   
   public boolean isSolved() {
      //Checks if the board is valid
      if (!isValid()) {
        return false;
      }
      
      Map<Integer, Integer> counts = new HashMap<>();
      //Loops through the whole board and counts how many times each number appears
      for (int r = 0; r < board.length; r++) {
         for (int c = 0; c < board.length; c++) {
            int value = board[r][c];
            if(value == 0) {
               return false;
            }
            
            if (value != 0) {
               if (counts.containsKey(value)) {
                  counts.put(value, counts.get(value) + 1);
               }
               else {
                  counts.put(value, 1);
               }
            } 
         }
      }
      //Checks if each number appeared 9 times
      for (int num = 1; num <= 9; num++) {
         if (!counts.containsKey(num) || counts.get(num) != 9) {
            return false;
         }
      }
      //If every condition was passed, the board is solved
      return true;
   }
}   