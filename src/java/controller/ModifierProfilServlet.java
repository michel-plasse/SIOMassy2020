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
  private static final String VUE_OK = "/WEB-INF/modifierProfil.jsp";
  /**
   * Vue si succes si MAJ OK
   */

  private static final String VUE_MENU = "/WEB-INF/menu.jsp";

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
    request.getRequestDispatcher(VUE_OK).forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String vue = VUE_MENU;

    if (request.getParameter("bouton_Valid").equals("Abandonner")) {
      //Abandon de la mise àjour
      vue = VUE_MENU;
      request.setAttribute("Abandon", "Modification abandonnée");
      request.getRequestDispatcher(vue).forward(request, response);
    }

    vue = VUE_OK;                      // modifierProfil.jsp

    String nom = request.getParameter("nom");
    String prenom = request.getParameter("prenom");
    String email = request.getParameter("email");
    String mdp = request.getParameter("mdp");
    int idPersonne = Integer.parseInt(request.getParameter("id"));

    // nous faisons d'abord un test sur tous les champs du formulaire
    if (nom == null || nom.trim().isEmpty() || prenom == null || prenom.trim().isEmpty()
            || email == null || email.trim().isEmpty() || mdp == null || mdp.trim().isEmpty()) {
      request.setAttribute("erreurLogin", "Veuillez renseigner tous les champs");
      request.getRequestDispatcher(vue).forward(request, response);
    } else if (!email.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+")) {
      request.setAttribute("emailInvalide", true);
      request.getRequestDispatcher(vue).forward(request, response);
    } else if (!mdp.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
      request.setAttribute("mdpEstInvalide", true);
      request.getRequestDispatcher(vue).forward(request, response);
    } else {
      try {
        PersonneDao.majByIdPersonne(idPersonne, nom, prenom, email, mdp);
        vue = VUE_MENU;

        request.setAttribute("MajOK", "Mise à jour effectuée");
        request.getRequestDispatcher(vue).forward(request, response);
      } catch (SQLException ex) {
        Logger.getLogger(ModifierProfilServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
