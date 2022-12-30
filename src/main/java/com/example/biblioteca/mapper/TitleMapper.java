package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.req.TitleReqDto;
import com.example.biblioteca.dto.res.BookResDto;
import com.example.biblioteca.dto.res.TitleResDto;
import com.example.biblioteca.entity.Author;
import com.example.biblioteca.entity.Title;

import java.util.ArrayList;
import java.util.List;

public class TitleMapper {
    public static Title convertResDtoToEntity(TitleReqDto titleDto, Author a){
        Title t = new Title();
        t.setId(0L);
        t.setTitle(titleDto.getTitle());
        t.setAuthor(a);
        return t;
    }

    public static TitleResDto convertEntityToResDto(Title t){
        TitleResDto titleDto = new TitleResDto();
        titleDto.setAuthor(t.getAuthor().getName()+" "+t.getAuthor().getLastname());
        titleDto.setTitle(t.getTitle());
        List<BookResDto> bookDtoList = new ArrayList<>();
        t.getBook_list().stream().forEach(
                b -> {
                    BookResDto bookDto = BookMapper.convertEntityToResDto(b);
                    bookDtoList.add(bookDto);
                }
        );
        titleDto.setBook_list(bookDtoList);

        return titleDto;
    }

    public static Title convertEditDtoToEntity(Title t, TitleReqDto titleReqDto, Author author){
        if (titleReqDto.getTitle() != null) t.setTitle(titleReqDto.getTitle());
        if (titleReqDto.getAuthor_id()!= null) t.setAuthor(author);
        return t;
    }
}
