package com.email.test;

/**
 * Created by wahaitao on 3/20/2018.
 */
public interface EmailService {
    void sendEmail(String receivedEmail,String msg,String htmlText) throws EmailException;
}
