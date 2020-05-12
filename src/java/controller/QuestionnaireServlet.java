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
import java.util.ArrayList;

@WebServlet("/questionnaire")
public class QuestionnaireServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Questionnaire> questionnaires = null;
        QuestionnaireDao questionnaireDao = new QuestionnaireDao();
        int idFormateur = 0;

        try {
            if (((Personne) request.getSession(true).getAttribute("user").i))
        }




    }



}
