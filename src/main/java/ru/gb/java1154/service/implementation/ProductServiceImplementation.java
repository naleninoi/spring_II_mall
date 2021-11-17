package ru.gb.java1154.service.implementation;

import org.springframework.stereotype.Service;
import ru.gb.java1154.entity.Product;
import ru.gb.java1154.repository.ProductRepository;
import ru.gb.java1154.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAllByIsDeletedIsFalse();
    }

    @Override
    public List<Product> findByCategory(String categoryTitle) {
        return productRepository.findByCategories_TitleAndIsDeletedIsFalse(categoryTitle);
    }
}
