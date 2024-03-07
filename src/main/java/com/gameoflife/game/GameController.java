package com.gameoflife.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {
  Logger log = LoggerFactory.getLogger(GameController.class);

  private final GameOfLife gameOfLife;
  private int generationCount = 0;
  private final String BOARD = "board";
  private final String GENERATION_COUNT = "generationCount";

  public GameController(GameOfLife gameOfLife) {
    this.gameOfLife = gameOfLife;
    log.info("GameController initialized");
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
    log.info("Generation count from game: {}", generationCount);
    // Pass the board to the Thymeleaf template
    model.addAttribute(BOARD, gameOfLife.getBoard());
    model.addAttribute(GENERATION_COUNT, generationCount);
    return "game";
  }
  @GetMapping("/update")
  public String updateGame(Model model) {
    // Update the board for the next generation
    gameOfLife.updateBoard();
    generationCount++;
    log.info("Generation count from update: {}", generationCount);

    // Pass the updated board and generation count to the Thymeleaf template
    model.addAttribute(BOARD, gameOfLife.getBoard());
    model.addAttribute(GENERATION_COUNT, generationCount);

    return "game :: boardInner";  // Return a Thymeleaf fragment
  }


  @PostMapping("/restart")
  public String restartGame(Model model) {
    // Reset the game state, initialize a new board, reset generation count, etc.
    log.info("Restart game");
    gameOfLife.initializeBoard();
    generationCount = 0; // Reset the generation count to 0
    log.info("Generation count after restart: {}", generationCount);
    // Pass the updated board and generation count to the Thymeleaf template
    model.addAttribute(BOARD, gameOfLife.getBoard());
    model.addAttribute(GENERATION_COUNT, generationCount);

    return "game :: board";
  }
}