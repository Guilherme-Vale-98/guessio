package com.gui.guessio.Repositories;


import com.gui.guessio.documents.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {
}
