package com.example.biblioteca.utils;

import com.example.biblioteca.dto.res.AdminResDto;
import com.example.biblioteca.dto.res.UserResDto;
import com.example.biblioteca.entity.Admin;
import com.example.biblioteca.entity.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Utils {

    public static LocalDate convertDateToLocalDate (Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date convertLocalDateToDate(LocalDate localDate){
        return java.util.Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static List<UserResDto> loadUserDtoList(){
        List<UserResDto> listaEsperada = new ArrayList<>();
        listaEsperada.add(
                new UserResDto(
                        "Matias"
                        ,"Scavarelli"
                        ,2233456
                        ,"Av. Cabildo 123"
                        ,32
                        ,45678921
                        ,"jperez@mail.com"
                        ,"mati123"
                        ,3
                        , new HashSet<>()));
        listaEsperada.add(
                new UserResDto("Jose"
                        ,"Perez"
                        ,11745698
                        ,"Av. Cabildo 123"
                        ,17
                        ,1174136987
                        ,"jperez@mail.com"
                        ,"jose123"
                        ,2
                        ,new HashSet<>()));
        listaEsperada.add(
                new UserResDto("Susana"
                        ,"Gimenez"
                        ,5632147
                        ,"Cerruti 741"
                        ,61
                        ,114789625
                        ,"sgimenez@mail.com"
                        ,"susana123"
                        ,2
                        ,new HashSet<>()));

        return listaEsperada;
    }

    public static List<User> loadUserList(){
        List<User> userListReturn = new ArrayList<>();
//        Crear usuario 1
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Matias");
        user1.setLastname("Scavarelli");
        user1.setAge(32);
        user1.setDni(2233456);
        user1.setAddress("Calle falsa 123");
        user1.setPassword("mati123");
        user1.setPhone(45678921);
        user1.setEmail("mscava@mail.com");
        user1.setTicket_list(new HashSet<>());
        user1.setMaxBook(3);
//        Crear usuario 2
        User user2 = new User();
        user2.setId(2L);
        user2.setName("Jose");
        user2.setLastname("Perez");
        user2.setAge(17);
        user2.setDni(11745698);
        user2.setAddress("Av. Cabildo 123");
        user2.setPassword("jose123");
        user2.setPhone(1174136987);
        user2.setEmail("jperez@mail.com");
        user2.setTicket_list(new HashSet<>());
        user2.setMaxBook(2);
//        Crear usuario 3
        User user3 = new User();
        user3.setId(3L);
        user3.setName("Susana");
        user3.setLastname("Gimenez");
        user3.setAge(61);
        user3.setDni(5632147);
        user3.setAddress("Cerruti 741");
        user3.setPassword("susana123");
        user3.setPhone(114789625);
        user3.setEmail("sgimenez@mail.com");
        user3.setTicket_list(new HashSet<>());
        user3.setMaxBook(2);

        userListReturn.add(user1);
        userListReturn.add(user2);
        userListReturn.add(user3);

        return userListReturn;
    }

    public static List<AdminResDto> laodAdminDtoList(){
        List<AdminResDto> adminDtoList = new ArrayList<>();
        adminDtoList.add(new AdminResDto("Pedro","Picapiedra",123456,456123,"ppicapiedra@mail.com","qwerty"));
        adminDtoList.add(new AdminResDto("Pablo","Marmol",123457,456123,"pmarmol@mail.com","qwerty"));
        Collections.sort(adminDtoList, new Comparator<AdminResDto>() {
            @Override
            public int compare(AdminResDto u1, AdminResDto u2) {
                return (u1.getName()).compareTo(u2.getName());
            }
        });
        return adminDtoList;
    }
    public static List<Admin> laodAdminList(){
        List<Admin> adminList = new ArrayList<>();
        Admin admin1 = new Admin();
        admin1.setName("Pedro");
        admin1.setLastname("Picapiedra");
        admin1.setDni(123456);
        admin1.setPhone(456123);
        admin1.setEmail("ppicapiedra@mail.com");
        admin1.setPassword("qwerty");
        admin1.setId(1L);

        Admin admin2 = new Admin();
        admin2.setName("Pablo");
        admin2.setLastname("Marmol");
        admin2.setDni(123457);
        admin2.setPhone(456123);
        admin2.setEmail("pmarmol@mail.com");
        admin2.setPassword("qwerty");
        admin2.setId(2L);

        adminList.add(admin1);
        adminList.add(admin2);
        return adminList;
    }
}
