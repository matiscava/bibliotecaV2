package com.example.biblioteca.exception;

public class MaxBookLimitException extends RuntimeException{
    public MaxBookLimitException(String message) {
        super(message);
    }
}
