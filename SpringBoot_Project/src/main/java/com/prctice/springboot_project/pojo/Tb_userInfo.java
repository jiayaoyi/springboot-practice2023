package com.seiryo.springboot_project.pojo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tb_userInfo {
    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_balance;
}
