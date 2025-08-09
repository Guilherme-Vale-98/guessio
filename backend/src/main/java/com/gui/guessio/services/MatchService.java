package com.gui.guessio.services;

import com.gui.guessio.ENUM.MatchStatus;
import com.gui.guessio.Repositories.MatchRepository;
import com.gui.guessio.documents.Game;
import com.gui.guessio.documents.Match;
import com.gui.guessio.exceptions.MatchExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private GameService gameService;

    public Match createNewMatch() {
        Match newMatch = new Match();
        newMatch.setStatus(MatchStatus.STARTED);
        Game randomGame = gameService.getRandomGame();

        newMatch.setAnswer(randomGame);
        return matchRepository.save(newMatch);
    }

    public Match getMatchById(String id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new MatchExceptions.MatchNotFoundException("Match with ID '" + id + "' not found."));
    }

    public boolean tryGame(Game attempt, Match match) {
        if (match.getStatus() == MatchStatus.FINISHED) {
            throw new MatchExceptions.MatchFinishedException("This match is finished.");
        }

        if (match.getAttempts().size() > 5) {
            throw new MatchExceptions.MaximumAttemptsReachedException("Maximum attempts reached. The match is now finished.");
        }
        if(match.getAttempts().contains(attempt)){
            throw new MatchExceptions.DuplicateAttemptException("This game has already been attempted.");
        }

        match.getAttempts().add(attempt);

        boolean isCorrect = attempt.getName().equalsIgnoreCase(match.getAnswer().getName());

        if (isCorrect) {
            match.setStatus(MatchStatus.FINISHED);
        }
        if(match.getAttempts().size() == 5){
            match.setStatus(MatchStatus.FINISHED);
        }
        matchRepository.save(match);

        return isCorrect;
    }
}