package com.example.biblioteca.dto.res;

import com.example.biblioteca.dto.res.TicketResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserResDto {
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    private int dni;
    @Nullable
    private String address;
    @Min(value = 16,message = "La edad no puede ser menor a 16")
    @Max(value = 110, message = "La edad no peude ser mayor a 110")
    private int age;
    @Nullable
    private int phone;
    private String email;
    private String password;
    private int maxBook=2;
    private Set<TicketResDto> ticket_list = new HashSet<>();

    @Override
    public String toString() {
        return "UserResDto{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dni=" + dni +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", maxBook=" + maxBook +
                ", ticket_list=" + ticket_list +
                '}';
    }
}
