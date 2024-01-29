package com.awbd.onlinestoremvc.service;

import com.awbd.onlinestoremvc.exceptions.ResourceNotFoundException;
import com.awbd.onlinestoremvc.model.Order;
import com.awbd.onlinestoremvc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awbd.onlinestoremvc.repositories.OrderRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new LinkedList<>();
        orderRepository.findAll().iterator().forEachRemaining(orders
                ::add);
        return orders ;
    }

    @Override
    public Order findById(Long l) {
        Optional<Order> orderOptional = orderRepository.findById(l);
        if (!orderOptional.isPresent()) {
            //throw new RuntimeException("Product not found!");
            throw new ResourceNotFoundException("product " + l + " not found");
        }
        return orderOptional.get();
    }
}
