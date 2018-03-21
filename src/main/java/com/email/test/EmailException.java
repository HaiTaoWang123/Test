package com.email.test;

/**
 * Created by wahaitao on 3/20/2018.
 */
public class EmailException extends Exception{
    private static final long serialVersionUid = 1L;

    public EmailException() {
        super();
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
