package com.email.test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by wahaitao on 3/20/2018.
 */
public class SendEmailTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        SendEmailUtils mail = new SendEmailUtils("1528779372@qq.com", "sgxqhmiqvyygbabe");
        map.put("mail.smtp.host", "smtp.qq.com");

        //暂时未成功，需要调试
        /*SendMail mail = new SendMail("14789****@sina.cn","***miya");
        map.put("mail.smtp.host", "smtp.sina.com");*/
        map.put("mail.smtp.auth", "true");
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        map.put("mail.smtp.port", "465");
        map.put("mail.smtp.socketFactory.port", "465");
        mail.setPros(map);
        mail.initMessage();
        List<String> list = new ArrayList<String>();
        list.add("haitaow@hpe.com");
        list.add("13343442079@163.com");
        try {
            mail.setRecipients(list);
            mail.setSubject("测试邮箱");
            mail.setText("谢谢合作");
            mail.setDate(new Date());
            mail.setFrom("MY");
            mail.setContent("谢谢合作", "text/html; charset=UTF-8");
            List<String> fileList = new ArrayList<String>();
            fileList.add("C://Users//wahaitao//Desktop//出差报销.xlsx");
            mail.setMultiparts(fileList);
            System.out.println(mail.sendMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}