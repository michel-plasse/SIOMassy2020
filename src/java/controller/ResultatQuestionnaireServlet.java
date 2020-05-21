package controller;

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


@WebServlet("/ResultatQuestionnaireServlet")
public class ResultatQuestionnaireServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       

    public ResultatQuestionnaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            ResultatQuestionnaireDao dao = new ResultatQuestionnaireDao();
            List<Personne> personnes = dao.getStagiaire(1);
            request.setAttribute("stagiaires", personnes);
        } catch (SQLException exception){
            Logger.getLogger(QuestionnaireServlet.class.getName()).log(Level.SEVERE, null, exception);
            request.setAttribute("exception",exception);
            
        }
       
    
        
        
        
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/resultatQuestionnaire.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}