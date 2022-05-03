package com.nowcoder.community.util;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

//代替这个客户端
//加个@Compoent，这个bean需要spring来进行管理
@Component
public class MailClient {

    //声明一个Logger，关键的地方要记录一些日志
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired
    //因为需要使用JavaMailSender，且其是spring来进行管理的，所以可以自动注入这个bean中
    private JavaMailSender mailSender;

    //把spring.mail.username注入到当前的bean中
    //这是发件人
    @Value("${spring.mail.username}")
    private String from;

    public void senderMail(String to, String subject, String content) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            //发件人
            helper.setFrom(from);
            //收件人
            helper.setTo(to);
            //主题
            helper.setSubject(subject);
            //内容，ture代表可以支持html，不加true的话代表只支持普通文本
            helper.setText(content,true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败"+e.getMessage());
        }
    }


}
