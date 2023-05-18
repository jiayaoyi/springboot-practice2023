package com.seiryo.springboot_project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OrderDTO {
    private Tb_order order;
    private List<Tb_orderDetail> orderDetails;
}
