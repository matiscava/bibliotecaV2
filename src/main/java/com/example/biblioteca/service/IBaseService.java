package com.example.biblioteca.service;

import com.example.biblioteca.dto.res.SuccessfullyMessageDto;

import java.util.List;

public interface IBaseService<T,Y> {
    List<T> getAll();
    SuccessfullyMessageDto create(Y object);
    T getById(Long id);
    SuccessfullyMessageDto update(Long id, Y object);
    SuccessfullyMessageDto deleteById(Long id);
}
