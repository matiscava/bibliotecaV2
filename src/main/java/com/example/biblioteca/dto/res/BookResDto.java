package com.example.biblioteca.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResDto {
    private String author;
    private String title;
    private String editorial;
    private int edition;
    private int year;
    private boolean enabled = true;
    @Column(nullable = true)
    private Set<TicketResDto> ticket_list = new HashSet<>();

    @Override
    public String toString() {
        return "BookResDto{" +
                "edition=" + edition +
                ", year=" + year +
                ", editorial='" + editorial + '\'' +
                ", title=" + title +
                ", enabled=" + enabled +
                ", ticket_list=" + ticket_list +
                '}';
    }
}
