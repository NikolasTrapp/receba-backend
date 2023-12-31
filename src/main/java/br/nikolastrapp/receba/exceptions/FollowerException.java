package br.nikolastrapp.receba.exceptions;

public class FollowerException extends RuntimeException {

    public FollowerException(String message) {
        super(message);
    }

    public FollowerException(String message, Throwable cause) {
        super(message, cause);
    }
}
