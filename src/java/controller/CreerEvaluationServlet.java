/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EvaluationDao;
import dao.ModuleDao;
import dao.SessionDao;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
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

/**
 *
 * @author kenzy
 */
@WebServlet(name = "CreerEvaluationServlet", urlPatterns = {"/creerEvaluation"})
public class CreerEvaluationServlet extends HttpServlet {

  private static final String VUE_FORM_CREATION_EVAL = "/WEB-INF/creerEvaluation.jsp";
  private static final String VUE_VALIDER = "/WEB-INF/accueil.jsp";
  private static final String VUE_ERREUR = "/WEB-INF/exception.jsp";

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    SessionDao daoSF = new SessionDao();
    Map<Integer, String> sessions;
    try {
      sessions = daoSF.getSession();
      request.setAttribute("sessions", sessions);
    } catch (SQLException ex) {
      Logger.getLogger(CreerEvaluationServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
    ModuleDao daoM = new ModuleDao();
    Map<Integer, String> modules;
    try {
      modules = daoM.getModule();
      request.setAttribute("modules", modules);
    } catch (SQLException ex) {
      Logger.getLogger(CreerEvaluationServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

    request.getRequestDispatcher(VUE_FORM_CREATION_EVAL).forward(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int idModule = Integer.parseInt(request.getParameter("idModule"));
    int idSesionFormation = Integer.parseInt(request.getParameter("idSesionFormation"));
    //LocalDateTime dateEffet = LocalDateTime.parse(request.getParameter("dateEffet"));
    String vue = VUE_FORM_CREATION_EVAL;

    if (idModule == 0 || idSesionFormation == 0) {
      request.setAttribute("erreur", "Veuillez renseigner tous les champs");
    } else {
      try {
        HttpSession session = request.getSession(true);
        Personne p = (Personne) session.getAttribute("user");
        Evaluation e = new Evaluation(idModule, idSesionFormation, LocalDateTime.now());
        EvaluationDao.insertEval(e, p);
        vue = VUE_VALIDER;

      } catch (Exception exc) {
        Logger.getLogger(ConnexionServlet.class
                .getName()).log(Level.SEVERE, null, exc);
        request.setAttribute(
                "exception", exc);
        vue = VUE_ERREUR;
      }
    }
    request.getRequestDispatcher(vue).forward(request, response);
  }
}
