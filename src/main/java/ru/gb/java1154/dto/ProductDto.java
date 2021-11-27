package ru.gb.java1154.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.gb.java1154.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@ToString
public class ProductDto {

    Long productId;
    String title;
    BigDecimal price;
    List<CategoryDto> categories = new ArrayList<>();

    public ProductDto (Product product) {
        this.productId = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categories = product.getCategories().stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }

}
