package com.seiryo.springboot_project.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.seiryo.springboot_project.dao.AdminMapper;
import com.seiryo.springboot_project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;


    public Integer checkAdminPassword(String adminName,String inputPassword){
        String adminPass = adminMapper.getPassWordByAdminName(adminName);
        int flag ;
        if (adminPass.isEmpty()){
            flag = -1;
            return flag;
        } else {
            if (inputPassword.equals(adminPass)){
                flag = 1;
                return flag;
            } else {
                flag = 0;
                return flag;
            }
        }
    }





}
