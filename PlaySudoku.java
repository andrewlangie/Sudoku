// Kimi Andrew Angkawinata Langie
// CS 143
// HW #1: Sudoku #1 (Board Setup)
// This program will run the sudoku board game

public class PlaySudoku {
    public static void main(String[] args) {

        String filePath = "data1.sdk";

        SudokuBoard sudokuBoard = new SudokuBoard(filePath);

        System.out.println(sudokuBoard);
    }
}

/*
  ----jGRASP exec: java PlaySudoku
 2     1   5     3 
   5 4       7 1   
   1   2   3   8   
 6   2 8   7 3   4 
                   
 1   5 3   9 8   6 
   2   7   1   6   
   8 1       2 4   
 7     4   2     1 
 
 
  ----jGRASP: Operation complete.
*/