package com.sprk.cart.service.impl;

import com.sprk.cart.model.Orders;
import com.sprk.cart.repository.OrderRepository;
import com.sprk.cart.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CardService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Orders getOrder(long orderId) {
        return repository.findById(orderId).orElse(null);
    }
}
