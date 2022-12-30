package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITitleRepository extends JpaRepository<Title,Long> {
    List<Title> findByTitle(String title);
}
