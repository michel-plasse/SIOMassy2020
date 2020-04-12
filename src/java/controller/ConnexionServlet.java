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
import javax.servlet.http.HttpSession;
import modele.Personne;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {

  private static final String VUE_INDEX = "/index.jsp";
  private static final String VUE_FORM = "/WEB-INF/connexion.jsp";
  private static final String VUE_ERREUR = "/WEB-INF/exception.jsp";
  private static final String VUE_CONNEXION_OK = "/WEB-INF/connexionOK.jsp";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(VUE_FORM).forward(request, response);
  }

  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    String vue = VUE_FORM;
    Personne user = null;
    try {
      if (login == null || login.trim().isEmpty()|| password == null || password.trim().isEmpty()) {
        request.setAttribute("erreurLogin", "Les champs sont obligatoires");
      } 
      user = PersonneDao.getByLoginPassword(login, password);
      if(user != null && user.getestActif()== true ){
        HttpSession maSession = request.getSession(true);
        maSession.setAttribute("user", user);
        request.setAttribute("nomprenom", "Session de : "+ user.getPrenom()+" "+ user.getNom());
        vue = VUE_CONNEXION_OK;
              
        }
      else if(user != null && user.getestActif()== false ){
          request.setAttribute("erreurLogin","Vous n'avez pas encore validé votre compte !!!");
          vue=VUE_FORM;
      }
      else {
          request.setAttribute("erreurLogin","Utilisateur inconnu ou mot de passe incorrect");
          vue=VUE_FORM;
      }
      
      
      
      
    } catch (SQLException exc) {
      Logger.getLogger(ConnexionServlet.class.getName()).log(Level.SEVERE, null, exc);
      request.setAttribute("exception", exc);
      vue = VUE_ERREUR;
    }
    // Passer la main à la JSP
    request.getRequestDispatcher(vue).forward(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
