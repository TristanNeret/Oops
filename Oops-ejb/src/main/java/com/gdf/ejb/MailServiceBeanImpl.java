/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import java.util.Date;
import java.util.Properties;
import javax.ejb.Asynchronous;
import javax.mail.*;
import javax.ejb.Stateless;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class Mail service 
 * @author chris
 */
@Stateless
public class MailServiceBeanImpl implements MailServiceBean {
    
    private final int port = 587;
    private final String host = "smtp.oopsgdf.fr";
    private final String from = "contact@oopsgdf.fr";
    private final boolean auth = true;
    private final String username = "contact@oopsgdf.fr";
    private final String password = "gdf243mail!";
    private final boolean debug = false;
    
    /**
     * function to send an email
     * @param to the email address to send to
     * @param subject subject of the email
     * @param message message of the email
     */
    @Asynchronous
    @Override
    public void sendEmail(String to, String subject, String message) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        props.put("mail.smtp.ssl.enable", false);
        Authenticator authenticator = null;
        if (auth) {
            props.put("mail.smtp.auth", true);
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }
        Session session = Session.getInstance(props, authenticator);
        session.setDebug(debug);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            mimeMessage.setRecipients(Message.RecipientType.TO, address);
            mimeMessage.setSubject(subject);
            mimeMessage.setSentDate(new Date());
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
