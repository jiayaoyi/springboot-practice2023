package com.seiryo.springboot_project.controller;

import com.seiryo.springboot_project.pojo.Tb_order;
import com.seiryo.springboot_project.pojo.Tb_shopinfo;
import com.seiryo.springboot_project.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService shopService;
    @RequestMapping("/index")
    public ModelAndView indexInit(HttpSession session, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {

        List<Tb_shopinfo> shopCardsList = shopService.allShopInfoByPage((pageNum*9), 9);

        int totalCount = shopService.allShopCount();
        int pageCount = (totalCount / 9);
        // 将商品列表、总数、总页数和当前页数添加到ModelAndView中
        ModelAndView mav = new ModelAndView("index");

        mav.addObject("shopCardsList", shopCardsList);
        mav.addObject("totalCount", totalCount);
        mav.addObject("pageCount", pageCount);
        mav.addObject("currentPage", pageNum); // 添加当前页数

        String username = (String) session.getAttribute("user_name");
        mav.addObject("user_name", username);

        return mav;
    }

    @GetMapping("/loadProducts")
    public ResponseEntity<List<Tb_shopinfo>> loadProducts(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        List<Tb_shopinfo> shopCardsList = shopService.allShopInfoByPage((pageNum - 1) * 9, 9);
        return ResponseEntity.ok(shopCardsList);
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.html";
    }

    @GetMapping(value = "/orderState", produces = "text/plain;charset=UTF-8")
    public String orderStateQuery(@RequestParam String orderNumber) {
        Tb_order order = new Tb_order();
        order.setOrderId(orderNumber);
        int orderStateFlag = shopService.checkOrderState(order);
        String message;

        switch (orderStateFlag) {
            case 0:
                message = "订单状态：待审核";
                break;
            case 1:
                message = "订单状态：已审核";
                break;
            case 2:
                message = "订单状态：已付款";
                break;
            case 3:
                message = "订单状态：已发货";
                break;
            default:
                message = "无效的订单状态";
        }
            return message;
    }



}
