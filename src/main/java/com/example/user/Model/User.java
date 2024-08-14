package com.example.user.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username cannot be null or empty")
    @Size(min = 5, message = "Username must be longer than 4 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;

    @Email
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @NotNull(message = "Registration date must not be null")
    @Column(columnDefinition = "DATE not null")
    private Date registrationDate;
}
