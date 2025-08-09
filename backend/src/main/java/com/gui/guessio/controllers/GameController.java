package com.gui.guessio.controllers;

import com.gui.guessio.documents.Game;
import com.gui.guessio.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins ="*", maxAge = 3600)
@RequestMapping("/api/v1")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }
    @GetMapping("/games")
    public ResponseEntity<List<String>> getGameNames(){
       List<String> gameNames = gameService.getAllGamesNames();

       return ResponseEntity.ok(gameNames);
    }
}
