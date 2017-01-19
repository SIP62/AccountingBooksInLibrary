package by.bsuir.lab01.dao.file.ssl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import by.bsuir.lab01.dao.DaoException;

import java.util.Properties;

public class Sender {

    private String username;
    private String password;
    private Properties props;

    public Sender(String username, String password) {
        this.username = username;
        this.password = password;
       
        String hostName = "smtp.gmail.com";
        String passSplit[] = username.split("@");
        if(passSplit[1].equals("yandex.ru") ||
        		passSplit[1].equals("narod.ru") ||
        		passSplit[1].equals("ya.ru") ||
        		passSplit[1].equals("yandex.by") ||
        		passSplit[1].equals("yandex.com")) hostName = "smtp.yandex.ru";
        if(passSplit[1].equals("mail.ru") ||
        		passSplit[1].equals("bk.ru") ||
        		passSplit[1].equals("inbox.ru") ||
        		passSplit[1].equals("list.ru")) hostName = "smtp.mail.ru";


        props = new Properties();
        props.put("mail.smtp.host", hostName);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }

    public void send(String subject, String text, String fromEmail, String toEmail) throws DaoException{
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            
            System.out.println("The message was sent successfully");
        } catch (MessagingException e) {
        	throw new RuntimeException(e);
        }
    }

}

