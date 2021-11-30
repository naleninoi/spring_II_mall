package ru.gb.java1154.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.gb.java1154.entity.Category;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    String title;

    public CategoryDto(Category category) {
        this.title = category.getTitle();
    }

}
