package com.seiryo.springboot_project.pojo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tb_order {
    private Integer ID;
    private String orderId;
    private Date createDtm;
    private Integer state;
}
