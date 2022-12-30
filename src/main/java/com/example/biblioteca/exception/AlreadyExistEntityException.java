package com.example.biblioteca.exception;

public class AlreadyExistEntityException extends RuntimeException{
    public AlreadyExistEntityException(String message) {
        super(message);
    }
}
