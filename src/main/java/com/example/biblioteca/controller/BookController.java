package com.example.biblioteca.controller;

import com.example.biblioteca.dto.req.BookReqDto;
import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.service.BookService;
import com.example.biblioteca.service.IBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {
    private IBookService bookService;

    public BookController(BookService bookService){ this.bookService = bookService; }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id){
        return new ResponseEntity<>(bookService.getById(id),HttpStatus.OK);
    }
    @GetMapping("/getAvailabled")
    public ResponseEntity<?> findAvailable(){
        return new ResponseEntity<>(bookService.getAvailable(), HttpStatus.OK);
    }    @GetMapping("/getDisabled")
    public ResponseEntity<?> findDisabled(){
        return new ResponseEntity<>(bookService.getDisabled(), HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity<?> create(
            @Valid @RequestBody BookReqDto bookDto
    ){
        return new ResponseEntity<>(bookService.create(bookDto),HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody BookReqDto bookDto){
        return new ResponseEntity<>(bookService.update(id, bookDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<SuccessfullyMessageDto> deleteById(@RequestParam Long id){
        return new ResponseEntity<>(bookService.deleteById(id), HttpStatus.OK);
    }
}
