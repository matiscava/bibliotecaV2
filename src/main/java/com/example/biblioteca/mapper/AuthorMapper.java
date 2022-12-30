package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.res.AuthorResDto;
import com.example.biblioteca.dto.res.TitleShortDto;
import com.example.biblioteca.entity.Author;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.example.biblioteca.utils.Utils.convertDateToLocalDate;
import static com.example.biblioteca.utils.Utils.convertLocalDateToDate;
import static java.lang.Integer.parseInt;

public class AuthorMapper {

    public static Author convertResDtoToEntity(AuthorResDto authorDto){
        Author a = new Author();
        a.setName(authorDto.getName());
        a.setLastname(authorDto.getLastname());
        a.setInfo(authorDto.getInfo());
        a.setBorn_date(convertLocalDateToDate(authorDto.getBorn_date()));
        if (authorDto.getDeath_date()!=null) a.setDeath_date(convertLocalDateToDate(authorDto.getDeath_date()));
        a.setTitle_list(new HashSet<>());
        a.setId(0L);
        return a;
    }

    public static AuthorResDto convertEntityToResDto(Author a){
        AuthorResDto authorDto = new AuthorResDto();
        authorDto.setName(a.getName());
        authorDto.setLastname(a.getLastname());
        authorDto.setBorn_date(convertDateToLocalDate(a.getBorn_date()));
        if (a.getDeath_date() != null){
            authorDto.setDeath_date(convertDateToLocalDate(a.getDeath_date()));
        }else {
            authorDto.setDeath_date(null);
        }
        Set<TitleShortDto> title_list = new HashSet<>();
        a.getTitle_list().stream().forEach(
                title -> {
                    AtomicInteger quantity = new AtomicInteger();
                    TitleShortDto titleDto = new TitleShortDto();
                    titleDto.setTitle(title.getTitle());
                    title.getBook_list().stream().forEach(
                            b-> {
                                if(b.isEnabled()) quantity.getAndIncrement();
                            }
                    );
                    titleDto.setBook_stock(parseInt(quantity.toString()));
                    title_list.add(titleDto);
                }
        );
        title_list.stream().sorted(new Comparator<TitleShortDto>() {
            @Override
            public int compare(TitleShortDto t1, TitleShortDto t2) {
                return (t1.getTitle()).compareTo(t2.getTitle());
            }
        }).collect(Collectors.toList());
        authorDto.setTitle_list(title_list);
        authorDto.setInfo(a.getInfo());
        return authorDto;
    }

    public static Author convertEditDtoToEntity(Author a,AuthorResDto authorDto){
        if(authorDto.getName()!=null) a.setName(authorDto.getName());
        if(authorDto.getLastname()!=null) a.setLastname(authorDto.getLastname());
        if(authorDto.getInfo()!=null) a.setInfo(authorDto.getInfo());
        if(authorDto.getBorn_date()!=null) a.setBorn_date(convertLocalDateToDate(authorDto.getBorn_date()));
        if(authorDto.getDeath_date()!=null) a.setDeath_date(convertLocalDateToDate(authorDto.getDeath_date()));

        return a;
    }

}
