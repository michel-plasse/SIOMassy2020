package SMTP;

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
 
public class JavaMailUtil {
 
  public static void sendMail(String recepient,String name,String prenom,String jeton) throws Exception {
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
          protected PasswordAuthentication getPasswordAuthentication(){
              return new PasswordAuthentication(myAccountEmail, password);
          }
      });  
      
       try {
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(myAccountEmail));
           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));     
           message.setSubject("Confirmation d'inscription Application Web AGRIOTES");
           //message.setText("Bonjour" + prenom +" "+name+" , votre compte a bien été créé");
           message.setText("Veuillez confirmez votre inscription en cliquant sur le lien ci-après :"+"http://localhost:8082/SIOMassy2020/confirmationEmail?token="+jeton);
           Transport.send(message);
           System.out.println("Message envoyé avec succès");
            }
        catch (Exception ex) {
          Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
      }

        
    

        

    

        
    }
}