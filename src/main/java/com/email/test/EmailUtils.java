package com.email.test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * Created by wahaitao on 3/20/2018.
 */
public class EmailUtils {
    public static boolean sendMail(String subject, String toMail,
                                   String content, String... files) {
        boolean isFlag = false;
        try {

            String smtpFromMail = "1528779372@qq.com";
            String pwd = "sgxqhmiqvyygbabe";
            //端口
            int port = 587;
            String host = "smtp.qq.com";

            Properties props = new Properties();
//            props.put("mail.smtp.host", host);
//            props.put("mail.smtp.auth", "true");

            props.put("mail.transport.protocol", "smtp");// 连接协议

            props.put("mail.smtp.host", "smtp.qq.com");// 主机名

            props.put("mail.smtp.port", 465);// 端口号
            props.put("mail.smtp.socketFactory.port", 465);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            props.put("mail.smtp.auth", "true");

            props.put("mail.smtp.ssl.enable", "true");//设置是否使用ssl安全连接  ---一般都使用

            props.put("mail.debug", "true");//设置是否显示debug信息  true 会在控制台显示相关信息

            Session session = Session.getDefaultInstance(props);
            session.setDebug(false);

            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(smtpFromMail, "hello world"));
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(toMail));
                message.setSubject(subject);
                message.addHeader("charset", "UTF-8");

                /*添加正文内容*/
                Multipart multipart = new MimeMultipart();
                BodyPart contentPart = new MimeBodyPart();
                contentPart.setText(content);

                contentPart.setHeader("Content-Type", "text/html; charset=UTF-8");
                multipart.addBodyPart(contentPart);

                /*添加附件*/
                for (String file : files) {
                    File usFile = new File(file);
                    MimeBodyPart fileBody = new MimeBodyPart();
                    DataSource source = new FileDataSource(file);
                    fileBody.setDataHandler(new DataHandler(source));
                    sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                    fileBody.setFileName("=?GBK?B?"
                            + enc.encode(usFile.getName().getBytes()) + "?=");
                    multipart.addBodyPart(fileBody);
                }

                message.setContent(multipart);
                message.setSentDate(new Date());
                message.saveChanges();
                Transport transport = session.getTransport("smtp");

                transport.connect(smtpFromMail, pwd);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                isFlag = true;
            } catch (Exception e) {
                e.printStackTrace();
                isFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isFlag;
    }

    public static void main(String[] args) {
        EmailUtils.sendMail("hello", "haitaow@hpe.com", "hello world" ,
                "C://Users//wahaitao//Desktop//出差报销.xlsx");
    }

}
