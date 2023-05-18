package com.seiryo.springboot_project.dao;

import com.seiryo.springboot_project.pojo.Tb_order;
import com.seiryo.springboot_project.pojo.Tb_shopinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ShopMapper {
    List<Tb_shopinfo> queryAllCommodityInfoByPage(int startIndex, int pageSize);

    Integer countCommodity();

    Tb_order queryOrderById(Tb_order order);

    Tb_shopinfo queryShopById(String shopId);
}
