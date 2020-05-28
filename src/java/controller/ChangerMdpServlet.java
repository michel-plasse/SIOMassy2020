/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PersonneDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Personne;
import tools.JavaMailUtil;

/**
 *
 * @author aline
 */
@WebServlet("/changerMdp")
public class ChangerMdpServlet extends HttpServlet {
// Vues de redirection
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";
    private static final String VUE_FORM_CHG = "/WEB-INF/changerMdp.jsp";
    private static final String VUE_INDEX = "/index.jsp";
    private static final String VUE_MESSAGE = "/WEB-INF/message.jsp";
//Method get attrape le jeton de rappelMdp verifie si il n est pas null et le passe à la PersonneDao dao 
//pour recuperer la personne dans la basse de données;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jeton = request.getParameter("jeton");
        String vue = VUE_FORM_CHG;
        if (jeton == null) {
           vue = VUE_ERREUR;
            request.setAttribute("message", "Vous devez fournir un jeton");
        } else {
            PersonneDao dao = new PersonneDao();
            try {
                if (dao.jetonEstTrouve(jeton)) {
                    vue = VUE_FORM_CHG;
                } else {
                    vue = VUE_ERREUR;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChangerMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
                vue = VUE_ERREUR;
            }
        }
        request.getRequestDispatcher(vue).forward(request, response);
    }
//Method post recupere le formulaire de changerMdp verifie la bonne saisie des identifiants et compare les 2 mots de passe
//s'il le formulaire est correct on essaye avec le try de mettre a jour le mot de passe de la PersonneDao avec en verifiant le jeton et le mail.
//envoie un mail de confirmation au mail renseigné, et redirige vers la vue message;  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PersonneDao dao = new PersonneDao();
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String mdp2 = request.getParameter("mdp2");
        String vue = VUE_FORM_CHG;
        String jeton = request.getParameter("jeton");
        Personne personne = dao.personneJeton(jeton);
        personne.setMdp(mdp);
        // nous faisons d'abord un test sur tous les champs du formulaire
        if (email == null || email.trim().isEmpty() || mdp == null || mdp.trim().isEmpty() || mdp2 == null || mdp2.trim().isEmpty()) {
            request.setAttribute("erreurLogin", "Veuillez renseigner tous les champs");
        } else if (!personne.emailIsValid(email)) {
            request.setAttribute("emailEstInvalide", true);
        } else if (!personne.mdpIsValid(mdp)) {
            request.setAttribute("mdpEstInvalide", true);
        } else if (!personne.mdpIsValid(mdp2)) {
            request.setAttribute("mdpEstInvalide", true);
        } else if (!mdp.equals(mdp2)) {
            request.setAttribute("mdpEstDifferent", true);
        } else {
            try {
                PersonneDao.changerMdp(mdp, email, jeton);
                String texte = "La modification de votre mot de passe est un succès :"
                        + JavaMailUtil.getCompletePath("confirmationEmail?email=" + email, request);
                String sujet = "mot de passe changé ";
                JavaMailUtil.sendMail(email, "", "", sujet, texte);                       // Dans la classe JavaMailUtil, nous avons l'implémentation de ma méthode sendMail() qui permet t'établie l'envoi du mail
                vue = VUE_MESSAGE;    // + passer la main a jsp VUE_MESSAGE (juste un message)
                request.setAttribute("majOK", "mot de passe modifié");
            } catch (SQLException ex) {
                Logger.getLogger(ChangerMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
                vue = VUE_ERREUR;
            } catch (Exception e) {
                vue = VUE_INDEX;
            }
        }
        request.getRequestDispatcher(vue).forward(request, response);                   // La servlet nous envoi vers la vue appropriée en envoyant avec les objets request et response
    }
}
