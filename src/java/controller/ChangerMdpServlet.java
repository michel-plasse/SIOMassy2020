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
@WebServlet("/changerMdp")
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
        String vue = VUE_FORM_CHG;
        if (jeton == null) {
//            vue = VUE_ERREUR;
            request.setAttribute("message", "Vous n'avez pas le droit aux modifications");
        } else {

            try {
                Connection db = Database.getConnection();
                PersonneDao dao = new PersonneDao();
                PreparedStatement stmt = db.prepareStatement("SELECT * from personne where jeton= ?");
                stmt.setString(1, jeton);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Personne personne = dao.personneJeton(jeton);

                    if (personne != null) {
                        vue = VUE_FORM_CHG;

                    } else {
                        vue = VUE_ERREUR;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChangerMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
                vue = VUE_ERREUR;
            }
        }
        request.getRequestDispatcher(vue).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PersonneDao dao = new PersonneDao();
        String email = request.getParameter("email");
        String mdp1 = request.getParameter("mdp1");
        String mdp2 = request.getParameter("mdp2");
        String vue = VUE_FORM_CHG;
        String msg;
        String jeton = request.getParameter("jeton");
        Personne personne = dao.personneJeton(jeton);
        personne.setMdp(mdp1);
        // nous faisons d'abord un test sur tous les champs du formulaire
        if (email == null || email.trim().isEmpty() || mdp1 == null || mdp1.trim().isEmpty() || mdp2 == null || mdp2.trim().isEmpty()) {
            request.setAttribute("erreurLogin", "Veuillez renseigner tous les champs");
        } else if (!personne.emailIsValid(email)) {
            request.setAttribute("emailEstInvalide", true);
        } else if (!personne.mdpIsValid(mdp1)) {
            request.setAttribute("mdpEstInvalide", true);
        } else if (!personne.mdpIsValid(mdp2)) {
            request.setAttribute("mdpEstInvalide", true);
        } else if (!mdp1.equals(mdp2)) {
            request.setAttribute("mdpEstDifferent", true);
        } else {
            try {
                PersonneDao.majByMailPersonne(personne);
                String texte = "La modification de votre mot de passe est un succès :"
                        + JavaMailUtil.getCompletePath("connexion", request);
                String sujet = "Connectez vous avec vos nouveaux identifiants";
                JavaMailUtil.sendMail(email, "", "", sujet, texte);                       // Dans la classe JavaMailUtil, nous avons l'implémentation de ma méthode sendMail() qui permet t'établie l'envoi du mail
                vue = VUE_INDEX;    // + passer la main a jsp VUE_MESSAGE (juste un message)
                request.setAttribute("majOK", "mot de passe modifié");
            } catch (SQLException ex) {
                Logger.getLogger(ModifierProfilServlet.class.getName()).log(Level.SEVERE, null, ex);
                vue = VUE_ERREUR;
            } catch (Exception e) {
                vue = VUE_ERREUR;
            }
        }
        request.getRequestDispatcher(vue).forward(request, response);                   // La servlet nous envoi vers la vue appropriée en envoyant avec les objets request et response
    }

}
