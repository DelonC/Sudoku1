import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MySudokuBoard {
   // fields creation
   private char [] [] board;
   
   // Loading the board from the file
   public MySudokuBoard(String filename) throws FileNotFoundException{
         
         board = new char[9][9];
         Scanner scanner = new Scanner(new File(filename));
         int r = 0;
         while(scanner.hasNextLine() && r < 9){
            String line = scanner.nextLine();
            for(int c = 0 ; c < line.length() ; c++){
               board[r][c] = line.charAt(c); 
            }
            r++;
         }

   }
   
   // Display the board
   public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
            char val = board[i][j];
            sb.append(val == '.' ? ". " : val + " ");
        }
        sb.append("\n");
    }
    return sb.toString();
    }
    
    // private helpers checking valid characters
    private boolean validData() {
      Set<Character> validChars = new HashSet<>();
      for (char c = '1'; c <= '9'; c++) {
         validChars.add(c);
      }
      validChars.add('.');

      for (int r = 0; r < 9; r++) {
         for (int c = 0; c < 9; c++) {
            if (!validChars.contains(board[r][c])) {
               return false;
            }
         }
      }
      return true;
   }

   // No duplicate numbers in any row
   private boolean validRows() {
      for (int r = 0; r < 9; r++) {
         Set<Character> seen = new HashSet<>();
         for (int c = 0; c < 9; c++) {
            char val = board[r][c];
            if (val != '.') {
               if (seen.contains(val)) {
                  return false;
               }
               seen.add(val);
            }
         }
      }
      return true;
   }

   // No duplicate numbers in any column
   private boolean validColumns() {
      for (int c = 0; c < 9; c++) {
         Set<Character> seen = new HashSet<>();
         for (int r = 0; r < 9; r++) {
            char val = board[r][c];
            if (val != '.') {
               if (seen.contains(val)) {
                  return false;
               }
               seen.add(val);
            }
         }
      }
      return true;
   }

   // No duplicate numbers in any 3x3 mini-square
   private boolean validMiniSquares() {
      for (int spot = 1; spot <= 9; spot++) {
         if (!validMiniSquare(spot)) {
            return false;
         }
      }
      return true;
   }

   // Validate a specific mini-square
   private boolean validMiniSquare(int spot) {
      Set<Character> seen = new HashSet<>();
      for (int r = 0; r < 3; r++) {
         for (int c = 0; c < 3; c++) {
            char val = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
            if (val != '.') {
               if (seen.contains(val)) {
                  return false;
               }
               seen.add(val);
            }
         }
      }
      return true;
    }
    
    // 
    private char[][] miniSquare(char spot) {
      char[][] mini = new char[3][3];
      for(char r = 0; r < 3; r++) {
         for(char c = 0; c < 3; c++) {
            // whoa - wild! This took me a solid hour to figure out (at least)
            // This translates between the "spot" in the 9x9 Sudoku board
            // and a new mini square of 3x3
            mini[r][c] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
      return mini;
   }


    // check if the board is valid
    public boolean isValid() {
      return validData() && validRows() && validColumns() && validMiniSquares();
    }
    
    
    // check if the board is solved
    public boolean isSolved() {
      //Checks if the board is valid
      if (!isValid()) {
        return false;
      }
     
      Map<Character, Integer> counts = new HashMap<>();
      //Loops through the whole board and counts how many times each number appears
      for (int r = 0; r < board.length; r++) {
         for (int c = 0; c < board.length; c++) {
            char value = board[r][c];
            
            if(value == '.') {
               return false;
            }
           
           counts.put(value, counts.getOrDefault(value, 0) + 1);
           
         }
      }
      //Checks if each number appeared 9 times
      for (char num = '1'; num <= '9'; num++) {
         if (!counts.containsKey(num) || counts.get(num) != 9) {
            return false;
         }
      }
      //If every condition was passed, the board is solved
      return true;
   }
    
}