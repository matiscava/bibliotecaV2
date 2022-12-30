package com.example.biblioteca.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketReqDto {
    private Long user_id;
    private Long book_id;
    @Future
    private LocalDate take_out_date = LocalDate.now();
    private boolean isReturned = false;
}
