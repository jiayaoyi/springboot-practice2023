package com.seiryo.springboot_project.service;

import com.seiryo.springboot_project.pojo.OrderDTO;

import java.util.Map;

public interface OrderService {

    Map<String, OrderDTO> getAllOrders();
}
