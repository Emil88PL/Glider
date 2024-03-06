package com.gameoflife.game;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

  private final GameOfLife gameOfLife;
  private int generationCount = 0;

  public GameController(GameOfLife gameOfLife) {
    this.gameOfLife = gameOfLife;
    System.out.println("GameController initialized");
  }

  @GetMapping("/game")
  public String test(Model model) {
    // Initialize the board if needed
    if (!gameOfLife.isBoardInitialized()) {
      gameOfLife.initializeBoard();
    }

    // Update the board for the next generation
    gameOfLife.updateBoard();
    generationCount++;

    // Pass the board to the Thymeleaf template
    model.addAttribute("board", gameOfLife.getBoard());
    model.addAttribute("generationCount", generationCount);
    return "game";
  }

  @PostMapping("/restart")
  public String restartGame(Model model) {
    // Reset the game state, initialize a new board, reset generation count, etc.
    gameOfLife.initializeBoard();
    generationCount = 0;

    // Pass the updated board and generation count to the Thymeleaf template
    model.addAttribute("board", gameOfLife.getBoard());
    model.addAttribute("generationCount", generationCount);

    return "game";
  }
}