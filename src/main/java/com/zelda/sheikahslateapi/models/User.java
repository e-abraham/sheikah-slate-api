package com.zelda.sheikahslateapi.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

//Generates getters, setters, constructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
