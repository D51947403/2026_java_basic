package com.practice.email;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Multipart;

public class SendEmail {
    
    private final String username;
    private final String password;

    private static Properties properties = new Properties();
    private static final String PROPERTIES_FILE = "D:\\2026_java_notes\\application_secret\\email-secrets.properties";
    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fis);
            System.out.println("Properties file loaded successfully from: " + PROPERTIES_FILE);
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
    }
    // Utility method to get property value
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public SendEmail(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendEmail(String to, String cc, String subject, String body) throws MessagingException {
        System.out.println("Sending email to " + to);
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        if (cc != null && !cc.isEmpty()) {
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
        }
        message.setSubject(subject);
        message.setText(body);
        
        Transport.send(message);
        System.out.println("Email sent successfully to " + to);
    }
    
    public void sendEmailWithAttachment(String to, String cc, String subject, String body, String filePath) 
            throws MessagingException {
        System.out.println("Sending email with attachment to " + to);
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        if (cc != null && !cc.isEmpty()) {
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
        }
        message.setSubject(subject);
        
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(body);
        
        MimeBodyPart attachmentPart = new MimeBodyPart();
        try {
            attachmentPart.attachFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);
        
        message.setContent(multipart);
        Transport.send(message);
        System.out.println("Email with attachment sent successfully to " + to);
    }
    
    public void sendHtmlEmail(String to, String cc, String subject, String htmlBody) throws MessagingException {
        System.out.println("Sending HTML email to " + to);
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        if (cc != null && !cc.isEmpty()) {
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
        }
        message.setSubject(subject);
        message.setContent(htmlBody, "text/html");
        
        Transport.send(message);
        System.out.println("HTML email sent successfully to " + to);
    }
    
    public static void main(String[] args) {
        try {
            SendEmail emailSender =new SendEmail(properties.getProperty("email.username") ,properties.getProperty("email.password"));

            emailSender.sendEmail(
                "singrauld@yahoo.com,deepnarayanlodhi1@gmail.com",
                "devendra20790@gmail.com",
                "Test2 Subject2",
                "This is a test email sent from Java application."
            );
            emailSender.sendEmailWithAttachment("singrauld@yahoo.com,deepnarayanlodhi1@gmail.com", "devendra20790@gmail.com", "Test2 with attachment Subject2", "This is a test email sent from Java application with attachment.", "D:\\2026_java_notes\\app_images\\front_image.jpg");

            emailSender.sendHtmlEmail("singrauld@yahoo.com,deepnarayanlodhi1@gmail.com", "devendra20790@gmail.com", "Test2 HTML Subject2", "<h1>This is a test HTML email sent from Java application.</h1>");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}