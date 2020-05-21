package controller;

import dao.PersonneDao;
import dao.QuestionnaireDao;
import dao.ResultatQuestionnaireDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Personne;
import modele.Questionnaire;
import modele.ResultatQuestionnaire;

@WebServlet("/resultatQuestionnaire")
public class ResultatQuestionnaireServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Vue si succes
     */
    private static final String VUE_OK = "/WEB-INF/resultatQuestionnaire.jsp";

    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Soyons optimistes
        String vue = VUE_OK;
        // d'abord en dur
        int idQuestionnaire = 1;
        // Recuperer les donnees (ici, les stagiaires)
        try {

            List<ResultatQuestionnaire> resquest = ResultatQuestionnaireDao.getresquest(idQuestionnaire);
            request.setAttribute("resquest", resquest);
        } catch (SQLException exc) {
            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;
        }
        // Passer la main a la vue
        request.getRequestDispatcher(vue).forward(request, response);
    }

}
