package com.gui.guessio.exceptions;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String message){
            super(message);
    }
}
