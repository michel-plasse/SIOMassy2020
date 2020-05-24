package controller;

import dao.QuestionnaireDao;
import modele.Questionnaire;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name="questionnaires", urlPatterns = {"/questionnaires"})
public class QuestionnaireServlet extends HttpServlet {

    private final String VUE_OK = "/WEB-INF/questionnairesFormateur.jsp";
    private final String VUE_EXCEPTION = "/WEB-INF/exception.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vue = VUE_OK;
        try {
            QuestionnaireDao dao = new QuestionnaireDao();
            List<Questionnaire> questionnaires = dao.getQuestionnaires();
            request.setAttribute("questionnaires", questionnaires);
        } catch (SQLException exception){
            Logger.getLogger(QuestionnaireServlet.class.getName()).log(Level.SEVERE, null, exception);
            request.setAttribute("exception", "This world is full of things that don't go as you wish.");
            vue = VUE_EXCEPTION;
        }
        request.getRequestDispatcher(vue).forward(request, response);
    }
}
