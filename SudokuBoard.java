// Kimi Andrew Angkawinata Langie
// CS 143
// HW #1: Sudoku #1 (Board Setup)
// This program will setup the sudoku board by reading sudoku puzzle from a specific file

import java.io.*;

public class SudokuBoard {
    private char[][] board; 
    
    //PRE: filepath must be valid and should contain 9 lines with up to 9 characters each line
    //POST: the board is initialized with values from the file.
    public SudokuBoard(String filePath) {
        this.board = new char[9][9];
        loadBoardFromFile(filePath);
    }
    
   //PRE: filepath must be valid to a sudoku file
   //POST: if a line contains more than 9 characters, only the first 9 are going to be used and if an IOException occurs, an error message will pop up
    private void loadBoardFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null && row < 9) {
                line = line.trim();
                if (line.length() > 9) {
                    line = line.substring(0, 9);
                }
                
                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    board[row][col] = (c == '.') ? ' ' : c;
                }
                row++;
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    
    //PRE: the board array has been initialized and has values
    //POST: a string will be returned where each row has a line of text and values
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                result.append(board[row][col]).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }
    
    // TODO: create an isValid method
    
    // TODO: create an is Solved method
}