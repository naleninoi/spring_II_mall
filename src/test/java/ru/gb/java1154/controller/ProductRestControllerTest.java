package ru.gb.java1154.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gb.java1154.dto.ProductDto;
import ru.gb.java1154.entity.Category;
import ru.gb.java1154.entity.Product;
import ru.gb.java1154.rest.ProductRestController;
import ru.gb.java1154.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class ProductRestControllerTest {

    ProductService productService = Mockito.mock(ProductService.class);
    ProductRestController productRestController;
    List<Product> mockProductList;
    Product mockProduct;

    @BeforeEach
    public void setUp() {
        productRestController = new ProductRestController(productService);
        Category testCategory = new Category(11L, "Test Category");
        Set<Category> categorySet = Set.of(testCategory);
        mockProductList = List.of(
          new Product(1L, "Test Product #1", new BigDecimal("100.11"), false, categorySet),
          new Product(2L, "Test Product #2", new BigDecimal("200.22"), false, categorySet),
          new Product(3L, "Test Product #3", new BigDecimal("300.33"), false, categorySet)
        );
        mockProduct = new Product(4L, "Test Product #4", new BigDecimal("400.44"), false, categorySet);
    }

    @Test
    void testGetAllProducts() {
        List<ProductDto> mockDtoList = mockProductList.stream().map(ProductDto::new).collect(Collectors.toList());
        Mockito.when(productService.findAllProducts()).thenReturn(mockProductList);
        ResponseEntity<List<ProductDto>> allProductsEntity = productRestController.getAllProducts();

        Assertions.assertEquals(HttpStatus.OK, allProductsEntity.getStatusCode());
        Assertions.assertEquals(allProductsEntity.getBody().size(), mockDtoList.size());
    }

    @Test
    void testGetProduct() {
        ProductDto mockProductDto = new ProductDto(mockProduct);
        Mockito.when(productService.findById(mockProduct.getId())).thenReturn(Optional.of(mockProduct));

        ResponseEntity<ProductDto> productEntity = productRestController.getProduct(mockProduct.getId());
        Assertions.assertEquals(HttpStatus.OK, productEntity.getStatusCode());
        Assertions.assertEquals(productEntity.getBody().getTitle(), mockProductDto.getTitle());
    }
}