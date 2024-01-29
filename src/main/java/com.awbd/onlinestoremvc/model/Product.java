package com.awbd.onlinestoremvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    @NotBlank(message = " not blank")
    private String productName;

    @Column(name = "price")
    @Positive(message = "must be positive")
    private Double price;

    @ManyToMany(mappedBy = "products",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Order> orders;

    public void removeOrder(Order order) {
        order.getProducts().remove(this);
        orders.remove(order);
    }
}
