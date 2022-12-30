package com.example.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date born_date;
//    private LocalDate born_date;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = true)
//    private LocalDate death_date;
    private Date death_date;
    private String info;
    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<Title> title_list;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", born_date=" + born_date +
                ", death_date=" + death_date +
                ", info='" + info + '\'' +
//                ", title_list=" + title_list +
                '}';
    }
}
