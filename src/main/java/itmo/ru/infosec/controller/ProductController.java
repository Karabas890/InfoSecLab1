package itmo.ru.infosec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import itmo.ru.infosec.entity.Product;
import itmo.ru.infosec.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/data")
    public List<Product> getData() {
        return productService.getAllProducts();
    }
}