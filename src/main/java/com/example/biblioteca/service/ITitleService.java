package com.example.biblioteca.service;

import com.example.biblioteca.dto.req.TitleReqDto;
import com.example.biblioteca.dto.res.TitleResDto;

import java.util.List;

public interface ITitleService extends IBaseService<TitleResDto, TitleReqDto>{

    List<TitleResDto> getByTitle(String title);
}
