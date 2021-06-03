package com.springboot.controller;

import cn.hutool.core.io.resource.ResourceUtil;
import com.springboot.service.MailService;
import com.springboot.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.mail.MessagingException;
import java.net.URL;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private ApplicationContext context;

    /**
     * 测试简单邮件
     */
    @PostMapping("/sendSimpleMail")
    public Result sendSimpleMail() {
        mailService.sendSimpleMail("205820645@qq.com", "这是一封简单邮件", "这是一封普通的Spring Boot Mail测试邮件");
        return Result.success();
    }

    /**
     * 测试HTML邮件
     *
     * @throws MessagingException 邮件异常
     */
    @PostMapping("/sendHtmlMail")
    public Result sendHtmlMail() throws MessagingException {
        Context context = new Context();
        context.setVariable("project", "Spring Boot Demo");
        context.setVariable("author", "Jonsson Yan");
        context.setVariable("url", "https://github.com/jonssonyan/spring-demo");

        String emailTemplate = templateEngine.process("welcome", context);
        mailService.sendHtmlMail("205820645@qq.com", "这是一封模板HTML邮件", emailTemplate);
        return Result.success();
    }

    /**
     * 测试HTML邮件，自定义模板目录
     *
     * @throws MessagingException 邮件异常
     */
    @PostMapping("/sendHtmlMail2")
    public Result sendHtmlMail2() throws MessagingException {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(context);
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("classpath:/mail/");
        templateResolver.setSuffix(".html");

        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("project", "Spring Boot Demo");
        context.setVariable("author", "Jonsson Yan");
        context.setVariable("url", "https://github.com/jonssonyan/spring-demo");

        String emailTemplate = templateEngine.process("test", context);
        mailService.sendHtmlMail("205820645@qq.com", "这是一封模板HTML邮件", emailTemplate);
        return Result.success();
    }

    /**
     * 测试附件邮件
     *
     * @throws MessagingException 邮件异常
     */
    @PostMapping("/sendAttachmentsMail")
    public Result sendAttachmentsMail() throws MessagingException {
        URL resource = ResourceUtil.getResource("static/tx.png");
        mailService.sendAttachmentsMail("205820645@qq.com", "这是一封带附件的邮件", "邮件中有附件，请注意查收！", resource.getPath());
        return Result.success();
    }

    /**
     * 测试静态资源邮件
     *
     * @throws MessagingException 邮件异常
     */
    @PostMapping("/sendResourceMail")
    public Result sendResourceMail() throws MessagingException {
        String rscId = "Spring Boot Demo";
        String content = "<html><body>这是带静态资源的邮件<br/><img src=\'cid:" + rscId + "\' ></body></html>";
        URL resource = ResourceUtil.getResource("static/tx.png");
        mailService.sendResourceMail("205820645@qq.com", "这是一封带静态资源的邮件", content, resource.getPath(), rscId);
        return Result.success();
    }
}
