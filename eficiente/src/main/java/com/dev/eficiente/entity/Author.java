package com.dev.eficiente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "author")
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 400)
    private String description;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private LocalDateTime instant = LocalDateTime.now();
}
