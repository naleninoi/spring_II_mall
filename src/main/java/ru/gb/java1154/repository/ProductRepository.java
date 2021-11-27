package ru.gb.java1154.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.java1154.entity.Product;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByIsDeletedIsFalse();

    List<Product> findByCategories_TitleAndIsDeletedIsFalse(String categoryTitle);

}
