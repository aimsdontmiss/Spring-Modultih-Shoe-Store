package com.example.ShlopApp.Commerce.Domain.Payment.model;

import com.example.ShlopApp.Commerce.Domain.Order.model.Order;
import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderId;
import com.example.ShlopApp.Commerce.Domain.Payment.model.ValueObjects.PaymentId;
import com.example.ShlopApp.Commerce.Domain.Payment.model.ValueObjects.PaymentMethod;
import com.example.ShlopApp.Commerce.Domain.Payment.model.ValueObjects.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

public class Payment {
    private PaymentId paymentId;
    private OrderId orderId;

    private Currency currency = Currency.getInstance("CAD");
    private BigDecimal totalAmount;

    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    private Instant createdAt;
    private boolean isCompleted = false;
    private Instant completedAt;



    public Payment(
            PaymentId paymentId,
            OrderId orderId,
            BigDecimal totalAmount,
            PaymentMethod paymentMethod
    ) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.createdAt = Instant.now();
    }

    public void complete() {
        this.paymentStatus = PaymentStatus.PAID;
        this.isCompleted = true;
        this.completedAt = Instant.now();
    }


}
