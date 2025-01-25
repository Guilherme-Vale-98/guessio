package com.gui.guessio.Repositories;


import com.gui.guessio.documents.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends MongoRepository<Game, String> {
    Optional<Game> findByName(String name);
    @Query(value = "{}", fields = "{ 'name' : 1, '_id' : 0 }")
    List<Game> findAllGameNames();
}
