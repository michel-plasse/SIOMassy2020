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

@WebServlet("/modifierProfil")
public class ModifierProfilServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  /**
   * Vue si succes ou en cas d'anomalie sur les valeurs saisies
   */
  private static final String VUE_FORM = "/WEB-INF/modifierProfil.jsp";
  /**
   * Vue si succes si MAJ OK
   */

  private static final String VUE_ACCUEIL = "/WEB-INF/accueil.jsp";

  /**
   * Vue si erreur (exception)
   */
  private static final String VUE_ERREUR = "/WEB-INF/exception.jsp";

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   * response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    Personne personne = (Personne) session.getAttribute("user");
    request.getRequestDispatcher(VUE_FORM).forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String vue = VUE_ACCUEIL;
    if (request.getParameter("bouton_valid").equals("Abandonner")) {
      //Abandon de la mise àjour
      request.setAttribute("Abandon", "Modification abandonnée");
      request.getRequestDispatcher(vue).forward(request, response);
    }
    vue = VUE_FORM;                      // modifierProfil.jsp
    String nom = request.getParameter("nom");
    String prenom = request.getParameter("prenom");
    String email = request.getParameter("email");
    String mdp1 = request.getParameter("mdp1");
    String mdp2 = request.getParameter("mdp2");
    HttpSession session = request.getSession(true);
    Personne personne = (Personne) session.getAttribute("user");
    personne.setNom(nom);
    personne.setPrenom(prenom);
    personne.setEmail(email);
    personne.setMdp(mdp1);    
    // nous faisons d'abord un test sur tous les champs du formulaire
    if (nom == null || nom.trim().isEmpty() || prenom == null || prenom.trim().isEmpty()
            || email == null || email.trim().isEmpty() || mdp1 == null || mdp1.trim().isEmpty() || mdp2 == null || mdp2.trim().isEmpty()) {
      request.setAttribute("erreurLogin", "Veuillez renseigner tous les champs");
      
    } else if (!email.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+")) {
      request.setAttribute("emailEstInvalide", true);
    } else if (!mdp1.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
      request.setAttribute("mdpEstInvalide", true);
    } else if (!mdp1.equals(mdp2)) {
      request.setAttribute("mdpEstDifferent ", true);
    } else {
      try {
        PersonneDao.majByIdPersonne(personne);
        vue = VUE_ACCUEIL;
        request.setAttribute("majOK", true);
      } catch (SQLException ex) {
        Logger.getLogger(ModifierProfilServlet.class.getName()).log(Level.SEVERE, null, ex);
        vue = VUE_ERREUR;
      }
    }
    request.getRequestDispatcher(vue).forward(request, response);
  }
}
