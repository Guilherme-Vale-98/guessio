package com.gui.guessio.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("games")
public class Game {
    @Id
    public String id;
    private String name;
    private List<String> platforms;
    private List<String> genres;
    private List<String> themes;
    private String releaseYear;
    private List<String> gameModes;
    private String developer;
    private String publisher;
    private List<String> perspectives;
    private String franchise;



}
