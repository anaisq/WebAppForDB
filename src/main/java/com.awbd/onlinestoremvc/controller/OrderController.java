package com.awbd.onlinestoremvc.controller;

import com.awbd.onlinestoremvc.model.Order;
import com.awbd.onlinestoremvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    public String getById(@PathVariable String id, Model model){

        Order order = orderService.findById(Long.valueOf(id));
        model.addAttribute("order",
                order);
        return "orderDetails";
    }

    @RequestMapping("")
    public ModelAndView orders(){
        ModelAndView modelAndView = new ModelAndView("orderList");
        List<Order> orders = orderService.findAll();
        modelAndView.addObject("orders",orders);
        return modelAndView;
    }



}
