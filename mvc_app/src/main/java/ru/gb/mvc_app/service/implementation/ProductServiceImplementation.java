package ru.gb.mvc_app.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.mvc_app.dto.ProductDto;
import ru.gb.mvc_app.service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    private final String API_ENDPOINT = "http://localhost:8081/api/products";

    private ObjectMapper mapper = new ObjectMapper();

    private final RestTemplate restTemplate;

    public ProductServiceImplementation(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<ProductDto> getAllProducts() {
        ResponseEntity<String> response = restTemplate.getForEntity(API_ENDPOINT, String.class);
        if ( !response.getStatusCode().equals(HttpStatus.OK) ) {
            return null;
        }
        List<ProductDto> products;
        try {
            products = mapper.readValue(response.getBody(), new TypeReference<>() {});
            return products;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
