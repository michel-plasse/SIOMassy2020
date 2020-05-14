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
            // la vue reste à VUE_FORM_INS
        } else if (!mail.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+")) {
            request.setAttribute("erreurLogin", "Ce mail est invalide, veuillez saisir un autre");
            // la vue reste à VUE_FORM_INS
        } else if (!mdp.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            request.setAttribute("erreurLogin", "<ul>\n"
                    + "<p><u>Le mot de passe :</u></p>\n"
                    + "<li> - doit contenir au moins 8 caractères</li>\n"
                    + "<li> - doit au moins un chiffre</li>\n"
                    + "<li> - doit contenir au moins un caractère spécial dans la liste : @#$%^&+=</li>\n"
                    + "<li> - ne doit pas contenir des espaces</li>\n"
                    + "</ul>");
        try {
            if (mail == null || mail.trim().isEmpty() //|| password == null || password.trim().isEmpty()
                    ) {
                request.setAttribute("erreurLogin", "Les champs sont obligatoires");
            } else {
                user = PersonneDao.getByLoginPassword(mail, mdp);
                if (user != null) {
                    // Ajouter en session
                    HttpSession maSession = request.getSession(true);
                    maSession.setAttribute("user", user);
                    vue = VUE_INDEX;
                } else {
                    request.setAttribute("erreurLogin",
                            "Utilisateur inconnu ou mot de passe incorrect");
                }
            }
        } catch (SQLException exc) {
            Logger.getLogger(ConnexionServlet.class.getName()).log(Level.SEVERE, null, exc);
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;
        }
        // Passer la main à la JSP
        request.getRequestDispatcher(VUE_dmdMDP).forward(request, response);
    }
}
