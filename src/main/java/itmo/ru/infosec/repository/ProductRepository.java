package itmo.ru.infosec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itmo.ru.infosec.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}