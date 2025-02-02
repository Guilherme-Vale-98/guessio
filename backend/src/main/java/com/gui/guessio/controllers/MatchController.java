package com.gui.guessio.controllers;

import com.gui.guessio.documents.Game;
import com.gui.guessio.documents.Match;
import com.gui.guessio.services.GameService;
import com.gui.guessio.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins ="http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/v1")
public class MatchController {
    @Autowired
    private GameService gameService;
    @Autowired
    private MatchService matchService;
    @GetMapping("/match")
    public Match startMatch(){
        return matchService.createNewMatch();
    }

    @PostMapping("/matches/{id}")
    public ResponseEntity<Match> matchTry(@PathVariable String id, @RequestBody Map<String, String> requestBody){
        String gameTitle = requestBody.get("gameTitle");
        if (gameTitle == null || gameTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Game title must not be null or empty.");
        }

        Game attempt = gameService.getGameByName(gameTitle);

        Match match = matchService.getMatchById(id);
        matchService.tryGame(attempt, match);

        return new ResponseEntity<Match>(match, HttpStatus.OK);
    }

}
