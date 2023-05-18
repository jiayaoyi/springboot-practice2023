package com.seiryo.springboot_project.dao;

import com.seiryo.springboot_project.pojo.Tb_userInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserMapper {
    Integer insertUser(Tb_userInfo user);

    String queryPassword(String username);

    Integer countByUserName(String username);
}
