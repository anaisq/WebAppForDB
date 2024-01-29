package com.awbd.onlinestoremvc.service;

import com.awbd.onlinestoremvc.exceptions.ResourceNotFoundException;
import com.awbd.onlinestoremvc.model.Product;
import com.awbd.onlinestoremvc.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void findProducts() {
        List<Product> productsRet = new ArrayList<Product>();
        Product product = new Product();
        product.setId(4L);
        product.setProductName("Manusa");
        product.setPrice(35.5);
        productsRet.add(product);

        when(productRepository.findAll(Sort.by("productName"))).thenReturn(productsRet);
        List<Product> products = productService.findAll();
        assertEquals(products.size(), 1);
        verify(productRepository, times(1)).findAll(Sort.by("productName"));
    }

    @Test
    public void deleteByIdThrowException() {
        //arrange
        Product product = new Product();
        product.setId(4L);
        product.setProductName("Manusa");
        product.setPrice(35.5);

        when(productRepository.findById(4L)).thenReturn(Optional.empty());

        //act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.deleteById(4L));

        //assert
        assertNotNull(exception);
        assertEquals("product " + 4L + " not found",
                exception.getMessage());

        verify(productRepository, times(0)).save(product);
        verify(productRepository, times(0)).deleteById(4L);
    }
}
