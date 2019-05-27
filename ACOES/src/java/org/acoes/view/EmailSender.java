
package org.acoes.view;

import java.util.Date;
import java.util.Properties;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Manuel
 */
@Named
@RequestScoped
public class EmailSender {
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String ACOES_EMAIL = "acoessiiuma@gmail.com";
    private static final String ACOES_EMAIL_PASSWORD = "randompassword";
    
    public void sendTo(String to, String subject, String body){
        sendEmail(to, subject, body);
    }
    
    public void sendToACOES(String subject, String body){
        sendEmail(ACOES_EMAIL, subject, body);
    }
    
    private void sendEmail(String to_email, String subject, String body){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", SMTP_HOST_NAME);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        
        Authenticator auth = new SMTPAuthenticator(ACOES_EMAIL, ACOES_EMAIL_PASSWORD);
        
        Session mailSession = Session.getInstance(prop, auth);

        MimeMessage message = new MimeMessage(mailSession);
        try {
            Address from = new InternetAddress(ACOES_EMAIL);
            Address to = new InternetAddress(to_email);
            message.setFrom(from);
            message.addRecipient(Message.RecipientType.TO, to);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
    }
}

class SMTPAuthenticator extends Authenticator {
    private String user;
    private String password;
    
    public SMTPAuthenticator(String user, String password){
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(user,  password);
    }
}
