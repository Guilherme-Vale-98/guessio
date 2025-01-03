package com.gui.guessio.script;

import java.util.List;

public class GameJsonResponse {
    private List<RawgGame> results;

    public List<RawgGame> getResults() {
        return results;
    }

    public void setResults(List<RawgGame> results) {
        this.results = results;
    }
}
