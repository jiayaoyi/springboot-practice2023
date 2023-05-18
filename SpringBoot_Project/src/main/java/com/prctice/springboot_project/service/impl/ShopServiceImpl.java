package com.seiryo.springboot_project.service.impl;

import com.seiryo.springboot_project.dao.ShopMapper;
import com.seiryo.springboot_project.pojo.Tb_order;
import com.seiryo.springboot_project.pojo.Tb_shopinfo;
import com.seiryo.springboot_project.pojo.Tb_userInfo;
import com.seiryo.springboot_project.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    @Qualifier(value = "shopMapper")
    private ShopMapper shopMapper;
    @Override
    public List<Tb_shopinfo> allShopInfoByPage(int startIndex, int pageSize) {
        return shopMapper.queryAllCommodityInfoByPage(startIndex,pageSize);
    }

    @Override
    public Integer allShopCount(){
        return shopMapper.countCommodity();
    }

    @Override
    public Integer checkOrderState(Tb_order order) {
        Tb_order queriedOrder = shopMapper.queryOrderById(order);
        return queriedOrder.getState();
    }
}
