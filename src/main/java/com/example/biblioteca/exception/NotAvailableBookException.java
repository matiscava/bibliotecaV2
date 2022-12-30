package com.example.biblioteca.exception;

public class NotAvailableBookException extends RuntimeException {
    public NotAvailableBookException(String message) {
        super(message);
    }
}
