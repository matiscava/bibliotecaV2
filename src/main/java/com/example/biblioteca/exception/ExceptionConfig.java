package com.example.biblioteca.exception;

import com.example.biblioteca.dto.res.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<?> idNotFoundException(IdNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(404,ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MaxBookLimitException.class)
    public ResponseEntity<?> maxBookLimitException(MaxBookLimitException ex){
        ErrorDto errorDto = new ErrorDto(406, ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(NotAvailableBookException.class)
    public ResponseEntity<?> notAvailableBookException(NotAvailableBookException ex){
        ErrorDto errorDto = new ErrorDto(406, ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AlreadyExistEntityException.class)
    public ResponseEntity<?> alreadyExistEntityException(AlreadyExistEntityException ex){
        ErrorDto errorDto = new ErrorDto(406, ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFounException(EntityNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(406, ex.getMessage());
        return new ResponseEntity<>(errorDto,HttpStatus.NOT_FOUND);
    }
}
