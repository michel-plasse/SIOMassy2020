/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Database;
import dao.PersonneDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Personne;
import org.apache.commons.codec.digest.DigestUtils;
import tools.JavaMailUtil;

/**
 *
 * @author aline
 */
@WebServlet( urlPatterns = "/changerMdp")
public class ChangerMdpServlet extends HttpServlet {

private static final String VUE_INDEX = "/index.jsp";
  private static final String VUE_FORM_CON = "/WEB-INF/connexion.jsp";
  private static final String VUE_FORM_CHGMDP = "/WEB-INF/changerMdp";
  private static final String VUE_ERREUR = "/WEB-INF/exception.jsp";
  private static final String VUE_VALMDP = "/WEB-INF/modificationValideMdp.jsp";
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { String jeton = request.getParameter("jeton");
    //Timestamp timestampvalidation = new Timestamp(System.currentTimeMillis());
//    LocalDateTime now = LocalDateTime.now();
//    Timestamp timestamp = Timestamp.valueOf(now);
//
//    String vue = VUE_FORM_CON;
//
//    try {
//      Connection db = Database.getConnection();
//      PreparedStatement stmt = db.prepareStatement("SELECT * from personne where jeton= ?");
//      stmt.setString(1, jeton);
//      ResultSet rs = stmt.executeQuery();
//      if (rs.next()) {
//        Timestamp t = rs.getTimestamp("date_butoir_jeton");
//        //long diff = now.getTime()-t.getTime();              
//        //if (diff<=86400000){
//        if (t.getTime() > timestamp.getTime()) {
//          PreparedStatement stmt1 = db.prepareStatement("UPDATE personne SET jeton=? , date_butoir_jeton= ? ,date_inscription =? where jeton= ? ");
//          stmt1.setString(1, null);
//          stmt1.setTimestamp(2, null);
//          stmt1.setTimestamp(3, timestamp);
//          stmt1.setString(4, jeton);
//          int i = stmt1.executeUpdate();
//          if (i == 1) {
//            request.setAttribute("messageBienvenue", "Bienvenue, vous venez de finaliser votre inscription");
//          }
//        } else {
//          PersonneDao.deletePerson(jeton);
//          request.setAttribute("messageBienvenue", "Vous avez dépassé le temps accordé pour valider votre inscription, Nous vous invitons à vous réinscrire");
//        }
//
//      }
//      // Pour tester vue=VUE_VERIFY;
//
//    } catch (SQLException ex) {
//      Logger.getLogger(ConfirmationEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
//      vue = VUE_INDEX;
//
//    
//
//  }
//
//      request.getRequestDispatcher(VUE_FORM_CHGMDP).forward(request, response);
//        //variables
//   
//
//        
//        
//        
//        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
    String mail = request.getParameter("mail");
    String mdp = request.getParameter("mdp");
    String mdp2 = request.getParameter("mdp2");
    String vue = VUE_FORM_CHGMDP;                                //inscription.jsp
    Random random = new Random();                               // Instance d'un objet random
    random.nextInt(9999999);                                  // Selection aléatoire d'un nombre entre 0 est 9999999
    String jeton = DigestUtils.md5Hex("" + random);
    String msg;

//    // nous faisons d'abord un test sur tous les champs du formulaire
//    if ( mail == null || mail.trim().isEmpty() || mdp == null || mdp.trim().isEmpty() || mdp2 == null || mdp2.trim().isEmpty()) {
//      request.setAttribute("erreurLogin", "Veuillez renseigner tous les champs");
//      // la vue reste à VUE_FORM_INS
//    } else if (!mail.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+")) {
//      request.setAttribute("erreurLogin", "Ce mail est invalide, veuillez saisir un autre");
//      // la vue reste à VUE_FORM_INS
//    } else if (!mdp.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
//      request.setAttribute("erreurLogin", "<ul>\n"
//              + "<p><u>Le mot de passe :</u></p>\n"
//              + "<li> - doit contenir au moins 8 caractères</li>\n"
//              + "<li> - doit au moins un chiffre</li>\n"
//              + "<li> - doit contenir au moins un caractère spécial dans la liste : @#$%^&+=</li>\n"
//              + "<li> - ne doit pas contenir des espaces</li>\n"
//              + "</ul>");
//      // la vue reste à VUE_FORM_INS    
//    } else if (!mdp.equals(mdp2)) {
//      request.setAttribute("erreurLogin", "Les mots de passe ne sont pas identiques ! merci de vérifier votre saisie");
//      // la vue reste à VUE_FORM_INS    
//    }
//
//    try {
//      PersonneDao.updatePersonByMdp(mail);
//      PersonneDao con = new PersonneDao();
//         int nb = con.setJeton(mail, jeton);
//                if (nb == 1) {
//                    // Jeton positionne : envoyer le mail avec JavaMailUtil
//                    String texte = "Veuillez confirmer la modification de votre mot de passe en cliquant sur le lien ci-après :"
//                            + JavaMailUtil.getCompletePath("changerMdp?jeton=" + jeton, request);
//                    String sujet = "Confirmez votre changement de mot de passe sur AGRIOTES";
//                    JavaMailUtil.sendMail(mail, "", "", sujet, texte);                       // Dans la classe JavaMailUtil, nous avons l'implémentation de ma méthode sendMail() qui permet t'établie l'envoi du mail
//                    vue = VUE_VALMDP;    // + passer la main a jsp VUE_MESSAGE (juste un message)
//                } else {
//                    request.setAttribute("erreurLogin", "Email introuvable ou inscription pas confirmée à temps");
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(RappelMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
//                vue = VUE_ERREUR;
//            } catch (Exception e) {
//                vue = VUE_ERREUR;}
    request.getRequestDispatcher(vue).forward(request, response);                   // La servlet nous envoi vers la vue appropriée en envoyant avec les objets request et response
  }
}
