package ru.gb.java1154.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.java1154.entity.Product;
import ru.gb.java1154.render.ProductR;
import ru.gb.java1154.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String showProducts(Model model) {
        List<Product> products = productService.findAllProducts();
        List<ProductR> result = products.stream().map(ProductR::new).collect(Collectors.toList());
        model.addAttribute("products", result);
        return "products";
    }
}
