package tools;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

public class JavaMailUtil {

  public static String getCompletePath(String urlPage, HttpServletRequest request) {
    StringBuffer currentUrl = request.getRequestURL();
    String uri = request.getRequestURI();
    return currentUrl.substring(0, currentUrl.indexOf(uri)) + request.getContextPath() + "/" + urlPage;
  }

  public static void sendMail(String mail, String nom, String prenom, String sujet, String texte) throws Exception {
    System.out.println("Préparation de l'envoi du mail");

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    String myAccountEmail = "agriotes2020@gmail.com";
    String password = "agriotes2020$$";

    Session session = Session.getDefaultInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(myAccountEmail, password);
      }
    });

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(myAccountEmail));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
      message.setSubject(sujet);
      message.setText(texte);
      Transport.send(message);

    } catch (Exception ex) {
      Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

    
}
