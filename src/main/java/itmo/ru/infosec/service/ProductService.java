package itmo.ru.infosec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import itmo.ru.infosec.entity.ProductModel;
import itmo.ru.infosec.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService( ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll().stream().peek(
                it -> it.setData(HtmlUtils.htmlEscape(it.getData()))
        ).toList();
    }
}