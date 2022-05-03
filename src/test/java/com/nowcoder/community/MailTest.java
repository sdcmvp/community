package com.nowcoder.community;


import com.nowcoder.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTest {

    @Autowired
    private MailClient mailClient;

    //模板引擎
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTestMail() {
        mailClient.senderMail("2363976330@qq.com","TEST","Welcome");
    }

    @Test
    public void testHtmlMail() {
        //利用Context对象，来进行参数传递，因为是tmyleaf模板引擎。包是import org.thymeleaf.context.Context;
        Context context = new Context();
        //把要传给模板的变量存到这个对象
        context.setVariable("username","sunday");

        //参数构建完成后，调用模板引擎，去生成动态网页
        //告诉其模板文件存在哪，以及数据在哪
        //模板引擎主要的目的就是生成动态网页
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        //发邮件还是通过mailClient来发
        mailClient.senderMail("2363976330@qq.com","Test2",content);
    }
}
