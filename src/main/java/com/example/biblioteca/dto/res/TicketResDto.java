package com.example.biblioteca.dto.res;

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
public class TicketResDto {
    private String user;
    private String book;
    @Future
    private LocalDate take_out_date = LocalDate.now();
    private boolean isReturned = false;

    @Override
    public String toString() {
        return "TicketResDto{" +
                "user='" + user + '\'' +
                ", book='" + book + '\'' +
                ", take_out_date=" + take_out_date +
                ", isReturned=" + isReturned +
                '}';
    }
}
