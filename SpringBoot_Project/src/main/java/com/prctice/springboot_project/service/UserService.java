package com.seiryo.springboot_project.service;

import com.seiryo.springboot_project.pojo.Tb_userInfo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface UserService {
    Integer userRegist(Tb_userInfo userInfo);

    Integer checkPass(Tb_userInfo userInfo);

    Integer checkDuplicateUserName(String userName);
}
