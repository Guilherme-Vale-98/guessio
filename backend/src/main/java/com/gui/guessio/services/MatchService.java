package com.gui.guessio.services;

import com.gui.guessio.ENUM.MatchStatus;
import com.gui.guessio.Repositories.MatchRepository;
import com.gui.guessio.documents.Game;
import com.gui.guessio.documents.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private GameService gameService;
    public Match createNewMatch(){
        Match newMatch = new Match();
        newMatch.setStatus(MatchStatus.STARTED);
        Game randomGame = gameService.getRandomGame();

        newMatch.setAnswer(randomGame);
        return matchRepository.save(newMatch);
    }
}
