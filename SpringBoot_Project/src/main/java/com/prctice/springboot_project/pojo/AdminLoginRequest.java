package com.seiryo.springboot_project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AdminLoginRequest {
    private Tb_admin admin;
    private String captcha;
}
