package ru.gb.mvc_app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long productId;
    private String title;
    private BigDecimal price;
    private List<CategoryDto> categories;

}
