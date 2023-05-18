package com.seiryo.springboot_project.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {

    String getPassWordByAdminName(String adminName);

}
