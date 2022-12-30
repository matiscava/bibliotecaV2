package com.example.biblioteca.repository;

import com.example.biblioteca.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);
    List<User> findByLastname(String lastname);
    List<User> findByDni(int dni);
}
