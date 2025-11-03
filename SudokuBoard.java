// Kimi Andrew Angkawinata Langie 
// CS 143
// HW #3: Sudoku #3 (solve)
// This program will read a Sudoku puzzle from a file, validates the initial configuration, and solve it using recursive backtracking.

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
   
   
   // Pre: Returns false
   // Post: The method will return true if the board contains only valid numbers
   // and has no duplicate value in any row, column, or 3x3 grid. If so, it will return false.
   public boolean isValid(){
      return validData() && checkRows() && checkCols() && checkMiniSquares();
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

   // Pre: Board has been initialized and not in an invalid state
   // Post: Tries to find out whether the board the board is solvable or not
   public void solve() {
       if (solveSudoku(0, 0)) {
           System.out.println("The Sudoku puzzle has been solved!");
       } else {
           System.out.println("The Sudoku puzzle is unsolvable.");
       }
   }

   // Pre: Cell is within bounds and a value will be placed
   // Post: Return true if a valid solution is found, if not returns false
   private boolean solveSudoku(int row, int col) {
       if (row == SIZE - 1 && col == SIZE) {
           return true;
       }

       if (col == SIZE) {
           row++;
           col = 0;
       }

       if (myBoard[row][col] != '.') {
           return solveSudoku(row, col + 1);
       }

       for (char num = '1'; num <= '9'; num++) {
           if (isValidMove(row, col, num)) {
               myBoard[row][col] = num;
               if (solveSudoku(row, col + 1)) {
                   return true;
               }
               myBoard[row][col] = '.'; 
           }
       }

       return false; 
   }
   
   // Pre: The cell is within the grid and the variable num is a digit between 1-9
   // Post: Returns true if placing num does not violate any sudoku rules. If so, returns false 
   private boolean isValidMove(int row, int col, char num) {
       for (int i = 0; i < SIZE; i++) {
           if (myBoard[row][i] == num || myBoard[i][col] == num) {
               return false;
           }
       }

       int startRow = row - row % 3;
       int startCol = col - col % 3;
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
               if (myBoard[i + startRow][j + startCol] == num) {
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