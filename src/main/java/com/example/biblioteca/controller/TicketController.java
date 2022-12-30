package com.example.biblioteca.controller;

import com.example.biblioteca.dto.req.TicketReqDto;
import com.example.biblioteca.service.ITicketService;
import com.example.biblioteca.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private ITicketService ticketService;

    public TicketController(TicketService ticketService) { this.ticketService = ticketService; }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(ticketService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(ticketService.getById(id),HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity<?> create(
            @Valid @RequestBody TicketReqDto ticketDto
    ){
        return new ResponseEntity<>(ticketService.create(ticketDto),HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TicketReqDto ticketDto){
        return new ResponseEntity<>(ticketService.update(id,ticketDto),HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnById(@RequestParam Long id){
        return new ResponseEntity<>(ticketService.returnById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Long id){
        return new ResponseEntity<>(ticketService.deleteById(id), HttpStatus.OK);
    }
}
