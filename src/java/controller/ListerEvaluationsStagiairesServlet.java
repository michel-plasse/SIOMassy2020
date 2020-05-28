package controller;

import dao.EvaluationDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Evaluation;
import modele.Personne;


@WebServlet(name = "ListerEvaluationsServlet", urlPatterns = {"/evaluationsStagiaires"})
public class ListerEvaluationsStagiairesServlet extends HttpServlet {

  private final String VUE_OK = "/WEB-INF/listeEvaluations.jsp";
  private final String VUE_ERREUR = "/WEB-INF/exception.jsp";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String vue = VUE_OK;
    try {
      HttpSession session = request.getSession(true);
      Personne p = (Personne) session.getAttribute("user");
      if (p == null) {
        request.setAttribute("exception", "Vous devez vous connecter");
        vue = VUE_ERREUR;
      } else {
        List<Evaluation> evaluations = EvaluationDao.getEvaluationByFormateur(p.getId());
        request.setAttribute("evaluation", evaluations);
      }

    } catch (SQLException exc) {
      Logger.getLogger(ConnexionServlet.class.getName()).log(Level.SEVERE, null, exc);
      request.setAttribute("exception", exc);
      vue = VUE_ERREUR;
    }
    getServletContext().getRequestDispatcher(vue).forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}