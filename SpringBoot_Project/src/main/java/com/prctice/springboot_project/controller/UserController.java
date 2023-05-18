package com.seiryo.springboot_project.controller;
import com.alibaba.fastjson.JSON;
import com.seiryo.springboot_project.pojo.Tb_userInfo;
import com.seiryo.springboot_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/regist")
    public Integer userRegist(@RequestBody String userInfoStr) {
        Tb_userInfo userInfo = JSON.parseObject(userInfoStr, Tb_userInfo.class);
        int a = userService.userRegist(userInfo);
        return a;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Integer userLogin (@RequestBody String userInfoStr,HttpSession session){
        Tb_userInfo userInfo = JSON.parseObject(userInfoStr,Tb_userInfo.class);
        int a = userService.checkPass(userInfo);
        if(a == 1 ){
            session.setAttribute("user_name", userInfo.getUser_name());
            return a;
        }
        else {
            return 0;
        }
    }
}
