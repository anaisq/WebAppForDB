package com.awbd.onlinestoremvc.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "shipping_address")
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String country;

    @OneToOne
    private Customer customer;

}
