package com.gui.guessio.documents;

import com.gui.guessio.ENUM.MatchStatus;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("matches")
public class Match {
    @Id
    public String id;

    private List<Game> attempts = new ArrayList<>();

    private Game answer;

    private MatchStatus status;

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

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus matchStatus) {
        this.status = matchStatus;
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

    public Match(String id, List<Game> attempts, Game answer,MatchStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.attempts = attempts;
        this.answer = answer;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
