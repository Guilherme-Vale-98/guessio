package com.gui.guessio.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document("games")
public class Game {
    @Id
    public String id;
    private String name;
    private String released;
    private double rating;
    private int metacritic;

    private int ratingsCount;
    private List<String> genres = new ArrayList<>();
    private List<String> platforms = new ArrayList<>();
    private List<String> imageUrls = new ArrayList<>();

    private List<String> developers = new ArrayList<>();
    private List<String> publishers = new ArrayList<>();
    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public Game() {
    }

    public List<String> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public Game(String id, String name, String released, double rating, int metacritic, int ratingsCount, List<String> genres, List<String> platforms, List<String> imageUrls, List<String> developers, List<String> publishers) {
        this.id = id;
        this.name = name;
        this.released = released;
        this.rating = rating;
        this.metacritic = metacritic;
        this.ratingsCount = ratingsCount;
        this.genres = genres;
        this.platforms = platforms;
        this.imageUrls = imageUrls;
        this.developers = developers;
        this.publishers = publishers;
    }


    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", released='" + released + '\'' +
                ", rating=" + rating +
                ", metacritic=" + metacritic +
                ", ratingsCount=" + ratingsCount +
                ", genres=" + genres +
                ", platforms=" + platforms +
                ", imageUrls=" + imageUrls +
                ", developers=" + developers +
                ", publishers=" + publishers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Double.compare(getRating(), game.getRating()) == 0 && getMetacritic() == game.getMetacritic() && getRatingsCount() == game.getRatingsCount() && Objects.equals(getId(), game.getId()) && Objects.equals(getName(), game.getName()) && Objects.equals(getReleased(), game.getReleased()) && Objects.equals(getGenres(), game.getGenres()) && Objects.equals(getPlatforms(), game.getPlatforms()) && Objects.equals(getImageUrls(), game.getImageUrls()) && Objects.equals(getDevelopers(), game.getDevelopers()) && Objects.equals(getPublishers(), game.getPublishers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getReleased(), getRating(), getMetacritic(), getRatingsCount(), getGenres(), getPlatforms(), getImageUrls(), getDevelopers(), getPublishers());
    }
}
