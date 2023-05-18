package com.seiryo.springboot_project.dao;

import com.seiryo.springboot_project.pojo.Tb_cartItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {
    Integer insertIntoCart(Tb_cartItem cartItem);

    List<Tb_cartItem> queryCartItemsByUserName(String userName);

    Integer updateQuantityByUserNameAndCartId(String userName, Integer newQuantity,Integer cartId);

    Integer deleteCartByUserNameAndCartId(String userName,Integer cartId);

    Integer clearCartByUserName (String userName);
}
