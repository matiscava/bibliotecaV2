package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.req.BookReqDto;
import com.example.biblioteca.dto.req.TicketReqDto;
import com.example.biblioteca.dto.res.TicketResDto;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.entity.Ticket;
import com.example.biblioteca.entity.Title;
import com.example.biblioteca.entity.User;

import static com.example.biblioteca.utils.Utils.convertDateToLocalDate;
import static com.example.biblioteca.utils.Utils.convertLocalDateToDate;

public class TicketMapper {
    public static Ticket convertReqDtoToEntity(TicketReqDto ticketDto, User u, Book b){
        Ticket ticket = new Ticket();
        ticket.setUser(u);
        ticket.setBook(b);
        ticket.setTake_out_date(convertLocalDateToDate(ticketDto.getTake_out_date()));
        ticket.setReturned(ticketDto.isReturned());
        ticket.setId(0L);
        return ticket;
    }
    public static TicketResDto convertEntityToResDto(Ticket t){
        TicketResDto ticketDto = new TicketResDto();
        ticketDto.setTake_out_date(convertDateToLocalDate(t.getTake_out_date()));
        ticketDto.setUser(t.getUser().getName()+" "+t.getUser().getLastname());
        ticketDto.setBook(t.getBook().getTitle().getTitle());
        ticketDto.setReturned(t.isReturned());
        return ticketDto;
    }
    
    public static Ticket convertEditDtoToEntity(Ticket ticket, TicketReqDto ticketDto, Book b, User u ){
        if (ticketDto.getUser_id()!= null) ticket.setUser(u);
        if (ticketDto.getBook_id()!= null) ticket.setBook(b);
        if (ticketDto.isReturned()){
            ticket.setReturned(true);
        }else {
            ticket.setReturned(false);
        }
        if (ticketDto.getTake_out_date() != null) ticket.setTake_out_date(convertLocalDateToDate(ticketDto.getTake_out_date()));

        return ticket;
    }
    
}
