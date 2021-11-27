package ru.gb.java1154.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.java1154.dto.ProductDto;
import ru.gb.java1154.entity.Product;
import ru.gb.java1154.service.ProductService;


import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        List<ProductDto> result = products.stream().map(ProductDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ProductDto result = new ProductDto( product.get());
            return ResponseEntity.ok( result );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            productService.delete(product.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto requestDto) {
        Optional <Product> optionalProduct = productService.findByTitle(requestDto.getTitle().toLowerCase());
        if (optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            Product product = new Product();
            product.setPrice(requestDto.getPrice());
            product.setTitle(requestDto.getTitle().toLowerCase());
            productService.save(product);
            ProductDto result = new ProductDto( product );
            return ResponseEntity.created( URI.create( "products/" + product.getId() ) ).body(result);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto requestDto) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Product product = optionalProduct.get();
            product.setPrice(requestDto.getPrice());
            product.setTitle(requestDto.getTitle().toLowerCase());
            productService.save(product);
            ProductDto result = new ProductDto( product );
            return ResponseEntity.ok(result);
        }
    }

}
