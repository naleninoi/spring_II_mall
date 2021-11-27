package ru.gb.java1154.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.gb.java1154.entity.Category;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
public class CategoryDto {

    String title;

    public CategoryDto(Category category) {
        this.title = category.getTitle();
    }

}
