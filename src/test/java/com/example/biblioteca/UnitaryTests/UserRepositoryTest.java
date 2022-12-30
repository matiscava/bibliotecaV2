package com.example.biblioteca.UnitaryTests;

import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.dto.res.UserResDto;
import com.example.biblioteca.entity.User;
import com.example.biblioteca.repository.IUserRepository;
import com.example.biblioteca.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
//@TestPropertySource(locations="classpath:application-test.properties")
public class UserRepositoryTest {
//    @Autowired
//    private IUserRepository userRepository;
//    @Autowired
//    private UserService userService;
//
//    private Long fakeId;
//
//    @Test
//    void createUserTestOk(){
//        UserResDto userResDto = new UserResDto();
//        userResDto.setName("Jorge");
//        userResDto.setLastname("Corona");
//        userResDto.setDni(4126978);
//        userResDto.setAddress("Calle 234");
//        userResDto.setAge(32);
//        userResDto.setPhone(114897652);
//        userResDto.setEmail("jorgecorona@mail.com");
//        userResDto.setPassword("jorge123");
//
//        User user = new User();
//        user.setName(userResDto.getName());
//        user.setLastname(userResDto.getLastname());
//        user.setDni(userResDto.getDni());
//        user.setAddress(userResDto.getAddress());
//        user.setAge(userResDto.getAge());
//        user.setPhone(userResDto.getPhone());
//        user.setEmail(userResDto.getEmail());
//        user.setPassword(userResDto.getPassword());
//        user.setId(0L);
//
//
//        User userTest = userRepository.save(user);
//        fakeId = userTest.getId();
//        Assert.assertTrue(userTest.getName() == user.getName());
//        Assert.assertTrue( userTest.getAge() == 32 );
//
////        Cambiamos los tados unicos para que no salte error
//        userResDto.setDni(456789123);
//        userResDto.setEmail("maase@asdas.com");
//
//        SuccessfullyMessageDto realMessage = userService.create(userResDto);
//        SuccessfullyMessageDto expectedMessage = new SuccessfullyMessageDto("El Usuario se creó correctamente");
//
//        Assert.assertTrue( expectedMessage.getMessage() == realMessage.getMessage());
//    }
//
//    @Test
//    void findByIdTestOk(){
//        User user = userRepository.findById(1L).get();
//
//        Assert.assertTrue(user!=null);
//        Assert.assertTrue(user.getName().equals("Homero"));
//
//        UserResDto userResDto = userService.getById(1L);
//        Assert.assertTrue(userResDto.getLastname().equals("Simpson"));
//    }
//
//    @Test
//    void findAllTest(){
//        List<User> userList = userRepository.findAll();
//
//        Assert.assertFalse(userList.isEmpty());
//        Assert.assertTrue(userList.size()==3);
//    }
//
//    @Test
//    void deleteUserById(){
//        SuccessfullyMessageDto realMessage = userService.deleteById(4L);
//        SuccessfullyMessageDto expectedMessage = new SuccessfullyMessageDto("El usuario id: "+4+" se eliminó correctamente");
//        Assert.assertEquals(expectedMessage.getMessage(),realMessage.getMessage());
//    }

}
