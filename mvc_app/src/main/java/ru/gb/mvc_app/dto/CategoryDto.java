package ru.gb.mvc_app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class CategoryDto implements Serializable {
    private String title;
}
