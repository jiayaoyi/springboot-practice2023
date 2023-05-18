package com.seiryo.springboot_project.test;

import com.seiryo.springboot_project.pojo.Tb_order;
import com.seiryo.springboot_project.service.ShopService;
import com.seiryo.springboot_project.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class test {


    public static void main(String[] args) {
        ShopService shopService = new ShopServiceImpl();
        int b = shopService.allShopCount();
        System.out.println(b);
        int a =  shopService.checkOrderState(new Tb_order(null,"20230509000004",null,null));
        System.out.println(a);
    }
}
