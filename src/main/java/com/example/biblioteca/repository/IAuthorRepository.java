package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorRepository extends JpaRepository<Author,Long> {
    List<Author> findByName(String name);
    List<Author> findByLastname(String lastname);
}
