package ru.gb.java1154.service;

import ru.gb.java1154.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    List<Product> findByCategory(String categoryTitle);
}
