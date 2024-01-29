package com.awbd.onlinestoremvc.repositories;

import com.awbd.onlinestoremvc.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    List<Customer> findByLastNameLike(String lastName);
}
