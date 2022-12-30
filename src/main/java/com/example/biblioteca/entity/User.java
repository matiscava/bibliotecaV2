package com.example.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname;
//    @Column(nullable = false, unique = true)
    @Column(unique = true,nullable = false)
    private int dni;
    private String address;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private int phone;
    @Column( unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int maxBook;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<Ticket> ticket_list;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dni=" + dni +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", maxBook=" + maxBook +
                ", ticket_list=" + ticket_list.size() +
                '}';
    }
}
