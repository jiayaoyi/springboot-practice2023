package com.seiryo.springboot_project.controller;

import com.alibaba.fastjson.JSONException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.seiryo.springboot_project.pojo.AdminLoginRequest;
import com.seiryo.springboot_project.pojo.OrderDTO;
import com.seiryo.springboot_project.pojo.Tb_admin;
import com.seiryo.springboot_project.service.AdminService;
import com.seiryo.springboot_project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    OrderService orderService;
    @Autowired
    private DefaultKaptcha captchaProducer;
    @Autowired

    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody AdminLoginRequest request, HttpSession session){
        try {
            Tb_admin admin = request.getAdmin();
            String adminLoginName = admin.getAdminName();
            String adminLoginPass = admin.getAdminPass();
            String adminCaptcha = request.getCaptcha();
            String captcha = (String) session.getAttribute("captcha");
            if (captcha == null || !captcha.equalsIgnoreCase(adminCaptcha)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("验证码错误，请重新输入");
            }
            // 检查输入的管理员名字和密码是否为空
            if (adminLoginName == null || adminLoginName.isEmpty() || adminLoginPass == null || adminLoginPass.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("管理员账号和密码不能为空！");
            }

            int flag = adminService.checkAdminPassword(adminLoginName,adminLoginPass);
            switch (flag) {
                case -1:
                    return ResponseEntity.ok("noadminname");
                case 0:
                    return ResponseEntity.ok("wrongpassword");
                case 1:
                    return ResponseEntity.ok("success");
                default:
                    // 如果服务返回了一个未知的标志，我们应该处理这个错误
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error occurred");
            }
        } catch (JSONException e) {
            // 处理 JSON 解析错误
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format");
        } catch (Exception e) {
            // 处理其他可能的错误
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request");
        }
    }

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生成验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            request.getSession().setAttribute("captcha", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @GetMapping("/init")
    public ModelAndView initializeAdminView(){
        ModelAndView mov = new ModelAndView("adminhomepage");
        Map<String, OrderDTO> orderDTOMap = orderService.getAllOrders();
        mov.addObject("orderDTOMap",orderDTOMap);
        System.out.println(mov);
        return mov;
    }

}
