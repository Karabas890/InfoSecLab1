package itmo.ru.infosec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import itmo.ru.infosec.entity.ProductModel;
import itmo.ru.infosec.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/data")
    public List<ProductModel> getData() {
        return productService.getAllProducts();
    }
}