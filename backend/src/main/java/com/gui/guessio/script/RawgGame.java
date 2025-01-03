package com.gui.guessio.script;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RawgGame {
    private int id;
    private String name;
    private String released;
    private double rating;
    private int metacritic;

    @JsonProperty("ratings_count")
    private int ratingsCount;
    private List<Genre> genres;
    private List<PlatformWrapper> platforms;

    private List<Developer> developers;
    private List<Publisher> Publishers;
    private List<Tag> tags;
    @JsonProperty("background_image")
    private String backgroundImage;

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public List<Publisher> getPublishers() {
        return Publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        Publishers = publishers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(int metacritic) {
        this.metacritic = metacritic;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<PlatformWrapper> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformWrapper> platforms) {
        this.platforms = platforms;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }
}

