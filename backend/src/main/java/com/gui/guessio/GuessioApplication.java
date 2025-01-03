package com.gui.guessio;

import com.gui.guessio.Repositories.GameRepository;
import com.gui.guessio.Repositories.PlatformRepository;
import com.gui.guessio.documents.Game;
import com.gui.guessio.documents.Platform;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GuessioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuessioApplication.class, args);
	}
	@Bean
	public CommandLineRunner apiRequest(GameRepository gameRepository, PlatformRepository platformRepository) {
		return (args) -> {
			HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/platforms")
					.header("Client-ID", "")
					.header("Authorization", "Bearer ")
					.header("Accept", "application/json")
					.body("fields name; search \"game boy\";")
					.asJson();

		};
	}
}
