package com.seiryo.springboot_project.service.impl;

import com.seiryo.springboot_project.dao.OrderMapper;
import com.seiryo.springboot_project.pojo.OrderDTO;
import com.seiryo.springboot_project.pojo.Tb_order;
import com.seiryo.springboot_project.pojo.Tb_orderDetail;
import com.seiryo.springboot_project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Map<String, OrderDTO> getAllOrders() {
        // 获取所有订单和订单详情
        List<Tb_order> orders = orderMapper.selectAllOrder();
        List<Tb_orderDetail> orderDetails = orderMapper.selectAllOrderDetail();

        // 将订单信息按照orderId映射到OrderDTO对象
        Map<String, OrderDTO> orderDTOMap = mapOrdersToOrderDTOs(orders);

        // 将订单详情信息添加到对应的OrderDTO对象
        addOrderDetailsToOrderDTOs(orderDetails, orderDTOMap);

        return orderDTOMap;
    }

    private Map<String, OrderDTO> mapOrdersToOrderDTOs(List<Tb_order> orders) {
        Map<String, OrderDTO> orderDTOMap = new HashMap<>();
        for (Tb_order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrder(order);
            orderDTOMap.put(order.getOrderId(), orderDTO);
        }
        return orderDTOMap;
    }

    private void addOrderDetailsToOrderDTOs(List<Tb_orderDetail> orderDetails, Map<String, OrderDTO> orderDTOMap) {
        for (Tb_orderDetail orderDetail : orderDetails) {
            String orderId = orderDetail.getOrderId();
            if (orderDTOMap.containsKey(orderId)) {
                OrderDTO orderDTO = orderDTOMap.get(orderId);
                addOrderDetailToOrderDTO(orderDTO, orderDetail);
            }
        }
    }

    private void addOrderDetailToOrderDTO(OrderDTO orderDTO, Tb_orderDetail orderDetail) {
        List<Tb_orderDetail> orderDetailList = orderDTO.getOrderDetails();
        if (orderDetailList == null) {
            orderDetailList = new ArrayList<>();
            orderDTO.setOrderDetails(orderDetailList);
        }
        orderDetailList.add(orderDetail);
    }
}
