package com.example.biblioteca.service;


import com.example.biblioteca.dto.req.BookReqDto;
import com.example.biblioteca.dto.res.BookResDto;

import java.util.List;

public interface IBookService extends IBaseService<BookResDto, BookReqDto>{
    List<BookResDto> getAvailable();
    List<BookResDto> getDisabled();
}
