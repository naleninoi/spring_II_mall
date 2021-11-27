package ru.gb.java1154.soap.web.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.java1154.entity.Product;
import ru.gb.java1154.service.ProductService;
import ru.gb.java1154.soap.web.products.GetProductsRequest;
import ru.gb.java1154.soap.web.products.GetProductsResponse;


import java.util.List;

@Endpoint
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://gb.ru/java1154/soap/web/products";

    private final ProductService productService;

    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {
        List<Product> productList = productService.findAllProducts();
        GetProductsResponse response = new GetProductsResponse();
        productList.forEach(product -> {
            ru.gb.java1154.soap.web.products.Product wsProduct = new ru.gb.java1154.soap.web.products.Product();
            wsProduct.setId(product.getId());
            wsProduct.setTitle(product.getTitle());
            wsProduct.setPrice(product.getPrice().floatValue());
            product.getCategories().forEach(category -> {
                ru.gb.java1154.soap.web.products.Category wsCategory = new ru.gb.java1154.soap.web.products.Category();
                wsCategory.setId(category.getId());
                wsCategory.setTitle(category.getTitle());
                wsProduct.getCategories().add(wsCategory);
            });
            response.getProducts().add(wsProduct);
        });

        return response;
    }

}
