package com.seiryo.springboot_project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tb_shopinfo {
    private int id;
    private String shopId;
    private String shopName;
    private double price;
    private double oldPrice;
    private String descr;
    private Date insertDtm;
}
