package com.seiryo.springboot_project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tb_orderDetail {
    private String orderId;
    private String productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Double totalAmount;
}
