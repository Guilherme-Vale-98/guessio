package com.gui.guessio.Repositories;

import com.gui.guessio.documents.Platform;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends MongoRepository<Platform, String> {
}
