package com.seiryo.springboot_project.service;

import com.seiryo.springboot_project.pojo.Tb_cartItem;
import com.seiryo.springboot_project.pojo.Tb_order;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface CartService {
    Integer addToCart(String productId, String user_name);

    List<Tb_cartItem> queryCartItemByUserName(String userName);

    void setNewQuantity(String userName,Integer newQuantity,Integer cartId);

    Integer deleteFromCart(String userName, Integer cartId);

    Integer clearCartByUser(String userName);

    Integer createNewOrder(String userName);

}
