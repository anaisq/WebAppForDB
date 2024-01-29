package com.awbd.onlinestoremvc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true)
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
