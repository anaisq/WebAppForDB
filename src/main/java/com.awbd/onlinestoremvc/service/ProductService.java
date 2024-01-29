package com.awbd.onlinestoremvc.service;

import com.awbd.onlinestoremvc.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long l);
    Product save(Product product);
    void deleteById(Long id);
}
