package com.sprk.payment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PaymentDetails")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long totalAmount;
}
