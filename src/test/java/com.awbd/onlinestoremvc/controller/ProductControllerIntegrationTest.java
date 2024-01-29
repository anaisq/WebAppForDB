package com.awbd.onlinestoremvc.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class ProductControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "guest", password = "12345", roles = "GUEST")
    public void showByIdMvc() throws Exception {

        mockMvc.perform(get("/products/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("productDetails"));
    }


    @Test
    @WithMockUser(username = "guest", password = "12345", roles = "GUEST")
    public void showByIdNotFound() throws Exception {

        mockMvc.perform(get("/products/{id}", "17"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("notFoundException"));
    }
}
