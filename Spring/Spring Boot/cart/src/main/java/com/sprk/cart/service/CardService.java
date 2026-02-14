package com.sprk.cart.service;

import com.sprk.cart.model.Orders;

public interface CardService {
    Orders getOrder(long orderId);
}
