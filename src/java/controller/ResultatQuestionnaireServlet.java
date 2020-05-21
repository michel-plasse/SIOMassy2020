/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import dao.ResultatQuestionnaireDao;
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
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author wowmi
 */
@WebServlet("/questionnaires")
public class ResultatQuestionnaireServlet extends HttpServlet {
    
    private final String VUE_OK = "/WEB-INF/resultatQuestionnaire.jsp";
    private final String VUE_ERREUR = "/WEB-INF/exception.jsp";
}
