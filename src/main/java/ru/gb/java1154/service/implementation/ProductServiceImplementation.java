package ru.gb.java1154.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gb.java1154.entity.Product;
import ru.gb.java1154.repository.ProductRepository;
import ru.gb.java1154.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findByIdAndIsDeletedIsFalse(id);
    }

    @Override
    public Optional<Product> findByTitle(String title) {
        return productRepository.findByTitleAndIsDeletedIsFalse(title);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findByIsDeletedIsFalse();
    }

    @Override
    public Page<Product> getAllProductsByPages(Pageable pageable) {
        return productRepository.findAllByIsDeletedIsFalse(pageable);
    }

    @Override
    public List<Product> findProductsByMaxPrice(BigDecimal maxPrice) {
        return productRepository.findByPriceLessThan(maxPrice);
    }

    @Override
    public List<Product> findProductsByMinPrice(BigDecimal minPrice) {
        return productRepository.findByPriceGreaterThan(minPrice);
    }

    @Override
    public List<Product> findProductsByPriceInterval(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        product.setDeleted(true);
        productRepository.save(product);
    }
}
