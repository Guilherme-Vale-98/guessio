package com.gui.guessio.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "platforms")
public class Platform {

    @Id
    private String id;

    private String name;

    public String getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Platform() {
    }

    public Platform(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
