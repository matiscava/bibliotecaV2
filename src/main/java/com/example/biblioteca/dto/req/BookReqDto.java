package com.example.biblioteca.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReqDto {
    private int edition;
    private String editorial;
    private boolean enabled = true;
    private int year;
    private Long title_id;

    @Override
    public String toString() {
        return "BookReqDto{" +
                "edition=" + edition +
                ", editorial='" + editorial + '\'' +
                ", enabled=" + enabled +
                ", year=" + year +
                ", title_id=" + title_id +
                '}';
    }
}
