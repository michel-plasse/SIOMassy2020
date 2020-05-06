package controller;

import dao.QuestionnaireDao;
import modele.Personne;
import modele.Questionnaire;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/questionnaire")
public class QuestionnaireServlet extends HttpServlet {

    protected void doGet(HttpServletRequest questionnaireRequest, HttpServletResponse questionnaireResponse) throws ServletException, IOException {
        ArrayList<Questionnaire> questionnaire = null;
        QuestionnaireDao dao = new QuestionnaireDao();
        int idAuteur = 0;

        try {
            if (((Personne) questionnaireRequest.getSession(true).getAttribute("user")).isEstFormateur()) {
                Personne personne = (Personne) questionnaireRequest.getSession(true).getAttribute("user");
                idAuteur = personne.getId();
                questionnaire = dao.getListByIdAuteur(idAuteur);
                questionnaireRequest.setAttribute("listeQuestionnaires", questionnaire);
                questionnaireRequest.getRequestDispatcher("/questionnaireListe.jsp").forward(questionnaireRequest, questionnaireResponse);
            }

        } catch (SQLException throwables) {
            questionnaireResponse.getWriter().println("You broke it");
            throwables.printStackTrace();
        }

    }



}
