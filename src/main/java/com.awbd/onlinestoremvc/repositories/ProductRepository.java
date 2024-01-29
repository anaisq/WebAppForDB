package com.awbd.onlinestoremvc.repositories;

import com.awbd.onlinestoremvc.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Optional<Product> findById(Long l);

    Product save(Product product);

    void deleteById(Long id);
}
