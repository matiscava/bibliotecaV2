package com.example.biblioteca.service;

import com.example.biblioteca.dto.res.TicketResDto;
import com.example.biblioteca.dto.res.UserResDto;

import java.util.List;

public interface IUserService extends IBaseService<UserResDto,UserResDto>{
    List<TicketResDto> getTickets(Long id);
    List<UserResDto> getByName(String name);
    List<UserResDto> getByLastname(String lastname);
    List<UserResDto> getByDni(int dni);

}
