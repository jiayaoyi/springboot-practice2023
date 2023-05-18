package com.seiryo.springboot_project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Tb_admin {
    private Integer adminId;
    private String adminName;
    private String adminPass;
}
