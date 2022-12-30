package com.example.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int edition;
    private int year;
    private String editorial;
//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "title_id",nullable = false)
    private Title title;
    private boolean enabled;
    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    private Set<Ticket> ticket_list;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", edition=" + edition +
                ", year=" + year +
                ", editorial='" + editorial + '\'' +
                ", title=" + title.getTitle()    +
                ", enabled=" + enabled +
                ", ticket_list=" + ticket_list.size() +
                '}';
    }
}
