package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.req.BookReqDto;
import com.example.biblioteca.dto.res.BookResDto;
import com.example.biblioteca.dto.res.TicketResDto;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.entity.Title;

import java.util.HashSet;
import java.util.Set;

import static com.example.biblioteca.utils.Utils.convertDateToLocalDate;

public class BookMapper {
    public static Book convertReqDtoToEntity(BookReqDto bookDto, Title t){
        Book b = new Book();
        b.setId(0L);
        b.setTitle(t);
        b.setTicket_list(new HashSet<>());
        b.setYear(bookDto.getYear());
        b.setEdition(bookDto.getEdition());
        b.setEditorial(bookDto.getEditorial());
        b.setEnabled(bookDto.isEnabled());

        return b;
    }

    public static BookResDto convertEntityToResDto(Book b){
        BookResDto bookDto = new BookResDto();
        bookDto.setAuthor(b.getTitle().getAuthor().getName()+" "+b.getTitle().getAuthor().getLastname());
        bookDto.setTitle(b.getTitle().getTitle());
        bookDto.setEditorial(b.getEditorial());
        bookDto.setEdition(b.getEdition());
        bookDto.setYear(b.getYear());
        bookDto.setEnabled(b.isEnabled());
        Set<TicketResDto> ticketResDtoSet = new HashSet<>();
        b.getTicket_list().stream().forEach(
                t -> {
                    TicketResDto tDto= new TicketResDto();
                    tDto.setUser(t.getUser().getName()+" "+t.getUser().getLastname());
                    tDto.setBook(bookDto.getTitle());
                    tDto.setTake_out_date(convertDateToLocalDate(t.getTake_out_date()));
                    tDto.setReturned(t.isReturned());
                    ticketResDtoSet.add(tDto);
                }
        );
        bookDto.setTicket_list(ticketResDtoSet);
        return bookDto;
    }

    public static Book convertEditDtoToEntity(Book book, BookReqDto bookDto, Title title ){
        if (bookDto.getEdition()>0) book.setEdition(bookDto.getEdition());
        if (bookDto.isEnabled()){
            book.setEnabled(true);
        }else {
            book.setEnabled(false);
        }
        if (bookDto.getEditorial()!=null) book.setEditorial(bookDto.getEditorial());
        if (bookDto.getYear()>0) book.setYear(bookDto.getYear());
        if (bookDto.getTitle_id() != null) book.setTitle(title);

        return book;
    }
}
