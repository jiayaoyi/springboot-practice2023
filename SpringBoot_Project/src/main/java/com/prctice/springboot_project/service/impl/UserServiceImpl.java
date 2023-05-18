package com.seiryo.springboot_project.service.impl;

import com.seiryo.springboot_project.dao.UserMapper;
import com.seiryo.springboot_project.pojo.Tb_userInfo;
import com.seiryo.springboot_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer userRegist(Tb_userInfo userInfo) {
        int flag = 0;
        int check = checkDuplicateUserName(userInfo.getUser_name());
        if(check == 1){
            return flag;
        }
        else{
            return userMapper.insertUser(userInfo);
        }
    }

    @Override
    public Integer checkDuplicateUserName(String userName){
        return userMapper.countByUserName(userName);
    }
    @Override
    public Integer checkPass(Tb_userInfo userInfo) {
        int flag = 0;
        String password = userMapper.queryPassword(userInfo.getUser_name());
        if (password.equals(userInfo.getUser_password())){
            flag = 1;
        }
        return flag;
    }


}
