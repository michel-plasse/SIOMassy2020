/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Database;
import dao.PersonneDao;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Personne;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import tools.JavaMailUtil;

@WebServlet(name = "GestionMdpServlet", urlPatterns = {"/demanderNouvMdp"})
public class GestionMdpServlet extends HttpServlet {

    private static final String VUE_INDEX = "/index.jsp";
    private static final String VUE_dmdMDP = "/WEB-INF/demanderNouvMdp.jsp";
    private static final String VUE_ERREUR = "WEB-INF/PageError.jsp";
    private static final String VUE_FORM_INS = "/WEB-INF/inscription.jsp";
    private static final String VUE_VERIFY = "/WEB-INF/verify.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VUE_dmdMDP).forward(request, response);
    }

    @Override
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mail = request.getParameter("mail");
        String mdp = request.getParameter("mdp");
        String vue = VUE_dmdMDP;
        Random random = new Random();                               // Instance d'un objet random
        random.nextInt(9999999);                                  // Selection aléatoire d'un nombre entre 0 est 9999999
        String jeton = DigestUtils.md5Hex("" + random);
        String msg;

        //Personne user = null;
        // nous faisons d'abord un test sur tous les champs du formulaire
        if (mail == null || mail.trim().isEmpty()) {
      request.setAttribute("erreurLogin", "Veuillez renseigner tous les champs");
    } else if (!mail.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+")) {
      request.setAttribute("erreurLogin", "Ce mail est invalide, veuillez saisir un autre");
    } else {
     
      try {
        PersonneDao dao = new PersonneDao();
        int nb = dao.setJeton(mail, jeton);
        if (dao.estValide(mail)==true) {
          // Jeton positionne : envoyer le mail avec JavaMailUtil
          String texte = "Veuillez confirmer la modification de votre mot de passe en cliquant sur le lien ci-après :"
              + JavaMailUtil.getCompletePath("confirmationChangementMdp?token=" + jeton, request);
      String sujet = "Confirmez votre changement de mot de passe sur AGRIOTES";
            try {
                JavaMailUtil.sendMail(mail,"", "", sujet, texte);                       // Dans la classe JavaMailUtil, nous avons l'implémentation de ma méthode sendMail() qui permet t'établie l'envoi du mail
            } catch (Exception ex) {
                Logger.getLogger(GestionMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
      vue = VUE_VERIFY;    // + passer la main a jsp VUE_MESSAGE (juste un message)
        } else {
          request.setAttribute("erreurLogin", "Email introuvable ou  modification pas confirmée");
        }
      } catch (SQLException ex) {
          Logger.getLogger(GestionMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
          vue = VUE_ERREUR;
        }

      }
      request.getRequestDispatcher(vue).forward(request, response);                   // La servlet nous envoi vers la vue appropriée en envoyant avec les objets request et response
    }
}
