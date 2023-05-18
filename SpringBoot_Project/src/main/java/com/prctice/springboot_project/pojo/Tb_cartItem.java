package com.seiryo.springboot_project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Tb_cartItem {
    private Integer id;
    private String productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Double totalAmount;
    private String userName;
}
