package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.res.AdminResDto;
import com.example.biblioteca.entity.Admin;

public class AdminMapper {
    public static Admin convertEditDtoToEntity(Admin admin, AdminResDto adminDto){
        if (adminDto.getName() != null) admin.setName(adminDto.getName());
        if (adminDto.getLastname() != null) admin.setLastname(adminDto.getLastname());
        if (adminDto.getDni()!=0) admin.setDni(adminDto.getDni());
        if (adminDto.getPhone()!=0) admin.setPhone(adminDto.getPhone());
        if (adminDto.getEmail() != null) admin.setEmail(adminDto.getEmail());
        if (adminDto.getPassword() != null) admin.setPassword(adminDto.getPassword());

        return admin;
    }
}
