/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Database;
import dao.PersonneDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "ChangerMdpServlet", urlPatterns = {"/changerMdp"})
public class ChangerMdpServlet extends HttpServlet {

    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";
    private static final String VUE_FORM_CHG = "/WEB-INF/changerMdp.jsp";
    private static final String VUE_INDEX = "/index.jsp";
    private static final String VUE_FORM_CON = "/WEB-INF/connexion.jsp";
    // Pour tester private static final String VUE_VERIFY= "/WEB-INF/verify.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jeton = request.getParameter("jeton");
        String mail= request.getParameter("mail");
        String vue = VUE_FORM_CHG;
       
           
       

        try {
            Connection db = Database.getConnection();
            PersonneDao dao = new PersonneDao();
            PreparedStatement stmt = db.prepareStatement("SELECT * from personne where jeton= ?");
            stmt.setString(1, jeton);
                        
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               int nb = dao.getByJeton(jeton);
               if ( nb ==1) {
                 vue = VUE_FORM_CHG;    
                } else {
                  vue= VUE_ERREUR;
                   
                }
    
            }
           

        } catch (SQLException ex) {
            Logger.getLogger(ChangerMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
            vue = VUE_ERREUR;

        }catch (Exception e) {
                vue = VUE_ERREUR;
            }
       
        // response.sendRedirect(vue);
        request.getRequestDispatcher(vue).forward(request, response);

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
        String vue = VUE_FORM_CHG;                                
        String msg;
        String jeton = request.getParameter("jeton");

        // nous faisons d'abord un test sur tous les champs du formulaire
        if (mail == null || mail.trim().isEmpty() || mdp == null || mdp.trim().isEmpty() || mdp2 == null || mdp2.trim().isEmpty()) {
            request.setAttribute("erreurLogin", "Veuillez renseigner tous les champs");
           
        } else if (!mail.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+")) {
            request.setAttribute("erreurLogin", "Ce mail est invalide, veuillez saisir un autre");
            
        } else if (!mdp.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            request.setAttribute("erreurLogin", "<ul>\n"
                    + "<p><u>Le mot de passe :</u></p>\n"
                    + "<li> - doit contenir au moins 8 caractères</li>\n"
                    + "<li> - doit au moins un chiffre</li>\n"
                    + "<li> - doit contenir au moins un caractère spécial dans la liste : @#$%^&+=</li>\n"
                    + "<li> - ne doit pas contenir des espaces</li>\n"
                    + "</ul>");
           
        } else if (!mdp.equals(mdp2)) {
            request.setAttribute("erreurLogin", "Les mots de passe ne sont pas identiques ! merci de vérifier votre saisie");
           
        }

        try {
            Connection db = Database.getConnection();

//            PersonneDao.updatePersonByMdp(mail);
//            Personne p = new Personne(mail, mdp, jeton);           // Instance d'un objet p avec un constructeur paramétré qui initialise l'attribut est_Actif à false et la date_insertion à l'haure et date du système 
//            
////      String texte = "Veuillez confirmez votre inscription en cliquant sur le lien ci-après :"
//              + JavaMailUtil.getCompletePath("confirmationEmail, request);
//      String sujet = "votre mot de passe est modifié";
//
//// Dans la classe PersonneDao, nous avons des méthodes qui permettent de préparer des requêtes Sql qui seront exécutées côté base de données
//      JavaMailUtil.sendMail(mail, nom, prenom, sujet, texte);                       // Dans la classe JavaMailUtil, nous avons l'implémentation de ma méthode sendMail() qui permet t'établie l'envoi du mail
//      vue = VUE_VERIFY;                                                   // la valeur de vue change à "/WEB-INF/verify.jsp"
        } catch (SQLException ex) {
            try {
                Logger.getLogger(ChangerMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
                switch (ex.getErrorCode()) {
                    case Database.DOUBLON:
                        boolean estConfirme = PersonneDao.estValide(mail);
                        msg = (estConfirme) ? "Email déjà enregistré et confirmé"
                                : "Email déjà enregistré, veuillez confirmer votre inscription en cliquant sur le lien inclus dans le mail qui vous a été adressé";
                        break;
                    default:
                        msg = "Pb avec la base de données "
                                + ex.getMessage(); // seulement en dev (pb de sécurité)
//            vue = VUE_ERREUR;
                }
                request.setAttribute("erreurLogin", msg);
            } catch (SQLException ex1) {
                Logger.getLogger(ChangerMdpServlet.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ChangerMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher(VUE_FORM_CON).forward(request, response);                   // La servlet nous envoi vers la vue appropriée en envoyant avec les objets request et response
    }
}
/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */

//        }
    
//}
