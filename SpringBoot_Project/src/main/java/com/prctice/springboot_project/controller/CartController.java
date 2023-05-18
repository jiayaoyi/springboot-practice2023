package com.seiryo.springboot_project.controller;

import com.seiryo.springboot_project.pojo.Tb_cartItem;
import com.seiryo.springboot_project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/addToCart")
    public String addToCart(@RequestBody Map<String, Object> payload) {
        String shopId = (String) payload.get("shopId").toString();
        String user_name = (String) payload.get(("user_name")).toString();
        cartService.addToCart(shopId,user_name);
        return ("添加成功");
    }

    @GetMapping("/init")
    public ModelAndView myPageInit(HttpSession session){
        ModelAndView mav = new ModelAndView("mypage");
        String userName = (String) session.getAttribute("user_name");
        if (userName != null && !userName.isEmpty()){
            List<Tb_cartItem> cartItemList = cartService.queryCartItemByUserName(userName);
            mav.addObject("cartItemList", cartItemList);
            System.out.println(mav.addObject("cartItemList", cartItemList));
        }
        else {
            mav.addObject("message", "购物车为空");
        }
        mav.setViewName("mypage");
        return mav;
    }

    @PostMapping("/changequantity")
    public ResponseEntity<String> updateQuantity(HttpSession session, @RequestBody Map<String, Object> cartInfo) {
        String userName = (String) session.getAttribute("user_name");
        int cartId = Integer.parseInt(String.valueOf(cartInfo.get("item_id")));
        int newQuantity = Integer.parseInt(String.valueOf(cartInfo.get("new_quantity")));
        cartService.setNewQuantity(userName, newQuantity, cartId);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/removeitem")
    public ResponseEntity<String> removeItem(HttpSession session, @RequestBody Map<String,Object> itemInfo){
        String userName = (String) session.getAttribute("user_name");
        int itemId = Integer.parseInt(String.valueOf(itemInfo.get("item_id")));
        int flag = cartService.deleteFromCart(userName,itemId);
        if (flag>0){
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failure");
        }

    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearItems(HttpSession session){
        String userName = (String) session.getAttribute("user_name");
        int flag = cartService.clearCartByUser(userName);
        if (flag>0){
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failure");
        }
    }

    @PostMapping("/createorder")
    public ResponseEntity<String> createOrder(HttpSession session){
        String userName = (String) session.getAttribute("user_name");
        Integer flag = cartService.createNewOrder(userName);
        if (flag != null && flag > 0){
            return ResponseEntity.ok("success");
        }
        else if (flag==-1){
            return ResponseEntity.ok("cartisnull");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failure");
        }
    }

}
