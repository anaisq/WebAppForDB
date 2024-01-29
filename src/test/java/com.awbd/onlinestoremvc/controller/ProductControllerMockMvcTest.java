package com.awbd.onlinestoremvc.controller;

import com.awbd.onlinestoremvc.model.Product;
import com.awbd.onlinestoremvc.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    Model model;

    @Test
    @WithMockUser(username = "guest", password = "12345", roles = "GUEST")
    public void getByIdMockMvc() throws Exception {
        Long id = 1l;
        Product productTest = new Product();
        productTest.setId(id);
        productTest.setProductName("test");

        when(productService.findById(id)).thenReturn(productTest);

        mockMvc.perform(get("/products/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("productDetails"))
                .andExpect(model().attribute("product", productTest))
                .andExpect(content().contentType("text/html;charset=UTF-8"));;
    }
}
