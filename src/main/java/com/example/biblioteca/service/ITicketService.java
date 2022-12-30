package com.example.biblioteca.service;

import com.example.biblioteca.dto.req.TicketReqDto;
import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.dto.res.TicketResDto;

public interface ITicketService extends IBaseService<TicketResDto, TicketReqDto> {

    SuccessfullyMessageDto returnById(Long id);
}
