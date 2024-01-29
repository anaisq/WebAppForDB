package com.awbd.onlinestoremvc.service;

import com.awbd.onlinestoremvc.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    public Order findById(Long l);
}
