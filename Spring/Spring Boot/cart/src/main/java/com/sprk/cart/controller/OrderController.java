package com.sprk.cart.controller;

import com.sprk.cart.model.Orders;
import com.sprk.cart.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private CardService service;

    @GetMapping("/order/{orderId}")
    public Orders getOrder(@PathVariable long orderId) {
        return service.getOrder(orderId);
    }
}
