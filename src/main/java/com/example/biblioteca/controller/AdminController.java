package com.example.biblioteca.controller;

import com.example.biblioteca.dto.res.AdminResDto;
import com.example.biblioteca.service.AdminService;
import com.example.biblioteca.service.IAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private IAdminService adminService;

    public AdminController(AdminService adminService){ this.adminService = adminService; }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(adminService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(adminService.getById(id),HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity<?> create(
            @Valid @RequestBody AdminResDto adminResDto
    ){
        return new ResponseEntity<>(adminService.create(adminResDto),HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AdminResDto adminDto){
        return new ResponseEntity<>(adminService.update(id, adminDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Long id){
        return new ResponseEntity<>(adminService.deleteById(id),HttpStatus.OK);
    }
}
