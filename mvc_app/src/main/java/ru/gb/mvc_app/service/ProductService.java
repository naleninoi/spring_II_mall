package ru.gb.mvc_app.service;

import ru.gb.mvc_app.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
}
