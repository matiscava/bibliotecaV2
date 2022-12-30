package com.example.biblioteca.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminResDto {
    private String name;
    private String lastname;
    @NotNull
    private int dni;
    @Nullable
    private int phone;
    @NotNull
    private String email;
    private String password;

    @Override
    public String toString() {
        return "AdminResDto{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dni=" + dni +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
