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
        SendEmailUtils mail = new SendEmailUtils("1528779372@qq.com", "fhwjyunkvmarghhi");
        map.put("mail.smtp.host", "smtp.qq.com");

        //暂时未成功，需要调试
        /*SendMail mail = new SendMail("14789****@sina.cn","***miya");
        map.put("mail.smtp.host", "smtp.sina.com");*/
        map.put("mail.smtp.auth", "true");
        map.put("proxySet", "true");
        map.put("socksProxyHost", "web-proxy.atl.hp.com");
        map.put("socksProxyPort", "8080");
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        map.put("mail.smtp.port", "587");
        map.put("mail.smtp.socketFactory.port", "587");//587或者465
        mail.setPros(map);
        mail.initMessage();
        List<String> list = new ArrayList<String>();
        list.add("wei.wang40@hpe.com");
        list.add("haitaow@hpe.com");
        list.add("13343442079@163.com");
        try {
            mail.setRecipients(list);
            mail.setSubject("测试邮箱");
            mail.setText("谢谢合作");
            mail.setDate(new Date());
            mail.setFrom("haitaow");
            mail.setContent("还是网络的原因，外网可以发送成功，内网报错，我再研究下", "text/html; charset=UTF-8");
            List<String> fileList = new ArrayList<String>();
            fileList.add("C://Users//wahaitao//Desktop//New Microsoft Excel Worksheet.xlsx");
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