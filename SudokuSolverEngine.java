// Kimi Andrew Angkawinata langie
// CS 143
// HW #3: Sudoku #3 (solve)
// This program will read, validate, and check if already solved, and attempts to solve various Sudoku boards from called files. It will print the initial and final states of the sudoku board 
// along with the solution status and time taken.

public class SudokuSolverEngine {

   public static void main(String[] args) {
      // Here I have called my class `MySudokuBoard` if you named your class
      // differently, modify the line below to use your own class name
      SudokuBoard board = new SudokuBoard("boards/fast-solve.sdk");
      System.out.println("Initial board");
      System.out.println(board);
      System.out.println();
      
      if (!board.isValid()) {
          System.out.println("The board cannot be solved");
          return;
      }
      
      if (board.isSolved()) {
          System.out.println("The board is already solved.");
          return;
      }
      
      System.out.print("Solving board...");
      long start = System.currentTimeMillis();
      board.solve();
      long stop = System.currentTimeMillis();
      System.out.printf("SOLVED in %.3f seconds.\n", ((stop-start)/1000.0));
      System.out.println();
      System.out.println(board);
      
   }
}

/*
Using the very-fast-solve.sdk file 
  ----jGRASP exec: java -ea SudokuSolverEngine
 Initial board
 My Board:
 
 .34678912
 .72195348
 198342567
 ..9.61423
 .26853791
 .13924.56
 .61537284
 .8.419635
 345.86179
 
 
 Solving board...The Sudoku puzzle has been solved!
 SOLVED in 0,000 seconds.
 
 My Board:
 
 534678912
 672195348
 198342567
 859761423
 426853791
 713924856
 961537284
 287419635
 345286179
 
 
  ----jGRASP: Operation complete.
 
Using the fast-solve.sdk file
  ----jGRASP exec: java -ea SudokuSolverEngine
 Initial board
 My Board:
 
 827154396
 965.27148
 3416.9752
 .........
 .........
 61897.435
 786235.14
 1547968.3
 23984....
 
 
 Solving board...The Sudoku puzzle has been solved!
 SOLVED in 0,000 seconds.
 
 My Board:
 
 827154396
 965327148
 341689752
 472513689
 593468271
 618972435
 786235914
 154796823
 239841567
 
 
  ----jGRASP: Operation complete.
*/
