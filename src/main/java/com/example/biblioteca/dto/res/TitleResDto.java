package com.example.biblioteca.dto.res;

import com.example.biblioteca.dto.res.BookResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TitleResDto {
    private String author;
    private String title;
    private List<BookResDto> book_list = new ArrayList<>();

    @Override
    public String toString() {
        return "TitleResDto{" +
                "title='" + title + '\'' +
                ", bookList=" + book_list +
                ", authors=" + author +
                '}';
    }
}
