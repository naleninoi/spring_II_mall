package ru.gb.rest_api_app.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

}
