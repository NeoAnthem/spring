package com.sprk.cart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Orders {

    @Id
    private Long id;
    private String productName;
    private double price;

    public Orders() {}

    public Orders(Long id, String productName, double price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
