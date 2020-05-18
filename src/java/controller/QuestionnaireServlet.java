/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author wowmi
 */
@WebServlet("/questionnaires")
public class QuestionnaireServlet extends HttpServlet {

    private final String VUE_OK = "/WEB-INF/questionnairesDuFormateur.jsp";
    private final String VUE_ERREUR = "/WEB-INF/exception.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vue = VUE_OK;
        try {
            QuestionnaireDao dao = new QuestionnaireDao();
            List<Questionnaire> questionnaires = dao.getLesQuestionnaires();
            request.setAttribute("questionnaires", questionnaires);
        } catch (SQLException exception){
            Logger.getLogger(QuestionnaireServlet.class.getName()).log(Level.SEVERE, null, exception);
            request.setAttribute("exception",exception);
            vue = VUE_ERREUR;
        }
        request.getRequestDispatcher(vue).forward(request, response);
    }
}
