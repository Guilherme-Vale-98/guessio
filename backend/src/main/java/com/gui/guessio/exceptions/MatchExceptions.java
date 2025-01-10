package com.gui.guessio.exceptions;

public class MatchExceptions {

    public static class MatchNotFoundException extends RuntimeException {
        public MatchNotFoundException(String message) {
            super(message);
        }
    }

    public static class MatchFinishedException extends RuntimeException {
        public MatchFinishedException(String message) {
            super(message);
        }
    }

    public static class DuplicateAttemptException extends RuntimeException {
        public DuplicateAttemptException(String message) {
            super(message);
        }
    }
    public static class MaximumAttemptsReachedException extends RuntimeException {
        public MaximumAttemptsReachedException(String message) {
            super(message);
        }
    }
}