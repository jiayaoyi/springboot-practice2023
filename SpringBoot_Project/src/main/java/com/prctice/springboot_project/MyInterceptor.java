package com.seiryo.springboot_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MyInterceptor implements HandlerInterceptor {

    /**
     * 过滤器的作用：假设我们想从登陆界面访问主界面的时候（商城系统 - 旅游项目）
     * 再主界面进行遍历商品，登录界面（AJAX） - 后台 - 通过访问数据库（数据放到 Session） - 主界面（进行商品便利） EL JSTL核心包
     * 登录界面（AJAX） - 后台 - 通过访问数据库（数据放到 Session） - 主界面（进行商品便利） EL JSTL核心包
     * 登录界面（AJAX） - 先经过过滤器 - 后台 - 通过访问数据库（数据放到 Session） - 主界面（进行商品便利） EL JSTL核心包
     * 先经过过滤器（用来保护数据安全性）
     * 过滤器优先执行（通过过滤器进行访问数据库 再拿到数据库的信息之后 直接设置到 Session 里边）
     * 登录界面（AJAX） - 先经过过滤器 - 主界面（进行商品便利）
     * 1.实现系统过滤器 HandlerInterceptor -> import org.springframework.web.servlet.HandlerInterceptor;
     * 2.实现拦截
     * 3.在拦截内部实现方法调用，或Session的使用  return true; 开启拦截
     * 4.创建拦截器设置类 InterceptorConfig 并继承 WebMvcConfigurationSupport 并添加注解 @Configuration
     * 5.初始化拦截器
     * 6.配置拦截器路径
     * 7.配置静态资源映射文件
     */

    //初始化用户服务

    //创建设置所有用户


    //拦截方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //提起设置 处理异常的方法 提起预知报错
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

}
