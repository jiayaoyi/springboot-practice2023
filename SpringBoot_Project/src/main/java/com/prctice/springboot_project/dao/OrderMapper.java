package com.seiryo.springboot_project.dao;

import com.seiryo.springboot_project.pojo.Tb_order;
import com.seiryo.springboot_project.pojo.Tb_orderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrderMapper {
    Integer insertNewOrder (Tb_order newOrder);

    int insertNewOrderDetail(Map<String, Object> parameters);

    List<String> selectAllOrderId();

    List<Tb_orderDetail> selectAllOrderDetail();

    List<Tb_order> selectAllOrder();
}
