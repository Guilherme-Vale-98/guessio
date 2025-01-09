package com.gui.guessio.controllers;

import com.gui.guessio.documents.Match;
import com.gui.guessio.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MatchController {
    @Autowired
    private MatchService matchService;
    @GetMapping("/matches")
    public Match startMatch(){
        return matchService.createNewMatch();
    }

}
