package com.gui.guessio.documents;

import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("matches")
public class Match {
    @Id
    public String id;

    private List<Game> attempts;

    private Game answer;

    private YamlProcessor.MatchStatus status;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Game> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Game> attempts) {
        this.attempts = attempts;
    }

    public Game getAnswer() {
        return answer;
    }

    public void setAnswer(Game answer) {
        this.answer = answer;
    }

    public YamlProcessor.MatchStatus getStatus() {
        return status;
    }

    public void setStatus(YamlProcessor.MatchStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Match() {
    }

    public Match(String id, List<Game> attempts, Game answer, YamlProcessor.MatchStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.attempts = attempts;
        this.answer = answer;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
