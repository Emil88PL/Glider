package com.gameoflife.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GameOfLife {
  Logger log = LoggerFactory.getLogger(GameOfLife.class);

  private final BoardConfig boardConfig;

  public GameOfLife(BoardConfig boardConfig) {
    this.boardConfig = boardConfig;
  }

  private int rows;
  private int cols;
  private boolean[][] board;

  public GameOfLife() {
    this.boardConfig = new BoardConfig(20, 45);
    this.rows = boardConfig.getRows();
    this.cols = boardConfig.getCols();
    this.board = new boolean[rows][cols];
  }

  private boolean boardInitialized = false;
  public boolean isBoardInitialized() {
    return boardInitialized;
  }


  public GameOfLife(BoardConfig boardConfig, int rows, int cols) {
    this.boardConfig = boardConfig;
    this.rows = rows;
    this.cols = cols;
    this.board = new boolean[rows][cols];
  }


public void initializeBoard() {
  // Initialize the board with random live cells
  Random random = new Random();
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
      board[i][j] = random.nextBoolean();
    }
  }
  // Debug output
  log.debug("Trying to print initialize board");
  boardInitialized = true;
  printBoard();
}

  private void printBoard() {
    log.info("Initialize Board");
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
      //  System.out.print(board[i][j] ? "X" : " ");
      }
     // System.out.println();
    }
  }


  public boolean[][] getBoard() {
    return board;
  }

  public void updateBoard() {
    // Create a new board to store the next generation
    //System.out.println("Creating new board..."); // last to see
    boolean[][] newBoard = new boolean[rows][cols];

    // Apply the rules of the Game of Life to update the new board
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        int liveNeighbors = countLiveNeighbors(i, j);
      //  System.out.println("Cell (" + i + ", " + j + "): liveNeighbors = " + liveNeighbors);

        // Apply the rules
        if (board[i][j]) {
          // Cell is alive
          newBoard[i][j] = liveNeighbors == 2 || liveNeighbors == 3;
        } else {
          // Cell is dead
          newBoard[i][j] = liveNeighbors == 3;
        }
       // System.out.println("Result: " + (newBoard[i][j] ? "X" : " "));
      }
    }

    // Update the board with the new generation
   // System.out.println("Board updated");
    board = newBoard;
  }

  private int countLiveNeighbors(int row, int col) {
    int count = 0;

    // Check the eight neighboring cells
    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = col - 1; j <= col + 1; j++) {
        // Skip the current cell
        if (i == row && j == col) {
          continue;
        }

        // Check boundaries to avoid ArrayIndexOutOfBoundsException
        if (i >= 0 && i < rows && j >= 0 && j < cols) {
          // Count live neighbors
       //   System.out.println("Neighbor (" + i + ", " + j + "): " + (board[i][j] ? "X" : " "));
          if (board[i][j]) {
            count++;
          }
        }
      }
    }

    return count;
  }
}
