package com.gui.guessio;

import com.gui.guessio.Repositories.GameRepository;
import com.gui.guessio.documents.Game;
import com.gui.guessio.script.Developer;
import com.gui.guessio.script.GameJsonResponse;
import com.gui.guessio.script.Publisher;
import com.gui.guessio.script.RawgGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;

@SpringBootApplication
@EnableMongoRepositories
public class GuessioApplication {
	public static void main(String[] args) {
		SpringApplication.run(GuessioApplication.class, args);
	}

}
