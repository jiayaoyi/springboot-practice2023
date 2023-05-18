package com.seiryo.springboot_project.utill;

import com.seiryo.springboot_project.pojo.Tb_order;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@Repository
public class OrderGenerator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public static String generateOrderId() {
        String datePart = LocalDate.now().format(DATE_FORMATTER);
        int count = COUNTER.incrementAndGet();
        String countPart = String.format("%06d", count);
        return datePart + countPart;
    }

    public static String generateUniqueOrderId(List<String> existingOrderIds) {
        String orderId;
        do {
            orderId = generateOrderId();
        } while (existingOrderIds.contains(orderId));
        return orderId;
    }

    public static Tb_order generateOrder(Integer state, List<String> existingOrderIds) {
        String orderId = generateUniqueOrderId(existingOrderIds);
        Date createDtm = new Date();
        return new Tb_order(null, orderId, createDtm, state);
    }

    public static Tb_order generateOrder(List<String> existingOrderIds) {
        return generateOrder(0, existingOrderIds);
    }
}
