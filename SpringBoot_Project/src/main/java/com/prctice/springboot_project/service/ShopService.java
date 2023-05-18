package com.seiryo.springboot_project.service;

import com.seiryo.springboot_project.dao.ShopMapper;
import com.seiryo.springboot_project.pojo.Tb_order;
import com.seiryo.springboot_project.pojo.Tb_shopinfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ShopService {

    List<Tb_shopinfo> allShopInfoByPage(int startIndex, int pageSize);

    Integer allShopCount();

    Integer checkOrderState(Tb_order order);
}
