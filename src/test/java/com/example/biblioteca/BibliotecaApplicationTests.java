package com.example.biblioteca;

import com.example.biblioteca.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@TestPropertySource(locations="classpath:application-test.properties")
class BibliotecaApplicationTests {

    @Autowired
    IUserRepository userRepository;
}
