package com.seiryo.springboot_project.service.impl;

import com.seiryo.springboot_project.utill.OrderGenerator;
import com.seiryo.springboot_project.dao.CartMapper;
import com.seiryo.springboot_project.dao.OrderMapper;
import com.seiryo.springboot_project.dao.ShopMapper;
import com.seiryo.springboot_project.pojo.Tb_cartItem;
import com.seiryo.springboot_project.pojo.Tb_order;
import com.seiryo.springboot_project.pojo.Tb_shopinfo;
import com.seiryo.springboot_project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.seiryo.springboot_project.utill.OrderGenerator.generateOrder;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    OrderMapper orderMapper;
    @Override
    public Integer addToCart(String productId,String user_name) {
        Tb_shopinfo shopinfo = shopMapper.queryShopById(productId);
        Tb_cartItem cartItem = new Tb_cartItem();
        cartItem.setProductName(shopinfo.getShopName());
        cartItem.setPrice(shopinfo.getPrice());
        cartItem.setQuantity(1);
        cartItem.setProductId(shopinfo.getShopId());
        cartItem.setTotalAmount(shopinfo.getPrice());
        cartItem.setUserName(user_name);
        return cartMapper.insertIntoCart(cartItem);
    }

    @Override
    public List<Tb_cartItem> queryCartItemByUserName(String userName) {
        List<Tb_cartItem> cartItemsList = cartMapper.queryCartItemsByUserName(userName);
        return cartItemsList;
    }

    @Override
    public void setNewQuantity(String userName, Integer newQuantity, Integer cartId) {
        cartMapper.updateQuantityByUserNameAndCartId(userName,newQuantity,cartId);
    }

    @Override
    public Integer deleteFromCart(String userName, Integer cartId) {
        return cartMapper.deleteCartByUserNameAndCartId(userName,cartId);
    }

    @Override
    public Integer clearCartByUser(String userName) {
        return cartMapper.clearCartByUserName(userName);
    }


    @Override
    public Integer createNewOrder(String userName){
        // 检查购物车是否为空
        List<Tb_cartItem> cartItems = cartMapper.queryCartItemsByUserName(userName);
        if (cartItems == null || cartItems.isEmpty()) {
            // 如果购物车为空，返回一个特殊的错误码
            return -1;
        }
        List<String> existingOrderIds = orderMapper.selectAllOrderId();
        Tb_order newOrder = createOrderUtil(existingOrderIds);
        String orderId = newOrder.getOrderId();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("orderId", orderId);
        parameters.put("userName", userName);
        //创建新订单
        orderMapper.insertNewOrder(newOrder);
        orderMapper.insertNewOrderDetail(parameters);
        return cartItems.size();
    }


    //订单创建方法
    private Tb_order createOrderUtil(List<String> existingOrderIds){
        OrderGenerator orderGenerator = new OrderGenerator();
        Tb_order newOrder = orderGenerator.generateOrder(existingOrderIds);
        return newOrder;
    }

}
