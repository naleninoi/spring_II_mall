package ru.gb.java1154.render;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.gb.java1154.entity.Category;
import ru.gb.java1154.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
public class ProductR {

    Long id;
    String title;
    BigDecimal price;
    String categories;
    boolean deleted;

    public ProductR(Product product) {
        this.id = product.getId();
        this.title = product.getTitle().substring(0, 1).toUpperCase() + product.getTitle().substring(1);
        this.price = product.getPrice();
        this.deleted = product.isDeleted();
        this.categories = product.getCategories().stream()
                .map(Category::getTitle)
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));
    }
}
