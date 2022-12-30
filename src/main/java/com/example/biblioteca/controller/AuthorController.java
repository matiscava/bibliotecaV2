package com.example.biblioteca.controller;

import com.example.biblioteca.dto.res.AuthorResDto;
import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.service.AuthorService;
import com.example.biblioteca.service.IAuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/author")
public class AuthorController {
    private IAuthorService authorService;

    public AuthorController(AuthorService authorService){ this.authorService = authorService; }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(authorService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable  String name){
        return new ResponseEntity<>(authorService.getByName(name),HttpStatus.OK);
    }

    @GetMapping("/getByLastname/{lastname}")
    public ResponseEntity<?> findByLastname(@PathVariable  String lastname){
        return new ResponseEntity<>(authorService.getByLastname(lastname),HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(
            @Valid @RequestBody AuthorResDto authorResDto
            ){
        return new ResponseEntity<>(authorService.create(authorResDto),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AuthorResDto authorDto){
        return new ResponseEntity<>(authorService.update(id, authorDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<SuccessfullyMessageDto> deleteById(@RequestParam Long id){
        return new ResponseEntity<>(authorService.deleteById(id), HttpStatus.OK);
    }
}
