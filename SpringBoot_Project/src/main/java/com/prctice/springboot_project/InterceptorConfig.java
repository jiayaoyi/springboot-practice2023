package com.seiryo.springboot_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    //初始化拦截器
    @Bean
    public MyInterceptor init(){
        return new MyInterceptor();
    }

    //配置拦截器路径
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(init()).addPathPatterns("/**").
                excludePathPatterns("/static/**");
    }

    //配置静态资源映射文件
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").
                addResourceLocations("classpath:/static/").
                addResourceLocations("classpath:/templates/");
    }
}
