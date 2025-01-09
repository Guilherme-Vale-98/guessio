package com.gui.guessio.services;

import com.gui.guessio.documents.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Game getRandomGame(){
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.sample(1));
        AggregationResults<Game> results =mongoTemplate.aggregate(aggregation, "games", Game.class);

        return results.getUniqueMappedResult();
    }
}
