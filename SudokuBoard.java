// Kimi Andrew Angkawinata Langie 
// CS 143
// HW Core Topics: HW #2: Sudoku #2 (isValid, isSolved)
// This program will check and validate the solution of the given Sudoku board

import java.util.*;
import java.io.*;

public class SudokuBoard {
   public final int SIZE = 9;
   protected char[][] myBoard;
   
   public SudokuBoard(String theFile) {
      myBoard = new char[SIZE][SIZE];
      try {
         Scanner file = new Scanner(new File(theFile));
         for(int row = 0; row < SIZE; row++) {
            String theLine = file.nextLine();
            for(int col = 0; col < theLine.length(); col++) {
               myBoard[row][col] = theLine.charAt(col);
            }
         }
      } catch(Exception e) {
         System.out.println("Something went wrong :(");
         e.printStackTrace();
      }
   }
   
   // Pre: Returns false
   // Post: The method will return true if the board contains only valid numbers
   // and has no duplicate value in any row, column, or 3x3 grid. If so, it will return false.
   public boolean isValid(){
      return validData() && checkRows() && checkCols() && checkMiniSquares();
   }
   
   // Pre: Returns false
   // Post: The method will return true if the board is solved based on the sudoku rules.
   // else it returns false
   public boolean isSolved(){
   
      if (!isValid()) return false;

      Map<Character, Integer> countMap = new HashMap<>();
      for (char num = '1'; num <= '9'; num++) {
         countMap.put(num, 0);
      }
      
      for (int row = 0; row < SIZE; row++) {
         for (int col = 0; col < SIZE; col++) {
            char val = myBoard[row][col];
            if (val >= '1' && val <= '9') {
               countMap.put(val, countMap.get(val) + 1);
            }
         }
      }

      for (int count : countMap.values()) {
         if (count != 9) {
            return false;
         }
      }

      return true;
   }
   
   // Check whether all data is valid
   private boolean validData() {
      for (int row = 0; row < SIZE; row++) {
         for (int col = 0; col < SIZE; col++) {
            char val = myBoard[row][col];
            if ((val < '1' || val > '9') && val != '.') {
               return false;
            }
         }
      }
      return true;
   }

   // Check whether any row contains duplicates
   private boolean checkRows() {
      for (int row = 0; row < SIZE; row++) {
         Set<Character> seen = new HashSet<>();
         for (int col = 0; col < SIZE; col++) {
            char val = myBoard[row][col];
            if (val != '.' && !seen.add(val)) {
               return false;
            }
         }
      }
      return true;
   }

   // Check whether any column contains duplicates
   private boolean checkCols() {
      for (int col = 0; col < SIZE; col++) {
         Set<Character> seen = new HashSet<>();
         for (int row = 0; row < SIZE; row++) {
            char val = myBoard[row][col];
            if (val != '.' && !seen.add(val)) {
               return false;
            }
         }
      }
      return true;
   }

   // Check whether each 3x3 mini-square contains duplicates
   private boolean checkMiniSquares() {
      for (int startRow = 0; startRow < SIZE; startRow += 3) {
         for (int startCol = 0; startCol < SIZE; startCol += 3) {
            if (!checkMiniSquare(startRow, startCol)) {
               return false;
            }
         }
      }
      return true;
   }

   // Helper to check a specific 3x3 mini-square for duplicates
   private boolean checkMiniSquare(int startRow, int startCol) {
      Set<Character> seen = new HashSet<>();
      for (int row = 0; row < 3; row++) {
         for (int col = 0; col < 3; col++) {
            char val = myBoard[startRow + row][startCol + col];
            if (val != '.' && !seen.add(val)) {
               return false;
            }
         }
      }
      return true;
   }
   
   public String toString() {
      String result = "My Board:\n\n";
      for(int row = 0; row < SIZE; row++) {
         for(int col = 0; col < SIZE; col++) {
            result += (myBoard[row][col]);
         }
         result += ("\n");
      }
      return result;
   }

}