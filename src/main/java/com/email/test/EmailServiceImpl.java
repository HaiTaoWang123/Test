package com.email.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by wahaitao on 3/20/2018.
 */
public class EmailServiceImpl implements EmailService{
    private static Log log = LogFactory.getLog(EmailServiceImpl.class);
    private JavaMailSender javaMailSender;
    private String systemEmail;



    public void sendEmail(String receivedEmail, String msg, String htmlText) throws EmailException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setFrom(systemEmail);
            messageHelper.setTo(receivedEmail);
            messageHelper.setSubject(msg);
            messageHelper.setText(htmlText,true);
            javaMailSender.send(message);
            log.info("邮件发送成功");
        } catch (MessagingException e) {
            log.info("邮件发送失败");
            throw new EmailException("Failed to send email",e);
        }
    }

    public static void main(String[] args){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("email.xml");
        EmailService emailService = (EmailService) classPathXmlApplicationContext.getBean("EmailService");
        try {
            emailService.sendEmail("haitaow@hpe.com","Hello World","<h3>Test<h3>");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String getSystemEmail() {
        return systemEmail;
    }

    public void setSystemEmail(String systemEmail) {
        this.systemEmail = systemEmail;
    }
}
