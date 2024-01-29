package com.awbd.onlinestoremvc.controller;

import com.awbd.onlinestoremvc.exceptions.ResourceNotFoundException;
import com.awbd.onlinestoremvc.model.Order;
import com.awbd.onlinestoremvc.model.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.awbd.onlinestoremvc.service.OrderService;
import com.awbd.onlinestoremvc.service.ProductService;


import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("")
    public ModelAndView products(){
        ModelAndView modelAndView = new ModelAndView("productList");
        List<Product> products = productService.findAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }


    @GetMapping("/{id}")
    public String getById(@PathVariable String id, Model model){

        Product product = productService.findById(Long.valueOf(id));
        model.addAttribute("product",
                product);
        return "productDetails";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id){
        productService.deleteById(Long.valueOf(id));
        return "redirect:/products";
    }


    @RequestMapping("/form")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        List<Order> ordersAll = orderService.findAll();
        model.addAttribute("ordersAll", ordersAll );
        return "productform";
    }


    @PostMapping("")
    public String saveOrUpdate(@Valid @ModelAttribute Product product
    ){
        Product savedProduct = productService.save(product);
        return "redirect:/products" ;
    }


    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("product",
                productService.findById(Long.valueOf(id)));

        List<Order> ordersAll = orderService.findAll();
        model.addAttribute("ordersAll", ordersAll );

        return "productForm";
    }

}
