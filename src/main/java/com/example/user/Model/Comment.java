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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "userId cannot be null or empty")
    @Column(columnDefinition = "int not null ")
    private int userId;


    @NotNull(message = "postId cannot be null or empty")
    @Column(columnDefinition = "int not null ")
    private int postId;

    @NotEmpty(message = "content cannot be null or empty")
    @Size(min = 5, message = "content must be longer than 4 characters")
    @Column(columnDefinition = "varchar(150) not null ")
    private String content;


    @NotNull(message = "date cannot be null or empty")
    @Column(columnDefinition = "DATE not null ")
    private Date commentDate;
}
