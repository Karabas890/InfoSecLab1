package itmo.ru.infosec.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_table")
public class ProductModel {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    String data;

    public ProductModel() {
    }
}