package com.secure.bankapp.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ElemailService  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpMessage(String toAddress, String emailSubject, String emailBody) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(emailSubject);
        simpleMailMessage.setText(emailBody);

        logger.info(emailSubject);
        logger.info(toAddress);
        logger.info(emailBody);

        javaMailSender.send(simpleMailMessage);
    }
}