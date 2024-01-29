package com.awbd.onlinestoremvc.repositories;

import com.awbd.onlinestoremvc.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from orders o where o.customer.id = ?1")
    List<Order> findByCustomer(Long customerId);

    @Query("select o from orders o where o.customer.firstName = :firstName and o.customer.lastName = :lastName")
    List<Order> findByCustomerName(@Param("firstName") String customerFirstName, @Param("lastName") String customerLastName);

}
