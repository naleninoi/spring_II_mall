package ru.gb.mvc_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.mvc_app.dto.ProductDto;
import ru.gb.mvc_app.service.ProductService;

import java.util.List;

@Controller
public class WelcomeController {

    private final ProductService productService;

    public WelcomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String getWelcomePage() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<ProductDto> productDtoList = productService.getAllProducts();
        model.addAttribute("products", productDtoList);
        return "products";
    }

}
