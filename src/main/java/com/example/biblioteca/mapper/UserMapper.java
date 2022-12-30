package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.res.TicketResDto;
import com.example.biblioteca.dto.res.UserResDto;
import com.example.biblioteca.entity.User;

import java.util.HashSet;
import java.util.Set;

import static com.example.biblioteca.utils.Utils.convertDateToLocalDate;

public class UserMapper {
    public static User convertResDtoToEntity(UserResDto userDto){
        User u = new User();
        u.setAddress(userDto.getAddress());
        u.setAge(userDto.getAge());
        u.setDni(userDto.getDni());
        u.setEmail(userDto.getEmail());
        u.setLastname(userDto.getLastname());
        u.setMaxBook(userDto.getMaxBook());
        u.setName(userDto.getName());
        u.setPassword(userDto.getPassword());
        u.setPhone(userDto.getPhone());
        u.setId(0L);
        return u;
    }
    public static UserResDto converEntityToResDto(User u){
        UserResDto userDto = new UserResDto();
        userDto.setName(u.getName());
        userDto.setLastname(u.getLastname());
        userDto.setDni(u.getDni());
        userDto.setAge(u.getAge());
        userDto.setAddress(u.getAddress());
        userDto.setEmail(u.getEmail());
//        userDto.setPassword(u.getPassword()); // No hace falta compartir la contraseña
        userDto.setPhone(u.getPhone());
        userDto.setMaxBook(u.getMaxBook());
        Set<TicketResDto> ticketResDtoSet = new HashSet<>();
        if (u.getTicket_list() != null){
            u.getTicket_list().stream().forEach(
                    t -> {
                        TicketResDto tDto= new TicketResDto();
                        tDto.setUser(u.getName()+" "+u.getLastname());
                        tDto.setBook(t.getBook().getTitle().getTitle());
                        tDto.setTake_out_date(convertDateToLocalDate(t.getTake_out_date()));
                        tDto.setReturned(t.isReturned());
                        ticketResDtoSet.add(tDto);
                    }
            );
        }
        userDto.setTicket_list(ticketResDtoSet);
        return userDto;
    }
    public static User convertEditDtoToEntity(User u, UserResDto userResDto){
        if (userResDto.getAge() != 0) u.setAge(userResDto.getAge());
        if (userResDto.getLastname() != null) u.setLastname(userResDto.getLastname());
        if (userResDto.getName()!=null) u.setName(userResDto.getName());
        if (userResDto.getEmail() != null) u.setEmail(userResDto.getEmail());
        if (userResDto.getAddress()!= null) u.setAddress(userResDto.getAddress());
        if (userResDto.getDni() != 0) u.setDni(userResDto.getDni());
        if (userResDto.getPhone() !=0) u.setPhone(userResDto.getPhone());
        if (userResDto.getMaxBook() !=0) u.setMaxBook(userResDto.getMaxBook());
        if (userResDto.getPassword() != null ) u.setPassword(userResDto.getPassword());

        return u;
    }
}
