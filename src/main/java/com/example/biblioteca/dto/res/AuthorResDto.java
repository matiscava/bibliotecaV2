package com.example.biblioteca.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResDto {
    private String name;
    private String lastname;
    @NotNull
    private LocalDate born_date;
    @Nullable
    private LocalDate death_date;
    private String info;
    private Set<TitleShortDto> title_list = new HashSet<>();

    @Override
    public String toString() {
        return "AuthorResDto{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", born_date=" + born_date +
                ", death_date=" + death_date +
                ", info='" + info + '\'' +
                ", title_list=" + title_list +
                '}';
    }
}
