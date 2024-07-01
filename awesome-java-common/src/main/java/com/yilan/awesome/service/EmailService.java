//package com.yilan.awesome.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.util.Date;
//import java.util.Map;
//
///**
// * @author： yilan0916
// * @date: 2024/7/1
// */
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class EmailService {
//
//    @Autowired(required = false)
//    private final JavaMailSender javaMailSender;
//    private final TemplateEngine templateEngine;
//
//    @Value("${spring.mail.username}")
//    private String from;
//
//    /**
//     * 发送简单邮件
//     */
//    public void sendSimpleEmail(String subject, String content, String[] to) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject(subject);
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSentDate(new Date());
//        message.setText(content);
//        System.out.println(message);
//        javaMailSender.send(message);
//        log.debug("邮件发送成功: {}", message);
//    }
//
//    /**
//     * 发送Thymeleaf模板邮件
//     */
//    public void sendThymeleafMail(String subject, Map<String, Object> content, String[] to) throws MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//        helper.setSubject(subject);
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setSentDate(new Date());
//
//        Context context = new Context();
//        // 单个参数
//        context.setVariable("msg", "这是一个MESSAGE");
//        // 多个参数
//        context.setVariables(content);
//
//        String text = templateEngine.process("mail-demo", context);
//        // 第二个参数true表示邮件正文是HTML格式的，该参数不传默认为false
//        helper.setText(text);
//        System.out.println(text);
////        javaMailSender.send(mimeMessage);
//        log.debug("邮件发送成功: {}", mimeMessage);
//    }
//
//}