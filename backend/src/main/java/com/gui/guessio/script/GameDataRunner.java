package com.gui.guessio.script;

import com.gui.guessio.Repositories.GameRepository;
import com.gui.guessio.documents.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class GameDataRunner implements CommandLineRunner {

    @Value("${rawgAPI}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Game> games = gameRepository.findAll();
        if(games.isEmpty()){
            games = fetchGamesFromAPI();
            saveGamesToDatabase(games);
        }
    }

    private List<Game> fetchGamesFromAPI() {
        List<Game> allGames = new ArrayList<>();
        for (int page = 1; page < 11; page++) {
            String apiUrl = "https://api.rawg.io/api/games?key=" + apiKey + "&ordering=-metacritic&page=" + page + "&page_size=60";
            GameJsonResponse gameJsonResponse = restTemplate.getForObject(apiUrl, GameJsonResponse.class);

            if (gameJsonResponse.getResults() != null) {
                gameJsonResponse.getResults()
                        .sort(Comparator.comparingInt(RawgGame::getRatingsCount).reversed());
                gameJsonResponse.getResults().forEach(rawgGame -> {
                    Game game = new Game();
                    game.setName(rawgGame.getName());
                    game.setReleased(rawgGame.getReleased());
                    game.setRating(rawgGame.getRating());
                    game.setMetacritic(rawgGame.getMetacritic());
                    game.setRatingsCount(rawgGame.getRatingsCount());
                    game.getImageUrls().add(rawgGame.getBackgroundImage());

                    String gameId = String.valueOf(rawgGame.getId());
                    String gameIdApi = "https://api.rawg.io/api/games/" + gameId + "?key=" + apiKey;
                    RawgGame gameResponse = restTemplate.getForObject(gameIdApi, RawgGame.class);

                    gameResponse.getDevelopers().forEach(developer -> game.getDevelopers().add(developer.getName()));
                    gameResponse.getPlatforms().forEach(platformWrapper -> game.getPlatforms().add(platformWrapper.getPlatform().getName()));
                    gameResponse.getGenres().forEach(genre -> game.getGenres().add(genre.getName()));
                    gameResponse.getPublishers().forEach(publisher -> game.getPublishers().add(publisher.getName()));

                    allGames.add(game);
                });
            } else {
                System.out.println("No games found in API response for page: " + page);
            }
        }
        return allGames;
    }

    private void saveGamesToDatabase(List<Game> games) {
        games.forEach(game -> {
            gameRepository.save(game);
            System.out.println("Saved game: " + game.getName());
        });
        System.out.println("All games saved to the database.");
    }

}
