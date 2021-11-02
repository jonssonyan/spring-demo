package com.jonssonyan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderPaidEvent implements Serializable {
    private static final long serialVersionUID = 7156490256121580458L;
    private String orderId;
    private BigDecimal paidMoney;
}
