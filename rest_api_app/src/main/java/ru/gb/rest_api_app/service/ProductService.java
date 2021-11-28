package ru.gb.rest_api_app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gb.rest_api_app.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);

    Optional<Product> findByTitle(String title);

    List<Product> findAllProducts();

    Page<Product> getAllProductsByPages(Pageable pageable);

    List<Product> findProductsByMaxPrice(BigDecimal maxPrice);

    List<Product> findProductsByMinPrice(BigDecimal minPrice);

    List<Product> findProductsByPriceInterval(BigDecimal minPrice, BigDecimal maxPrice);

    void save (Product product);

    void delete(Product product);
}
