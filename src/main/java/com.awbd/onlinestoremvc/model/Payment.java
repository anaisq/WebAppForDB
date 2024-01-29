package com.awbd.onlinestoremvc.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private java.util.Date date;

    @OneToOne
    private Order order;
}
