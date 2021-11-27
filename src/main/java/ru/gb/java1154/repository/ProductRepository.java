package ru.gb.java1154.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gb.java1154.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        PagingAndSortingRepository<Product, Long> {

    Page<Product> findAllByIsDeletedIsFalse(Pageable pageable);

    List<Product> findByIsDeletedIsFalse();

    Optional<Product> findByIdAndIsDeletedIsFalse(Long id);

    Optional<Product> findByTitleAndIsDeletedIsFalse(String title);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceLessThan(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

}
