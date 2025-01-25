package com.gui.guessio.services;

import com.gui.guessio.Repositories.GameRepository;
import com.gui.guessio.documents.Game;
import com.gui.guessio.exceptions.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Game getRandomGame(){
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.sample(1));
        AggregationResults<Game> results =mongoTemplate.aggregate(aggregation, "games", Game.class);

        return results.getUniqueMappedResult();
    }

    public Game getGameByName(String name) {
        return gameRepository.findByName(name)
                .orElseThrow(() -> new GameNotFoundException("Game with name '" + name + "' not found."));
    }

    public List<String> getAllGamesNames(){
        List<Game> allGames = gameRepository.findAllGameNames();
        return allGames.stream().map(Game::getName).toList();
    }
}
