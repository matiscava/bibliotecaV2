package com.example.biblioteca.controller;


import com.example.biblioteca.dto.req.TitleReqDto;
import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.dto.res.UserResDto;
import com.example.biblioteca.service.IUserService;
import com.example.biblioteca.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private IUserService userService;

    public UserController(UserService userService){ this.userService = userService; }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id){
        return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable  String name){
        return new ResponseEntity<>(userService.getByName(name),HttpStatus.OK);
    }

    @GetMapping("/getByLastname/{lastname}")
    public ResponseEntity<?> findByLastname(@PathVariable  String lastname){
        return new ResponseEntity<>(userService.getByLastname(lastname),HttpStatus.OK);
    }

    @GetMapping("/getByDni/{dni}")
    public ResponseEntity<?> findByDni(@PathVariable  int dni){
        return new ResponseEntity<>(userService.getByDni(dni),HttpStatus.OK);
    }

    @GetMapping("/getTickets/{id}")
    public ResponseEntity<?> findTickets(@PathVariable  Long id){
        return new ResponseEntity<>(userService.getTickets(id),HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(
            @Valid @RequestBody UserResDto userResDto
            ){
        return new ResponseEntity<>(userService.create(userResDto),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserResDto userDto){
        return new ResponseEntity<>(userService.update(id,userDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<SuccessfullyMessageDto> deleteById(@RequestParam Long id){
        return new ResponseEntity<>(userService.deleteById(id),HttpStatus.OK);
    }
}
