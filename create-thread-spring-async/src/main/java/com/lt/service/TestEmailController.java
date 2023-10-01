package com.lt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author LiTeng
 * @Date 2023/9/24 21:10
 * Version 1.0
 * @Description 测试发送邮箱信息
 */
@RestController
@RequestMapping("/send")
@Slf4j
public class TestEmailController {

    @Value("${spring.mail.username}")
    private String from;


    @Resource
    private JavaMailSender javaMailSender;

    @RequestMapping("/email")
    public void sendEmailCode(String email, String code, Integer type){

        try {
            Date date = new Date();
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);  //发送人

            message.setTo(email);  //向谁发送验证码
            message.setSentDate(date);  //发送时间

            if (type == 1){
                message.setSubject("【邮箱注册校验】");  //发送标题
//        String code = RandomUtil.randomNumbers(4);//发送长度为4的验证码
                message.setText("您本次注册邮箱帐号的验证码是：" + code + "，有效期为5分钟，请在5分钟内完成注册");
            }
            if (type == 2){
                message.setSubject("【邮箱忘记密码校验】");  //发送标题
//        String code = RandomUtil.randomNumbers(4);//发送长度为4的验证码
                message.setText("您本次修改邮箱帐号密码的验证码是：" + code + "，有效期为5分钟，请在5分钟内完成注册");
            }

            javaMailSender.send(message);
        } catch (MailException e) {
            log.error("邮箱发送失败",e);
        }
    }
}
