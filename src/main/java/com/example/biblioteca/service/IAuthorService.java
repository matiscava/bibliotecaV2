package com.example.biblioteca.service;

import com.example.biblioteca.dto.res.AuthorResDto;

import java.util.List;

public interface IAuthorService extends IBaseService<AuthorResDto,AuthorResDto>{
    List<AuthorResDto> getByName(String name);
    List<AuthorResDto> getByLastname(String lastname);
}
