package com.example.biblioteca.controller;

import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.dto.req.TitleReqDto;
import com.example.biblioteca.service.ITitleService;
import com.example.biblioteca.service.TitleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/title")
public class TitleController {
    private ITitleService titleService;

    public TitleController(TitleService titleService){ this.titleService = titleService; }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(titleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id){
        return new ResponseEntity<>(titleService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<?> findByName(@PathVariable  String title){
        return new ResponseEntity<>(titleService.getByTitle(title),HttpStatus.OK);
    }


    @PostMapping("/new")
    public ResponseEntity<?> create(
            @Valid @RequestBody TitleReqDto titleDto
            ){
        return new ResponseEntity<>(titleService.create(titleDto),HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TitleReqDto titleDto){
        return new ResponseEntity<>(titleService.update(id,titleDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<SuccessfullyMessageDto> deleteById(@RequestParam Long id){
        return new ResponseEntity<>(titleService.deleteById(id), HttpStatus.OK);
    }
}
