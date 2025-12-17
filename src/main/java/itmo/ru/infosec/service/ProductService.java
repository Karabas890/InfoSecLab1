package itmo.ru.infosec.service;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import itmo.ru.infosec.entity.Product;
import itmo.ru.infosec.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService( ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().peek(
                it -> it.setData(HtmlUtils.htmlEscape(it.getData()))
        ).toList();
    }
}