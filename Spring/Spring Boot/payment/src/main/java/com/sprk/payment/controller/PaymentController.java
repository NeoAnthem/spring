package com.sprk.payment.controller;

import com.sprk.payment.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/{orderId}")
    public Orders getOrderDetails(@PathVariable long orderId) {

        Orders order = restTemplate.getForObject(
                "http://localhost:8080/api/v1/order/" + orderId,
                Orders.class
        );
        return order;
    }
}
