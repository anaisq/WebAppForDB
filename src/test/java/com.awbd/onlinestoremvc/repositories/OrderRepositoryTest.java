package com.awbd.onlinestoremvc.repositories;

import com.awbd.onlinestoremvc.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("mysql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@Slf4j
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findOrders() {
        List<Order> orders = orderRepository.findByCustomer(1L);
        assertTrue(orders.size() >= 1);
        log.info("findByCustomer ...");
        orders.forEach(order -> log.info(order.getDate().toString()));
    }

    @Test
    public void findOrdersByCustomerName() {
        List<Order> orders = orderRepository.findByCustomerName("Ana","Florea");
        assertTrue(orders.size() >= 1);
        log.info("findByCustomer ...");
        orders.forEach(order -> log.info(order.getDate().toString()));
    }



}
