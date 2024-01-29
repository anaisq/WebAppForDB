package com.awbd.onlinestoremvc.service;

import com.awbd.onlinestoremvc.exceptions.ResourceNotFoundException;
import com.awbd.onlinestoremvc.model.Order;
import com.awbd.onlinestoremvc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.awbd.onlinestoremvc.repositories.ProductRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll(){
        List<Product> products = new LinkedList<>();
        productRepository.findAll(Sort.by("productName")).iterator().forEachRemaining(products::add);
        return products;
    }

    @Override
    public Product findById(Long l) {
        Optional<Product> productOptional = productRepository.findById(l);
        if (!productOptional.isPresent()) {
            //throw new RuntimeException("Product not found!");
            throw new ResourceNotFoundException("product " + l + " not found");
        }
        return productOptional.get();
    }

    @Override
    public Product save(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public void deleteById(Long id) {

        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            //throw new RuntimeException("Product not found!");
            throw new ResourceNotFoundException("product " + id + " not found");
        }
        Product product = productOptional.get();
        List<Order> orders = new LinkedList<Order>();
        product.getOrders().iterator().forEachRemaining(orders::add);
        orders.iterator().forEachRemaining(product::removeOrder);

        productRepository.save(product);
        productRepository.deleteById(id);

    }
}
