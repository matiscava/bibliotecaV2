package com.example.biblioteca.MockTest;

import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.dto.res.UserResDto;
import com.example.biblioteca.entity.User;
import com.example.biblioteca.mapper.UserMapper;
import com.example.biblioteca.repository.ITicketRepository;
import com.example.biblioteca.repository.IUserRepository;
import com.example.biblioteca.service.UserService;
import com.example.biblioteca.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTestConMock {
    @Mock
    private IUserRepository userRepository;
    @Mock
    private ITicketRepository ticketRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllUserTestOk(){
        //arrange
        List<User> userListReturn = Utils.loadUserList();
        List<UserResDto> listaEsperada = Utils.loadUserDtoList();
        //act
        when(userRepository.findAll()).thenReturn(userListReturn);
        List<UserResDto> listaObtenida = userService.getAll();
        //assert
        Assertions.assertEquals(listaEsperada.size(), listaObtenida.size());
        Assertions.assertEquals(listaEsperada.get(0).getDni(),listaObtenida.get(0).getDni());
    }
    @Test
    void createUserTestOk(){
        //arrange
        UserResDto userDto = new UserResDto("Mirtha","Legrand",912345687,"Av. de Mayo 456",101,756487,"mlegrand@mail.com","qwerty",5,new HashSet<>());
        User user = UserMapper.convertResDtoToEntity(userDto);
        User userExpected = UserMapper.convertResDtoToEntity(userDto);
        SuccessfullyMessageDto messageDto = new SuccessfullyMessageDto("El Usuario "+userDto.getName()+" se creó correctamente, con el Id: "+4);
        userExpected.setId(4L);
        //act
        when(userRepository.save(Mockito.any(User.class))).thenReturn(userExpected);
        SuccessfullyMessageDto messageObtain = userService.create(userDto);
        //assets
        Assertions.assertTrue(messageObtain.getMessage().equals(messageDto.getMessage()));
        Assertions.assertNotEquals(user.getId(),userExpected.getId());
        Assertions.assertEquals(user.getName(),userExpected.getName());
    }

    @Test
    void getUserByIdTestOk(){
        //arrange
        UserResDto userDto = new UserResDto("Jose","Perez",11745698,"Av. Cabildo 123",17,1174136987,"jperez@mail.com","jose123",2,new HashSet<>());
        User userExpected = UserMapper.convertResDtoToEntity(userDto);
        userExpected.setId(2L);
        //act
        when(userRepository.findById(2L)).thenReturn(Optional.of(userExpected));
        UserResDto userDtoObtain = userService.getById(2L);
        //assets
        Assertions.assertNotNull(userExpected);
        Assertions.assertNotNull(userDtoObtain);
        Assertions.assertEquals(userDto.getAge(), userDtoObtain.getAge());
        Assertions.assertEquals(userExpected.getLastname(), userDtoObtain.getLastname());
    }

    @Test
    void getUserByNameTestOk(){
        UserResDto userDto = new UserResDto("Jose","Perez",11745698,"Av. Cabildo 123",17,1174136987,"jperez@mail.com","jose123",2,new HashSet<>());
        User userExpected = UserMapper.convertResDtoToEntity(userDto);
        List<User> listUserExpected = new ArrayList<>();
        listUserExpected.add(userExpected);

        when(userRepository.findByName(userDto.getName())).thenReturn(listUserExpected);
        List<UserResDto> listUserDtoObtain = userService.getByName("Jose");

        Assertions.assertNotNull(userExpected);
        Assertions.assertNotNull(listUserDtoObtain);
        Assertions.assertEquals(1,listUserDtoObtain.size());
        Assertions.assertEquals(userDto.getAge(),listUserDtoObtain.get(0).getAge());
        Assertions.assertEquals(userExpected.getLastname(),listUserDtoObtain.get(0).getLastname());
    }

    @Test
    void getUserByLastameTestOk(){
        UserResDto userDto = new UserResDto("Jose","Perez",11745698,"Av. Cabildo 123",17,1174136987,"jperez@mail.com","jose123",2,new HashSet<>());
        User userExpected = UserMapper.convertResDtoToEntity(userDto);
        List<User> listUserExpected = new ArrayList<>();
        listUserExpected.add(userExpected);

        when(userRepository.findByLastname(userDto.getLastname())).thenReturn(listUserExpected);
        List<UserResDto> listUserDtoObtain = userService.getByLastname("Perez");

        Assertions.assertNotNull(userExpected);
        Assertions.assertNotNull(listUserDtoObtain);
        Assertions.assertEquals(1,listUserDtoObtain.size());
        Assertions.assertEquals(userDto.getAge(),listUserDtoObtain.get(0).getAge());
        Assertions.assertEquals(userExpected.getLastname(),listUserDtoObtain.get(0).getLastname());

    }

    @Test
    void getUserByDniTestOk(){
        UserResDto userDto = new UserResDto("Jose","Perez",11745698,"Av. Cabildo 123",17,1174136987,"jperez@mail.com","jose123",2,new HashSet<>());
        User userExpected = UserMapper.convertResDtoToEntity(userDto);
        List<User> listUserExpected = new ArrayList<>();
        listUserExpected.add(userExpected);

        when(userRepository.findByDni(userDto.getDni())).thenReturn(listUserExpected);
        List<UserResDto> listUserDtoObtain = userService.getByDni(11745698);

        Assertions.assertNotNull(userExpected);
        Assertions.assertNotNull(listUserDtoObtain);
        Assertions.assertEquals(1,listUserDtoObtain.size());
        Assertions.assertEquals(userDto.getAge(),listUserDtoObtain.get(0).getAge());
        Assertions.assertEquals(userExpected.getLastname(),listUserDtoObtain.get(0).getLastname());
    }

}
