///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
//import dao.PersonneDao;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import modele.Personne;
//
///**
// *
// * @author aline
// */
//@WebServlet(name = "DemanderNouvMdpServlet", urlPatterns = {"/demanderNouvMdp"})
//
//public class DemanderNouvMdpServlet extends HttpServlet {
//private static final String VUE_DemandeNOUV = "WEB-INF/demanderNouvMdp.jsp";
//  
//
//
//protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet DemanderNouvMdpServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet DemanderNouvMdpServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//      request.getSession(true).invalidate();
//    request.getRequestDispatcher(VUE_DemandeNOUV ).forward(request, response);
//     
//    }
//
//    @Override
//    
// protected void doPost(HttpServletRequest request, HttpServletResponse response)
//          throws ServletException, IOException {
////   request.getSession(true).invalidate();
////    request.getRequestDispatcher(VUE_DemandeNOUV ).forward(request, response);
//  
//  String login = request.getParameter("login");
// 
// try {
//      if (login == null || login.trim().isEmpty()
//              //|| password == null || password.trim().isEmpty()
//              ) {
//        request.setAttribute("erreurLogin", "Les champs sont obligatoires");
//      } else {
//          Personne user = PersonneDao.getByLogin(login);
//        if (user != null) {
//          // Ajouter en session
//          HttpSession maSession = request.getSession(true);
//          maSession.setAttribute("user", user);
//          //vue = VUE_INDEX
//                  ;
//        } else {
//          request.setAttribute("erreurLogin",
//                  "Utilisateur inconnu ou mot de passe incorrect");
//        }
//      }
//    } catch (SQLException exc) {
//      Logger.getLogger(ConnexionServlet.class.getName()).log(Level.SEVERE, null, exc);
//      request.setAttribute("exception", exc);
//      //vue = VUE_ERREUR;
//    }
// 
// }
//
//  
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>

//}
package controller;

import javax.mail.*;
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
//import org.apache.commons.codec.digest.DigestUtils;

@WebServlet("/inscription")
public class DemanderNouvMdpServlet extends HttpServlet {

    private static final String VUE_INDEX = "/index.jsp";
 
    private static final String VUE_FORM_INS = "/WEB-INF/inscription.jsp";
    private static final String VUE_ERREUR = "/WEB-INF/exception.jsp";
    private static final String VUE_VERIFY = "/WEB-INF/verify.jsp";
    private static final String VUE_DemandeNOUV = "/WEB-INF/demanderNouvMdp.jsp";

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.getRequestDispatcher(VUE_FORM_INS).forward(request, response);
//    }
    
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
      request.getSession(true).invalidate();
    request.getRequestDispatcher(VUE_DemandeNOUV ).forward(request, response);
    }
  
    /**
     *
     * @param request
     */
    @Override

    protected void doPost(HttpServletRequest request , HttpServletResponse response) {
        
        String mail = request.getParameter("mail");
        String mdp = request.getParameter("mdp");
       
        String vue = VUE_FORM_INS;                                //inscription.jsp
        Random random = new Random();                               // Instance d'un objet random
        random.nextInt(9999999);                                  // Selection aléatoire d'un nombre entre 0 est 9999999
        //String jeton = DigestUtils.md5Hex("" + random);
        String msg;

        // nous faisons d'abord un test sur tous les champs du formulaire
        if ( mail == null || mail.trim().isEmpty()) {
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
            // la vue reste à VUE_FORM_INS    
     }// else if (!mdp.equals(mdp2)) {
           // request.setAttribute("erreurLogin", "Les mots de passe ne sont pas identiques ! merci de vérifier votre saisie");
            // la vue reste à VUE_FORM_INS    
       // }

        try 
        {
//            PersonneDao.deletePersonBydate(new Timestamp(System.currentTimeMillis()));
//            Personne p = new Personne("", "", mail, "", jeton);           // Instance d'un objet p avec un constructeur paramétré qui initialise l'attribut est_Actif à false et la date_insertion à l'haure et date du système 
//            PersonneDao.insert(p);                                              // Dans la classe PersonneDao, nous avons des méthodes qui permettent de préparer des requêtes Sql qui seront exécutées côté base de données
//             Java//JavaMailUtil.sendMail(mail, jeton);                       // Dans la classe JavaMailUtil, nous avons l'implémentation de ma méthode sendMail() qui permet t'établie l'envoi du mail
//            vue = VUE_VERIFY;                                                   // la valeur de vue change à "/WEB-INF/verify.jsp"
        } 
        catch (Exception ex) 
        {
            try 
            {
//                Logger.getLogger(DemanderNouvMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
//                switch (ex.getErrorCode()) 
                {
//                    case Database.DOUBLON:
//                        boolean estConfirme = PersonneDao.EstValide(mail);
//                        msg = (estConfirme) ? "Email déjà enregistré et confirmé"
//                                : "Email déjà enregistré, veuillez confirmer votre inscription en cliquant sur le lien inclus dans le mail qui vous a été adressé";
//                        break;
//                    default:
//                        msg = "Pb avec la base de données "
//                                + ex.getMessage(); // seulement en dev (pb de sécurité)
//                        vue = VUE_ERREUR;
                }
               // request.setAttribute("erreurLogin", msg);
            }
            catch (Exception ex1) 
            {
                Logger.getLogger(DemanderNouvMdpServlet.class.getName()).log(Level.SEVERE, null, ex1);
            }
        
        }
        request.getRequestDispatcher(vue).forward(request, response);                   // La servlet nous envoi vers la vue appropriée en envoyant avec les objets request et response
    
        }
        

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

}

    