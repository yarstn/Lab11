package com.example.user.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "id cannot be null or empty")
    @Column(columnDefinition = "int not null ")
    private int categoryId;

    @NotEmpty(message = "title cannot be null or empty")
    @Size(min = 5, message = "title must be longer than 4 characters")
    @Column(columnDefinition = "varchar(20) not null ")
    private String title;

    @NotEmpty(message = "content cannot be null or empty")
    @Size(min = 5, message = "content must be longer than 4 characters")
    @Column(columnDefinition = "varchar(150) not null ")
    private String content;

    @NotNull(message = "userId cannot be null or empty")
    @Column(columnDefinition = "int not null ")
    private int userId;

    @NotNull(message = "date cannot be null or empty")
    @Column(columnDefinition = "DATE not null unique")
    private Date publishDate;
}
