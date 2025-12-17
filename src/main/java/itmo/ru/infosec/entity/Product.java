package itmo.ru.infosec.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    String data;

    public Product() {
    }
}