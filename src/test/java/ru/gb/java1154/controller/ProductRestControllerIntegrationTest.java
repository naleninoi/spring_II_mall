package ru.gb.java1154.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gb.java1154.dto.ProductDto;
import ru.gb.java1154.entity.Category;
import ru.gb.java1154.entity.Product;
import ru.gb.java1154.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductService productService;

    List<Product> mockProductList;
    Product mockProduct;

    @BeforeEach
    public void setUp() {
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
    public void shouldReturnProductDtoList() throws Exception {
        List<ProductDto> mockDtoList = mockProductList.stream().map(ProductDto::new).collect(Collectors.toList());
        BDDMockito.given(productService.findAllProducts()).willReturn(mockProductList);

        ResponseEntity<List> responseEntity = restTemplate.getForEntity( "http://localhost:" + port + "/api/products", List.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotEquals(responseEntity.getBody(), null);
        Assertions.assertEquals(responseEntity.getBody().size(), mockDtoList.size());
    }

    @Test
    public void shouldReturnProductDtoByProductId() throws Exception {
        ProductDto mockProductDto = new ProductDto(mockProduct);
        BDDMockito.given(productService.findById(mockProduct.getId())).willReturn(Optional.ofNullable(mockProduct));

        ResponseEntity<ProductDto> responseEntity = restTemplate.getForEntity( "http://localhost:" + port + "/api/products/" + mockProduct.getId(), ProductDto.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotEquals(responseEntity.getBody(), null);
        Assertions.assertEquals(responseEntity.getBody().getTitle(), mockProductDto.getTitle());
    }

    @Test
    public void shouldCreateNewProduct_AfterHttpPostRequest() throws Exception {
        ProductDto mockProductDto = new ProductDto(mockProduct);
        ResponseEntity<ProductDto> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/products",
                mockProductDto, ProductDto.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }
}
